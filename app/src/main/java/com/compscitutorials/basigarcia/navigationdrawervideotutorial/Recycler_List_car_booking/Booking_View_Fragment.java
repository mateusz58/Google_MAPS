package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List_car_booking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Collator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import android.support.v4.widget.SwipeRefreshLayout;


import com.compscitutorials.basigarcia.navigationdrawervideotutorial.MainActivity;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Booking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List_Car.Recycler_List_car;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.SignupActivity;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Time_operations.Time_converter;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.car_booking;

import android.widget.ListView;
import android.widget.Toast;

import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.support.v4.view.MenuItemCompat;
import android.app.SearchManager;
import android.widget.EditText;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.Spanned;


import android.view.ViewGroup;
import android.view.MenuInflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class Booking_View_Fragment extends Fragment {


    private static final String TAG="Booking_View_Fragment";

    public Booking_View_Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recycler_view_divider, menu);

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getActivity().SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        //changing edittext color
        EditText searchEdit = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        searchEdit.setTextColor(Color.WHITE);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Search");

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {

                    if (!Character.isLetterOrDigit(source.charAt(i)))
                        return "";
                }


                return null;


            }
        };
        searchEdit.setFilters(fArray);
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<car_booking> filterList = new ArrayList<car_booking>();
                if (s.length() > 0) {
                    for (int i = 0; i < modelList.size(); i++) {
                        if (modelList.get(i).toString().toLowerCase().contains(s.toString().toLowerCase())) {
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

    public class Getcar_booking extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... params)  {


            SharedPreferences prefs = getContext().getSharedPreferences("Token.txt", MODE_PRIVATE);
            String Token = prefs.getString("Token", null);

            String result = "1";
            try {

                Log.w(TAG, "doInBackground:Retrofit mobile client initialization ");
                API_end_points service = Parking_Service.getRetrofitInstance().create(API_end_points.class);
                Call<ArrayList<car_booking>> call = service.getcar_booking_token(Token);
                call.enqueue(new Callback<ArrayList<car_booking>>() {
                    @Override
                    public void onResponse(Call<ArrayList<car_booking>> call, Response<ArrayList<car_booking>> response) {


                        if (!response.isSuccessful()) {
                            Log.w(TAG, "doInBackground:Retrofit mobile client Response Code: " + response.code());
                            Log.w(TAG, "doInBackground:Retrofit mobile client Response message: " + response.message());
                            Toast.makeText(getContext(), R.string.internet_error, Toast.LENGTH_SHORT).show();
                        } else {

                            try {

                                String filePath = getContext().getFilesDir().getPath().toString() + "/car_booking.tmp";
                                File car_booking_file = new File(filePath);
                                car_booking_file.createNewFile(); // if file already exists will do nothing
                                FileOutputStream fos = new FileOutputStream(filePath);
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(response.body());
                                oos.close();
                            } catch (IOException e) {
                                Log.e(getClass().getSimpleName(), "Exception handled", e);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<car_booking>> call, Throwable t) {

                        Log.e(TAG, "doInBackground:Retrofit mobile client Failure JS: " + t.getMessage());
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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;
//    @BindView(R.id.swipe_refresh_recycler_list)
//    SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private Booking_View_Adapter mAdapter;
    private RecyclerViewScrollListener scrollListener;

    public ArrayList<car_booking> modelList = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Booking_View_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Booking_View_Fragment newInstance(String param1, String param2) {
        Booking_View_Fragment fragment = new Booking_View_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Booking_View_Fragment newInstance() {
        Booking_View_Fragment fragment = new Booking_View_Fragment();
        return fragment;
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
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment_recycler_view_fragment_car_booking_cards, container, false);

            // ButterKnife.bind(this);
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
//        getCurrentFragment();



        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Getcar_booking().execute();
                // Do your stuff on refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshRecyclerList.isRefreshing())
                            swipeRefreshRecyclerList.setRefreshing(false);
                    }
                }, 5000);




            }
        });


    }


    private void findViews(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_recycler_list);
    }


    // TODO: Rename method, update argument and hook method into UI event
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void setAdapter() {


        try {
//            modelList=
//


            try {
                String filePath = getContext().getFilesDir().getPath().toString() + "/car_booking.tmp";
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                modelList = (ArrayList<car_booking>) ois.readObject();
                Collections.sort(modelList, new Comparator<car_booking>() {
                    @Override
                    public int compare(car_booking o1, car_booking o2) {
                        try {
                            return Time_converter.convert_string_to_date_time(o1.getDateFrom()).compareTo(Time_converter.convert_string_to_date_time(o2.getDateFrom()));

                        } catch (ParseException e1) {
                            Log.e(getClass().getSimpleName(), "Exception handled", e1);
                            return 0;
                        }
                    }
                });
                ois.close();

                mAdapter = new Booking_View_Adapter(getActivity(), modelList);

                recyclerView.setHasFixedSize(true);

                // use a linear layout manager
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
            } catch (IOException e) {
                Log.e(getClass().getSimpleName(), "Exception handled", e);
            } catch (ClassNotFoundException e) {
                Log.e(getClass().getSimpleName(), "Exception handled", e);
            }

//            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
//            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.booking_divider_recyclerview));
//            recyclerView.addItemDecoration(dividerItemDecoration);

            recyclerView.setAdapter(mAdapter);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }


        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {

                Toast.makeText(getActivity(), "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();

                scrollListener.disableScrollListener();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
          /*
             Note: The below two methods should be used wisely to handle the pagination enable and disable states based on the use case.
                     1. scrollListener.disableScrollListener(); - Should be called to disable the scroll state.
                     2. scrollListener.enableScrollListener(); - Should be called to enable the scroll state.
          */


        mAdapter.SetOnItemClickListener(new Booking_View_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, car_booking model) {
                //handle item click events here
                Toast.makeText(getActivity(), "Hey " + model.getParking(), Toast.LENGTH_SHORT).show();

                    List<Car> set = modelList.get(position).getBooking();
                    Collections.sort(set, new Comparator<Car>() {
                        @Override
                        public int compare(Car o1, Car o2) {
                            try {
                                return Time_converter.convert_string_to_date_time(o1.getDateFrom()).compareTo(Time_converter.convert_string_to_date_time(o2.getDateFrom()));

                            } catch (ParseException e1) {
                                Log.e(getClass().getSimpleName(), "Exception handled", e1);
                                return 0;
                            }
                        }
                    });

//                    HashSet<Car>hashSet=new HashSet<Car>(modelList.get(position).getBooking());
//                    hashSet.addAll((modelList.get(position).getBooking()));
                try {
                    String filePath = getContext().getFilesDir().getPath().toString() + "/car.tmp";
                    File car_file = new File(filePath);
                    car_file.createNewFile(); // if file already exists will do nothing
                    FileOutputStream fos = new FileOutputStream(filePath);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(set);
                    oos.close();
                } catch (IOException e1) {
                    Log.e(getClass().getSimpleName(), "Exception handled", e1);
                }


                try {

                        Intent intent = new Intent(getActivity(), Recycler_List_car.class);
                        startActivity(intent);

                    } catch (Exception e) {
                        Log.e(getClass().getSimpleName(), "Exception handled", e);
                    }


            }
        });

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        public void onListItemClick(ListView l, View v, int position, long id);

        }

    }



