package pl.parking.view.RecyclerLists;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import pl.parking.controller.api.ApiController;
import pl.parking.controller.api.ApiStaticData;
import pl.parking.model.CarBooking;
import pl.parking.service.ParkingService;
import pl.parking.view.RecyclerListCars.RecyclerListCar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class BookingViewFragment extends Fragment {

    private static final String TAG = "BookingViewFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public List<CarBooking> modelList = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private BookingViewAdapter mAdapter;
    private RecyclerViewScrollListener scrollListener;

    public BookingViewFragment() {}

    public static BookingViewFragment newInstance(String param1, String param2) {
        BookingViewFragment fragment = new BookingViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BookingViewFragment newInstance() {
        BookingViewFragment fragment = new BookingViewFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recycler_view_divider, menu);

        final SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(getActivity().SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));

        EditText searchEdit =
                ((EditText)
                        searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        searchEdit.setTextColor(Color.WHITE);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Search");

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] =
                new InputFilter() {
                    @Override
                    public CharSequence filter(
                            CharSequence source,
                            int start,
                            int end,
                            Spanned dest,
                            int dstart,
                            int dend) {

                        for (int i = start; i < end; i++) {

                            if (!Character.isLetterOrDigit(source.charAt(i))) return "";
                        }

                        return null;
                    }
                };
        searchEdit.setFilters(fArray);
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        v.setBackgroundColor(Color.TRANSPARENT);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        List<CarBooking> filterList = new ArrayList<CarBooking>();
                        if (s.length() > 0) {
                            for (int i = 0; i < modelList.size(); i++) {
                                if (modelList
                                        .get(i)
                                        .toString()
                                        .toLowerCase()
                                        .contains(s.toString().toLowerCase())) {
                                    filterList.add(modelList.get(i));
                                    mAdapter.updateList(filterList);
                                }
                            }

                        } else {
                            mAdapter.updateList(modelList);
                        }
                        return false;
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        try {
            view =
                    inflater.inflate(
                            R.layout.fragment_recycler_view_fragment_car_booking_cards,
                            container,
                            false);

            findViews(view);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            setAdapter();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
        swipeRefreshRecyclerList.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        new Getcar_booking().execute();

                        new Handler()
                                .postDelayed(
                                        new Runnable() {
                                            @Override
                                            public void run() {

                                                if (swipeRefreshRecyclerList.isRefreshing())
                                                    swipeRefreshRecyclerList.setRefreshing(false);
                                            }
                                        },
                                        5000);
                    }
                });
    }

    private void findViews(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList =
                (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_recycler_list);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void setAdapter() {

        try {
            mAdapter = new BookingViewAdapter(getActivity(), ApiStaticData.bookings);

            recyclerView.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

            layoutManager.setReverseLayout(true);
            layoutManager.setStackFromEnd(true);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mAdapter);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }

        scrollListener =
                new RecyclerViewScrollListener() {

                    public void onEndOfScrollReached(RecyclerView rv) {

                        Toast.makeText(
                                        getActivity(),
                                        "End of the RecyclerView reached. Do your pagination stuff here",
                                        Toast.LENGTH_SHORT)
                                .show();

                        scrollListener.disableScrollListener();
                    }
                };
        recyclerView.addOnScrollListener(scrollListener);

        mAdapter.SetOnItemClickListener(
                new BookingViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, CarBooking model) {

                        Getcar_booking obj = new Getcar_booking();
                        obj.execute();
                        try {
                            obj.get(1000, TimeUnit.MILLISECONDS);
                        } catch (InterruptedException e) {
                            Log.e(getClass().getSimpleName(), "Exception handled", e);
                        } catch (ExecutionException e) {
                            Log.e(getClass().getSimpleName(), "Exception handled", e);
                        } catch (TimeoutException e) {
                            Log.e(getClass().getSimpleName(), "Exception handled", e);
                        } finally {
                        }
                        ApiStaticData.cars = ApiStaticData.bookings.get(position).getBooking();
                        try {
                            Intent intent = new Intent(getActivity(), RecyclerListCar.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            Log.e(getClass().getSimpleName(), "Exception handled", e);
                        }
                    }
                });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);

        public void onListItemClick(ListView l, View v, int position, long id);
    }

    public class Getcar_booking extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            SharedPreferences prefs = getContext().getSharedPreferences("Token.txt", MODE_PRIVATE);
            String Token = prefs.getString("Token", null);

            String result = "1";
            try {

                Log.w(TAG, "doInBackground:Retrofit mobile client initialization ");
                ApiController service =
                        ParkingService.getRetrofitInstance().create(ApiController.class);
                Call<List<CarBooking>> call = service.getCarBookingToken(Token);
                call.enqueue(
                        new Callback<List<CarBooking>>() {
                            @Override
                            public void onResponse(
                                    Call<List<CarBooking>> call,
                                    Response<List<CarBooking>> response) {

                                if (!response.isSuccessful()) {
                                    Log.w(
                                            TAG,
                                            "doInBackground:Retrofit mobile client Response Code: "
                                                    + response.code());
                                    Log.w(
                                            TAG,
                                            "doInBackground:Retrofit mobile client Response message: "
                                                    + response.message());
                                    Toast.makeText(
                                                    getContext(),
                                                    R.string.internet_error,
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                } else {

                                    try {
                                        ApiStaticData.bookings = response.body();
                                    } catch (Exception e) {
                                        Log.e(getClass().getSimpleName(), "Exception handled", e);
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<CarBooking>> call, Throwable t) {

                                Log.e(
                                        TAG,
                                        "doInBackground:Retrofit mobile client Failure JS: "
                                                + t.getMessage());
                            }
                        });
            } catch (Exception e) {

                Log.e(TAG, e.getMessage(), e);
                result = "0";

            } finally {
                Log.i(TAG, "doInBackground:out value" + result);
                return result;
            }
        }
    }
}
