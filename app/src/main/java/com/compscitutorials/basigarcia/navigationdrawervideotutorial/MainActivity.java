package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.Manifest;
import android.content.Context;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.FactoryAPI;
//import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.FactoryAPIFactory;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.ErrorUtils;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List.Booking_View_Fragment;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List.Booking_View_Fragment.OnFragmentInteractionListener;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.TEMP.FactoryAPI;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Parking;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Log_out;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Login;
//import com.magnet.android.mms.MagnetMobileClient;
//import com.magnet.android.mms.async.Call;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnFragmentInteractionListener {

   private Booking_View_Fragment booking_view_fragment;









    private final String TAG="MainActivity";
    ////TRASES
    public static FactoryAPI Parking_list;
     public static API_end_points service;
    private String result;
    NavigationView navigationView = null;

    TextView logintext=null;
    Toolbar toolbar = null;
    ImageButton floatButton;

    final int PERMISSION_READ_STATE=1;
    public static List<Parking> parking_list;

    final String url="http://polar-plains-14145.herokuapp.com/parks/";

    @Override
    public void onFragmentInteraction(Uri uri) {

        Log.w(TAG,"Interaction");

    }


    class GetParking extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            result = "0";
            try {

                Log.w(TAG, "doInBackground:Retrofit mobile client initialization ");
                API_end_points service = Parking_Service.getRetrofitInstance().create(API_end_points.class);
                Call<List<Parking>> call = service.getParking();
                call.enqueue(new Callback<List<Parking>>() {
                    @Override
                    public void onResponse(Call<List<Parking>> call, Response<List<Parking>> response) {


                        if (!response.isSuccessful()) {
                            Log.w(TAG, "doInBackground:Retrofit mobile client Response Code: "+response.code());
                            Log.w(TAG, "doInBackground:Retrofit mobile client Response message: "+response.message());
                            Toast.makeText(MainActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        parking_list = response.body();
                        for (Parking data : parking_list) {
                            String content = "";
                            content += "id: " + data.getid() + "\n";
//                            content += "User ID: " + data.getUser() + "\n";
                            content += "Parking name: " + data.getparking_name() + "\n";
//                            content += "Registration plate: " + data.getRegistrationPlate() + "\n\n";
                            Log.w(TAG, "doInBackground:Retrofit mobile client  JSON: "+content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Parking>> call, Throwable t) {

                        Log.e(TAG, "doInBackground:Retrofit mobile client Failure JS: "+t.getMessage());
                    }
                });

                return result;
            } catch (Exception e) {

                Log.e(TAG, e.getMessage(), e);

            } finally {

                Log.i(TAG, "doInBackground:out value"+result);


                return result;

            }
        }

        @Override
        protected void onPostExecute(String out) {

                Log.w(TAG, "onPostExecute:Map initialization ");
                //Log.w("JSON", "Lista pobranych wspolrzednych" + out);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GetParking().execute();
        setContentView(R.layout.activity_main);
        Log.w(TAG, "onCreate: ");
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //How to change elements in the header programatically
        logintext = (TextView) findViewById(R.id.nav_logout);

        getSupportActionBar().show();
        navigationView.setNavigationItemSelectedListener(this);






    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume: " );
        getSupportActionBar().show();

    }

    @Override
       protected void onStart() {


          Log.w(TAG, "onStart: ");

          try {

              super.onStart();
              if(API.is_Token==true) {
                  // navigationView.getMenu().getItem(2).setChecked(true);
                  navigationView.getMenu().getItem(2).setIcon(R.drawable.ic_no_encryption_black_24dp);
                  navigationView.getMenu().getItem(2).setTitle("Log out");
                  navigationView.getMenu().getItem(R.id.nav_parkingplace).setEnabled(true);

              }
              else
              {
                  navigationView.getMenu().getItem(2).setIcon(R.drawable.ic_https_black_24dp);
                  navigationView.getMenu().getItem(2).setTitle("Sign in");
                  navigationView.getMenu().getItem(R.id.nav_parkingplace).setEnabled(false);
              }

              booking_view_fragment=new Booking_View_Fragment();

          }catch(Exception ex)
          {
              Log.d(TAG,ex.getMessage());

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
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.w(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.main, menu);





        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            ////pobierana lista zarezherwowanyc parkingow
            new GetParking().execute();
            return true;
        }

        if (id == R.id.action_search) {
           // Toast.makeText(this, "Searching", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {//przejscie do mapy

            getSupportActionBar().show();
            new GetParking().execute();



        } else if (id == R.id.nav_parkingplace) {
            Log.w(TAG, "onNavigationItemSelected: Restartactivity");


                getSupportActionBar().show();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, booking_view_fragment);
                fragmentTransaction.commit();

//            Parkinghistory fragment = new Parkinghistory();
//            //fragment.newInstance("BLA","BLA1");
//            android.support.v4.app.FragmentTransaction fragmentTransaction =
//                    getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container,fragment);
//            fragmentTransaction.commit();



        } else if (id == R.id.nav_logout) {


            if(API.is_Token) {

                //Magic is here at .execute() instead of .enqueue()
                //Retrive token
                SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
                String token = prefs.getString("Token", "default_value_here_if_string_is_missing");

                API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
                // Pobranie listy
                Call<Response_Log_out> call = apiEndpoints.post_logout(token);
                call.enqueue(new Callback<Response_Log_out>() {
                    @Override
                    public void onResponse(Call<Response_Log_out> call, Response<Response_Log_out> response) {
                        if (response.isSuccessful()) {
                            Response_Log_out response_log_out = response.body();
                        }

                    }

                    @Override
                    public void onFailure(Call<Response_Log_out> call, Throwable t) {

                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                SharedPreferences prefs_temp = getSharedPreferences("Token.txt", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs_temp.edit();
                editor.putString("Email", "none");
                editor.putString("Token", "Token" +"none");
                editor.putString("User_id", "none");
                editor.commit();
            }


            ///przejscie do RestFramgnet
            API.is_Token=false;
            Intent myIntent = new Intent(this,LoginActivity.class);
            this.startActivity(myIntent);//to jest wazne
        } else if (id == R.id.nav_tools) {




        } else if (id == R.id.nav_share) {





        } else if (id == R.id.nav_send) {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
