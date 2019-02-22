package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List_Car;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.support.v4.widget.SwipeRefreshLayout;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;

import android.widget.Toast;
import android.os.Handler;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.support.v7.widget.SearchView;
import android.support.v4.view.MenuItemCompat;
import android.app.SearchManager;
import android.widget.EditText;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.Spanned;

import static java.security.AccessController.getContext;


public class Recycler_List_car extends AppCompatActivity {

    private RecyclerView recyclerView;

    // @BindView(R.id.recycler_view)
    // RecyclerView recyclerView;

    //@BindView(R.id.toolbar)
    //Toolbar toolbar;
    private Toolbar toolbar;

    // @BindView(R.id.swipe_refresh_recycler_list)
    // SwipeRefreshLayout swipeRefreshRecyclerList;

    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private Recycler_List_Car_Adapter mAdapter;
    private RecyclerViewScrollListener scrollListener;

    private ArrayList<Car> modelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_recycler__list_car);

        // ButterKnife.bind(this);
        findViews();
        initToolbar("Takeoff Android");
        setAdapter();

        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

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

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_recycler_list);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_search, menu);


        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) this.getSystemService(this.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

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
                ArrayList<Car> filterList = new ArrayList<Car>();
                if (s.length() > 0) {
                    for (int i = 0; i < modelList.size(); i++) {
                        if (modelList.get(i).getRegistrationPlate().toLowerCase().contains(s.toString().toLowerCase())) {
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


        return true;
    }

    private void setAdapter() {

//
//        modelList.add(new Car("Android", "Hello " + " Android"));
//        modelList.add(new Car("Beta", "Hello " + " Beta"));
//        modelList.add(new Car("Cupcake", "Hello " + " Cupcake"));
//        modelList.add(new Car("Donut", "Hello " + " Donut"));
//        modelList.add(new Car("Eclair", "Hello " + " Eclair"));
//        modelList.add(new Car("Froyo", "Hello " + " Froyo"));
//        modelList.add(new Car("Gingerbread", "Hello " + " Gingerbread"));
//        modelList.add(new Car("Honeycomb", "Hello " + " Honeycomb"));
//        modelList.add(new Car("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich"));
//        modelList.add(new Car("Jelly Bean", "Hello " + " Jelly Bean"));
//        modelList.add(new Car("KitKat", "Hello " + " KitKat"));
//        modelList.add(new Car("Lollipop", "Hello " + " Lollipop"));
//        modelList.add(new Car("Marshmallow", "Hello " + " Marshmallow"));
//        modelList.add(new Car("Nougat", "Hello " + " Nougat"));
//        modelList.add(new Car("Android O", "Hello " + " Android O"));


        try {
            String filePath = getApplicationContext().getFilesDir().getPath().toString() + "/car.tmp";
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            modelList = (ArrayList<Car>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        } catch (ClassNotFoundException e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }


        mAdapter = new Recycler_List_Car_Adapter(Recycler_List_car.this, modelList);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        scrollListener = new RecyclerViewScrollListener() {

            public void onEndOfScrollReached(RecyclerView rv) {

//                Toast.makeText(Recycler_List_car.this, "End of the RecyclerView reached. Do your pagination stuff here", Toast.LENGTH_SHORT).show();

                scrollListener.disableScrollListener();
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
          /*
             Note: The below two methods should be used wisely to handle the pagination enable and disable states based on the use case.
                     1. scrollListener.disableScrollListener(); - Should be called to disable the scroll state.
                     2. scrollListener.enableScrollListener(); - Should be called to enable the scroll state.
          */


        mAdapter.SetOnItemClickListener(new Recycler_List_Car_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Car model) {

                //handle item click events here
//                Toast.makeText(Recycler_List_car.this, "Hey " + model.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });

        mAdapter.SetOnCheckedListener(new Recycler_List_Car_Adapter.OnCheckedListener() {
            @Override
            public void onChecked(View view, boolean isChecked, int position, Car model) {

            }

        });


    }


}
