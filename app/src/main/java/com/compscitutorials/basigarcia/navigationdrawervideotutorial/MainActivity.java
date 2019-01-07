package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.FactoryAPI;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.FactoryAPIFactory;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans._ParkingsResult;
import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.async.Call;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private final String TAG="MainActivity";
    public static FactoryAPI Parking_list;


    NavigationView navigationView = null;

    TextView logintext=null;
    Toolbar toolbar = null;
    ImageButton floatButton;

    final int PERMISSION_READ_STATE=1;

    final String url="http://polar-plains-14145.herokuapp.com/parks_wsp";


    class GetParking extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            String out = "0";
            try {

//                Log.w(TAG, "doInBackground:Magnet mobile client initialization ");
//                MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(getApplicationContext());
//                FactoryAPIFactory controllerFactory = new FactoryAPIFactory(magnetClient);
//
//                Parking_list = controllerFactory.obtainInstance();
//                Call<List<_ParkingsResult>> callObject = MainActivity.Parking_list.get_Parkings(null);
//                List<_ParkingsResult> result = callObject.get();
//                out="1";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();



                return out;
            } catch (Exception e) {

                Log.e(TAG, e.getMessage(), e);
                Toast.makeText(MainActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
            } finally {

                Log.i(TAG, "doInBackground:out value"+out);


                return out;

            }
        }

        @Override
        protected void onPostExecute(String out) {

            if (out == "1") {

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


            } else {

                Toast.makeText(MainActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        logintext = (TextView)findViewById(R.id.nav_logout);

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
              if(!API.userid.equals("0")) {
                  // navigationView.getMenu().getItem(2).setChecked(true);
                  navigationView.getMenu().getItem(2).setIcon(R.drawable.ic_no_encryption_black_24dp);
                  navigationView.getMenu().getItem(2).setTitle("Log out");

              }
              else
              {

                  navigationView.getMenu().getItem(2).setIcon(R.drawable.ic_https_black_24dp);
                  navigationView.getMenu().getItem(2).setTitle("Sign in");


              }
              //new GetParking().execute();
              Mapfragment fragment = new Mapfragment();

              android.support.v4.app.FragmentTransaction fragmentTransaction =
                      getSupportFragmentManager().beginTransaction();
              fragmentTransaction.replace(R.id.fragment_container, fragment);
              fragmentTransaction.commit();

              getSupportActionBar().show();



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

            Mapfragment fragment = new Mapfragment();

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            getSupportActionBar().show();



        } else if (id == R.id.nav_parkingplace) {
            Log.w(TAG, "onNavigationItemSelected: Restartactivity");
            getSupportActionBar().hide();
            Parkinghistory fragment = new Parkinghistory();
            //fragment.newInstance("BLA","BLA1");
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_logout) {///przejscie do RestFramgnet
            API.userid="0";
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
