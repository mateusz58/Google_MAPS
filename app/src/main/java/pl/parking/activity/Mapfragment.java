package pl.parking.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
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

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import pl.parking.app.MainActivity;
import pl.parking.controller.api.ApiStaticData;

public class Mapfragment extends Fragment
        implements GoogleApiClient.ConnectionCallbacks,
                GoogleApiClient.OnConnectionFailedListener,
                View.OnClickListener,
                SearchView.OnQueryTextListener,
                GoogleMap.OnMarkerClickListener {

    private final String TAG = "Mapfragment";
    Geocoder coder;
    MapView mapView;
    Marker marker;
    Vector<Marker> markerList = new Vector<>();
    GoogleMap map;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    private double latitude;
    private double longitude;
    private Button btnShowLocation;

    private void getParkings() throws Exception {
        Marker temp;

        Geocoder gc = new Geocoder(getActivity());
        Log.i(TAG, "getParkings:started");
        for (int i = 0; i < MainActivity.parkingList.size(); i++) {

            Log.i(TAG, "getParkings X:" + MainActivity.parkingList.get(i).getX().toString() + "\n");
            Log.i(TAG, "getParkings Y:" + MainActivity.parkingList.get(i).getY().toString() + "\n");
            Log.i(
                    TAG,
                    "getParkings ilosc miejsc:"
                            + MainActivity.parkingList.get(i).getFreePlaces().toString()
                            + "\n");
            String miasto = MainActivity.parkingList.get(i).getParkingCity();
            String Ulica = MainActivity.parkingList.get(i).getParkingStreet();
            Double cena = MainActivity.parkingList.get(i).getHourCost();

            if (MainActivity.parkingList.get(i).getFreePlaces().equals(0)) {

                MarkerOptions userIndicator =
                        new MarkerOptions()
                                .position(
                                        new LatLng(
                                                (MainActivity.parkingList.get(i).getX()),
                                                MainActivity.parkingList.get(i).getY()))
                                .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_YELLOW))
                                .title(
                                        "City"
                                                + miasto
                                                + "\nStreet:"
                                                + Ulica
                                                + "\n Hour prize: "
                                                + cena.toString()
                                                + "\n "
                                                + getResources().getString(R.string.free_places)
                                                + ""
                                                + MainActivity.parkingList
                                                        .get(i)
                                                        .getFreePlaces()
                                                        .toString());
                temp = map.addMarker(userIndicator);
                markerList.add(temp);
            } else {
                MarkerOptions userIndicator =
                        new MarkerOptions()
                                .position(
                                        new LatLng(
                                                (MainActivity.parkingList.get(i).getX()),
                                                MainActivity.parkingList.get(i).getY()))
                                .icon(
                                        BitmapDescriptorFactory.defaultMarker(
                                                BitmapDescriptorFactory.HUE_AZURE))
                                .title(
                                        "City: "
                                                + miasto
                                                + "\nStreet: "
                                                + Ulica
                                                + "\n Hour prize:"
                                                + cena.toString()
                                                + "\n "
                                                + getResources().getString(R.string.free_places)
                                                + ""
                                                + MainActivity.parkingList
                                                        .get(i)
                                                        .getFreePlaces()
                                                        .toString());

                userIndicator.snippet(MainActivity.parkingList.get(i).getId().toString());
                temp = map.addMarker(userIndicator);

                markerList.add(temp);
            }
        }
        map.setInfoWindowAdapter(
                new GoogleMap.InfoWindowAdapter() {

                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        LinearLayout info = new LinearLayout(getContext());
                        info.setOrientation(LinearLayout.VERTICAL);

                        TextView title = new TextView(getContext());
                        title.setTextColor(Color.BLACK);
                        title.setGravity(Gravity.CENTER);
                        title.setTypeface(null, Typeface.BOLD);
                        title.setText(marker.getTitle());

                        TextView snippet = new TextView(getContext());
                        snippet.setTextColor(Color.GRAY);
                        snippet.setText(marker.getSnippet());

                        info.addView(title);
                        info.addView(snippet);

                        return info;
                    }
                });
        map.setOnInfoWindowClickListener(
                new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        LatLng latLon = marker.getPosition();
                        Log.i(TAG, "onInfoWindowClick: TRUE");

                        for (Marker place : markerList) {
                            if (latLon.equals(place.getPosition())) {
                                Log.i(TAG, "onInfoWindowClick: ITERATION" + place.getTitle());
                                if (ApiStaticData.is_Token) {
                                    Intent intent =
                                            new Intent(getActivity(), ParkingReservation.class);
                                    ParkingReservation.startActivity(
                                            getActivity(), place.getSnippet());
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        Log.i(TAG, "onCreateOptionsMenu: started");
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv =
                new SearchView(
                        ((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(
                item,
                MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
                        | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(item, sv);
        sv.setOnQueryTextListener(this);
        sv.setIconifiedByDefault(false);
        sv.setOnSearchClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, "onCreateOptionsMenu: Clicked: " + view);
                    }
                });

        MenuItemCompat.setOnActionExpandListener(
                item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        Log.i(TAG, "onMenuItemActionCollapse:" + item);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {

                        Log.i(TAG, "onMenuItemActionExpand: " + item);
                        return true;
                    }
                });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i(TAG, "onQueryTextSubmit: " + query);

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
            Log.i(TAG, "onQueryTextSubmit: " + latitude + "" + longitude);
            displayLocation(longitude, latitude);

            return true;
        } catch (IOException e) {
            Toast.makeText(getActivity(), "No Results Found", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onQueryTextSubmit: " + latitude + "" + longitude);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i(TAG, "onQueryTextChange: " + newText);
        return false;
    }

    private void displayLocation(double longitude, double latitude) {
        Log.i(TAG, "displayLocation: longitude:" + longitude + "latitude" + latitude);

        CameraUpdate cameraUpdate =
                CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 20);
        map.animateCamera(cameraUpdate);

        marker = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
        marker.setVisible(true);
    }

    @SuppressLint("MissingPermission")
    private void display_My_Location() {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        Location a;

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            CameraUpdate cameraUpdate =
                    CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 9);
            map.animateCamera(cameraUpdate);

            marker = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)));
            marker.setVisible(true);

            Toast.makeText(getActivity(), latitude + ", " + longitude, Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(
                            getActivity(),
                            "(Couldn't get your localization. Make sure GPS is enabled on the device)",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        MapsInitializer.initialize(getActivity());
        btnShowLocation = (Button) v.findViewById(R.id.btnShowLocation);
        btnShowLocation.setOnClickListener(this);

        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
            case ConnectionResult.SUCCESS:
                Log.i(TAG, "onCreateView: SUCCESS");
                mapView = (MapView) v.findViewById(R.id.map);
                mapView.onCreate(savedInstanceState);

                if (mapView != null) {
                    map = mapView.getMap();

                    if (ContextCompat.checkSelfPermission(
                                            getContext(),
                                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                                    == PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(
                                            getContext(),
                                            android.Manifest.permission.ACCESS_COARSE_LOCATION)
                                    == PackageManager.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                        map.getUiSettings().setMyLocationButtonEnabled(true);
                    } else {
                        ActivityCompat.requestPermissions(
                                getActivity(),
                                new String[] {
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                },
                                4);
                    }
                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(getActivity(), "SERVICE MISSING", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getActivity(), "UPDATE REQUIRED", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(
                                getActivity(),
                                GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()),
                                Toast.LENGTH_SHORT)
                        .show();
        }
        return v;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        mGoogleApiClient =
                new GoogleApiClient.Builder(getActivity())
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .addApi(Places.GEO_DATA_API)
                        .addApi(Places.PLACE_DETECTION_API)
                        .addApi(LocationServices.API)
                        .build();

        marker = map.addMarker(new MarkerOptions().position(new LatLng(100, 100)));

        marker.setVisible(false);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

        try {

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

        try {
            getParkings();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(Bundle arg0) {

        Log.i(TAG, "onConnected:Connection success");

        try {
            display_My_Location();
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
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
