package pl.parking.app;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.parking.activity.LoginActivity;
import pl.parking.activity.Mapfragment;
import pl.parking.controller.api.ApiStaticData;
import pl.parking.controller.api.ApiController;
import pl.parking.controller.api.UserApi;
import pl.parking.controller.reponses.successfulResponses.ResponseLogOut;
import pl.parking.model.CarBooking;
import pl.parking.model.Parking;
import pl.parking.service.ParkingService;
import pl.parking.view.RecyclerLists.BookingViewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                BookingViewFragment.OnFragmentInteractionListener {

    static final int PERMISSION_READ_STATE = 1;
    private static final String TAG = "MainActivity";
    public static UserApi Parking_list;
    public static ApiController service;
    public static List<Parking> parkingList;
    NavigationView navigationView = null;

    TextView logintext = null;
    private BookingViewFragment booking_view_fragment;
    private String result;
    private String Token;
    private List<CarBooking> carBookingList;

    @Override
    public void onFragmentInteraction(Uri uri) {}

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
        Token = prefs.getString("Token", null);

        if (Token != null) {
            new GetCarBooking().execute();
        }
        new GetParking().execute();
        setContentView(R.layout.activity_main);
        Log.w(TAG, "onCreate: ");
        ActivityCompat.requestPermissions(
                this, new String[] {Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        logintext = (TextView) findViewById(R.id.nav_logout);

        getSupportActionBar().show();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume: ");
        getSupportActionBar().show();
    }

    @Override
    protected void onStart() {
        Log.w(TAG, "onStart: ");

        try {
            SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
            Token = prefs.getString("Token", null);
            super.onStart();
            if (Token != null) {

                navigationView.getMenu().getItem(2).setIcon(R.drawable.ic_no_encryption_black_24dp);
                navigationView.getMenu().getItem(2).setTitle("Log out");
                navigationView.getMenu().getItem(R.id.nav_parkingplace).setEnabled(true);

            } else {
                navigationView.getMenu().getItem(2).setIcon(R.drawable.ic_https_black_24dp);
                navigationView.getMenu().getItem(2).setTitle("Sign in");
                navigationView.getMenu().getItem(R.id.nav_parkingplace).setEnabled(false);
            }

        } catch (Exception ex) {
            Log.d(TAG, ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.w(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {

            SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
            String token = prefs.getString("Token", null);

            if (token != null) {
                new GetCarBooking().execute();
            }
            new GetParking().execute();
            return true;
        }
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_map) {

            getSupportActionBar().show();
            new GetParking().execute();

        } else if (id == R.id.nav_parkingplace) {
            Log.w(TAG, "onNavigationItemSelected: Restartactivity");

            SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
            Token = prefs.getString("Token", null);

            if (Token != null) {

                try {

                    BookingViewFragment fragment = new BookingViewFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                    getSupportActionBar().hide();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Exception handled", e);
                }

            } else {
                Toast.makeText(
                                MainActivity.this,
                                "Please log in to access this section",
                                Toast.LENGTH_SHORT)
                        .show();
            }

        } else if (id == R.id.nav_logout) {
            SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
            String token = prefs.getString("Token", null);

            if (ApiStaticData.is_Token = false) {

                ApiController apiEndpoints =
                        ParkingService.getRetrofitInstance().create(ApiController.class);
                Call<ResponseLogOut> call = apiEndpoints.postLogout(token);
                call.enqueue(
                        new Callback<ResponseLogOut>() {
                            @Override
                            public void onResponse(
                                    Call<ResponseLogOut> call, Response<ResponseLogOut> response) {
                                if (response.isSuccessful()) {}
                            }

                            @Override
                            public void onFailure(Call<ResponseLogOut> call, Throwable t) {}
                        });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Email", null);
                editor.putString("Token", null);
                editor.putString("User_id", null);

                editor.commit();
            }
            ApiStaticData.is_Token = false;
            Intent myIntent = new Intent(this, LoginActivity.class);
            this.startActivity(myIntent);
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class GetCarBooking extends AsyncTask<Void, Void, String> implements Serializable {

        @Override
        protected String doInBackground(Void... params) {

            result = "1";
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
                                                    MainActivity.this,
                                                    R.string.internet_error,
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                } else {

                                    result = "1";
                                    try {
                                        ApiStaticData.bookings = response.body();
                                        String filePath =
                                                getApplicationContext()
                                                                .getFilesDir()
                                                                .getPath()
                                                                .toString()
                                                        + "/CarBooking.tmp";
                                        File car_booking_file = new File(filePath);
                                        car_booking_file.createNewFile();

                                        FileOutputStream fos = new FileOutputStream(filePath);
                                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                                        oos.writeObject(carBookingList);
                                        oos.close();
                                    } catch (IOException e) {
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
                                result = "0";
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

        @Override
        protected void onPostExecute(String out) {

            Log.w(TAG, "onPostExecute:Map initialization ");
            try {
                if (out == "1") {}
            } catch (Exception e) {
                Log.v(TAG, e.toString());
            }
        }
    }

    class GetParking extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            result = "0";
            try {

                Log.w(TAG, "doInBackground:Retrofit mobile client initialization ");
                ApiController service =
                        ParkingService.getRetrofitInstance().create(ApiController.class);
                Call<List<Parking>> call = service.getAllParkings();
                call.enqueue(
                        new Callback<List<Parking>>() {
                            @Override
                            public void onResponse(
                                    Call<List<Parking>> call, Response<List<Parking>> response) {

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
                                                    MainActivity.this,
                                                    R.string.internet_error,
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    return;
                                }
                                parkingList = response.body();
                                for (Parking data : parkingList) {
                                    String content = "";
                                    content += "id: " + data.getId() + "\n";
                                    content += "Parking name: " + data.getParkingName() + "\n";
                                    Log.w(
                                            TAG,
                                            "doInBackground:Retrofit mobile client  JSON: "
                                                    + content);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Parking>> call, Throwable t) {
                                Log.e(
                                        TAG,
                                        "doInBackground:Retrofit mobile client Failure JS: "
                                                + t.getMessage());
                            }
                        });

                return result;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);

            } finally {
                Log.i(TAG, "doInBackground:out value" + result);
                return result;
            }
        }

        @Override
        protected void onPostExecute(String out) {
            Log.w(TAG, "onPostExecute:Map initialization ");

            try {
                Mapfragment fragment = new Mapfragment();

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();

            } catch (Exception e) {
                Log.v(TAG, e.toString());
            }
        }
    }
}
