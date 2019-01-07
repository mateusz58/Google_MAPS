package com.compscitutorials.basigarcia.navigationdrawervideotutorial;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans._ParkingsResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.vision.barcode.Barcode;
import com.magnet.android.mms.async.Call;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class Mapfragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,View.OnClickListener,SearchView.OnQueryTextListener,GoogleMap.OnMarkerClickListener {

    private final String TAG = "Mapfragment";
    Geocoder coder;
    //Barcode.GeoPoint p;
    private double latitude;
    private double longitude;
    MapView mapView;
    Marker marker;
    Vector<Marker> markerList = new Vector<>();
    GoogleMap map;
    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    private Button btnShowLocation;



    private void getParkings() throws Exception {
        Marker temp;
        Call<List<_ParkingsResult>> callObject = MainActivity.Parking_list.get_Parkings(null);
        List<_ParkingsResult> result = callObject.get();
        Geocoder gc = new Geocoder(getActivity());
        Log.i(TAG, "getParkings:started");
        for (int i = 1; i < result.size(); i++) {


            Log.i(TAG, "getParkings X:"+result.get(i).getX().toString());
            Log.i(TAG, "getParkings ilosc miejsc:"+result.get(i).getIloscmiejsc().toString());


            String miasto=result.get(i).getMiasto();
            String Ulica=result.get(i).getUlica();
            String nr_ulicty=result.get(i).getNr_ulicy().toString();


            if (result.get(i).getIloscmiejsc().equals(0)) {

                MarkerOptions userIndicator = new MarkerOptions()
                        .position(new LatLng((result.get(i).getX()), result.get(i).getY())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).title(miasto+" "+Ulica+" "+nr_ulicty+" "+getResources().getString(R.string.free_places)+":"+result.get(i).getIloscmiejsc().toString());
                temp = map.addMarker(userIndicator);
                markerList.add(temp);
            }
            else {
                MarkerOptions userIndicator = new MarkerOptions()
                        .position(new LatLng((result.get(i).getX()), result.get(i).getY())).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)).title(miasto + " " + Ulica + " " + nr_ulicty + " " + getResources().getString(R.string.free_places) + ":" + result.get(i).getIloscmiejsc().toString());
                temp = map.addMarker(userIndicator);
                markerList.add(temp);

            }

        }
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {


                LatLng latLon = marker.getPosition();

                Log.i(TAG, "onInfoWindowClick: TRUE");

                //Cycle through places array
                for (Marker place : markerList) {
                    if (latLon.equals(place.getPosition())) {
                        Log.i(TAG, "onInfoWindowClick: ITERATION" + place.getTitle());

                        Intent intent = new Intent(getActivity(), Parkingreservation.class);
                        Parkingreservation.startActivity(getActivity(), place.getTitle());


                    }

                }
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // What i have added is this

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //inflater.inflate(R.menu.main, menu); // removed to not double the menu items
        Log.i(TAG, "onCreateOptionsMenu: started");
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(this);
        sv.setIconifiedByDefault(false);
        sv.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onCreateOptionsMenu: Clicked: " + view);
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                //    Utils.LogDebug("Closed: ");
                Log.i(TAG, "onMenuItemActionCollapse:"+item);
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                //  Utils.LogDebug("Openeed: ");
                Log.i(TAG, "onMenuItemActionExpand: "+item);
                return true;  // Return true to expand action view
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, "onQueryTextSubmit: " + query);
        //Toast.makeText(getActivity(), "Testing instatnt run1", Toast.LENGTH_SHORT).show();

        Geocoder gc = new Geocoder(getContext());
        List<Address> list = null;
        Log.i(TAG, "onQueryTextSubmit:status ");

        try {
            list = gc.getFromLocationName(query, 1);
            Address add = list.get(0);
            String locality = add.getLocality();
            Toast.makeText(getActivity(), locality, Toast.LENGTH_SHORT).show();
            this.latitude = add.getLatitude();
            this.longitude = add.getLongitude();
            Log.i(TAG, "onQueryTextSubmit: "+latitude+""+longitude);
            displayLocation(longitude, latitude);

            return true;
        } catch (IOException e) {
            Toast.makeText(getActivity(), "No Results Found", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onQueryTextSubmit: "+latitude+""+longitude);
        }

        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "onQueryTextChange: " + newText);


        return false;
    }

    private void displayLocation(double longitude, double latitude) {

        //marker.remove();
        Log.i(TAG, "displayLocation: longitude:"+longitude+"latitude"+latitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 20);
        map.animateCamera(cameraUpdate);

        marker = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
        marker.setVisible(true);


        //Toast.makeText(getActivity(), latitude + ", " + longitude, Toast.LENGTH_SHORT).show();


    }

    private void display_My_Location() {

        Log.i(TAG, "display_My_Location: longitude:"+longitude+"latitude"+latitude);
        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

      Location a;




        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();


            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 9);
            map.animateCamera(cameraUpdate);

            marker = map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude)));
            marker.setVisible(true);

            Toast.makeText(getActivity(), latitude + ", " + longitude, Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(getActivity(), "(Couldn't get your localization. Make sure GPS is enabled on the device)", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_main, container, false);
        // Gets the MapView from the XML layout and creates it
        MapsInitializer.initialize(getActivity());
        btnShowLocation = (Button) v.findViewById(R.id.btnShowLocation);
        btnShowLocation.setOnClickListener(this);

        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
            case ConnectionResult.SUCCESS:
                //Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onCreateView: SUCCESS");
                mapView = (MapView) v.findViewById(R.id.map);
                mapView.onCreate(savedInstanceState);
                // Gets to GoogleMap from the MapView and does initialization stuff
                if (mapView != null) {
                    map = mapView.getMap();
                    map.getUiSettings().setMyLocationButtonEnabled(false);
                    map.getUiSettings().setZoomControlsEnabled(true);
                    //map.setMyLocationEnabled(true);
                    // map.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);
                    //map.setOnInfoWindowClickListener(this);


                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
        }
        // Updates the location and zoom of the MapView

        // LatLng sydney = new LatLng(-33.867, 151.206);

        //   map.setMyLocationEnabled(true);
        // map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        // map.addMarker(new MarkerOptions()
        //      .title("Sydney")
        //      .snippet("The most populous city in Australia.")
        //    .position(sydney));

        //  map.addMarker(new MarkerOptions()
        //        .position(new LatLng(10, 10))
        //      .title("Hello worl"));

        return v;

    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);




        //  mGoogleApiClient = new GoogleApiClient
        //          .Builder(getActivity())
        //          .addApi(Places.GEO_DATA_API)
        //          .addApi(Places.PLACE_DETECTION_API)
        //          .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
        //          .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(LocationServices.API).build();

            marker = map.addMarker(new MarkerOptions()
                  .position(new LatLng(100, 100)));

            marker.setVisible(false);




    }

    @Override
    public void onResume() {
        mapView.onResume();

        super.onResume();

        try {
            getParkings();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "onResume:Success ");


    }

    @Override
    public void onPause() {

        super.onPause();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        mapView.onLowMemory();
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStartmethod started");

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
            Log.i(TAG, "Connected with mGoogleAPiClient");


        }
        if (mGoogleApiClient == null) {

            Log.i(TAG, "Failed to connect with mGoogleAPIclient");

        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed:" + result.getErrorMessage());
        Toast.makeText(getActivity(),R.string.internet_error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnected(Bundle arg0) {

        Log.i(TAG, "onConnected:Connection success");

        display_My_Location();

    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {
                case R.id.btnShowLocation:
                    Log.i(TAG, "onClick:Connection success");
                    marker.remove();
                    display_My_Location();
                    break;

            }
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {


        Log.i(TAG, "onMarkerClick: infowindow in not show");

        return true;

    }

}







