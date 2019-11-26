package pl.parking.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;
import com.magnet.android.mms.async.Call;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import pl.parking.app.MainActivity;
import pl.parking.controller.api.ApiStaticData;
import pl.parking.model.ParkingReservationResult;
import pl.parking.model.ParkingsResult;
import pl.parking.view.ParkingHistoryArrayAdapter;

public class ParkingHistory extends ListFragment {

    public static ArrayList<ParkingReservationResult> reservationList = new ArrayList<>();
    public final String URL = "https://polar-plains-14145.herokuapp.com/allocations";
    final String TAG = "ParkingHistory";
    List<Address> geocodeMatches = null;
    Geocoder gc;
    String Address1;
    String Address2;
    String Address3;

    public ParkingHistory() {}

    public Date parsing(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        boolean success = false;
        try {
            GetParkingReservation obj = new GetParkingReservation();
            obj.execute();
            Log.i(TAG, "onViewCreated: View has been created");
            success = true;

        } catch (Exception e) {
            Log.e(TAG, "onViewCreated: " + e.getMessage());
        } finally {
            if (!success) {

                Toast.makeText(getActivity(), "Could not load parking history", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Toast.makeText(getActivity(), " selected", Toast.LENGTH_LONG).show();
    }

    public class GetParkingReservation extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {

                if (ApiStaticData.userid.equals("0")) {
                    return "1";
                }
                String out =
                        new Scanner(new URL(URL).openStream(), "UTF-8").useDelimiter("\\A").next();
                Log.i(TAG, "doInBackground" + out);
                return out;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                return "0";
            }
        }

        @Override
        protected void onPostExecute(String out) {

            if (out.equals("1")) {
                Toast.makeText(
                                getActivity(),
                                "You must log in to check your reservations",
                                Toast.LENGTH_SHORT)
                        .show();

                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(myIntent);

                return;
            }

            if (out.equals("0")) {
                Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_SHORT).show();
                return;

            } else {
                Log.i(TAG, "Pobrany URL" + out);

                try {

                    Call<List<ParkingsResult>> callObject =
                            MainActivity.Parking_list.getParkings(null);
                    List<ParkingsResult> result = callObject.get();
                    gc = new Geocoder(getActivity());

                    JSONArray jsonarray = new JSONArray(out);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        ParkingReservationResult obj = new ParkingReservationResult();
                        obj.setiD_uzytkownika(jsonobject.getString("ID_uzytkownika"));

                        if (obj.getID_uzytkownika().equals(ApiStaticData.userid)) {

                            for (int ii = 0; ii < result.size(); ii++) {
                                Log.i(TAG, "Checking results: " + result.get(ii).getID());
                                Log.i(
                                        TAG,
                                        "Checking results from json: "
                                                + jsonobject.getString("ID_parkingu"));
                                if (result.get(ii)
                                        .getID()
                                        .toString()
                                        .equals(jsonobject.getString("ID_parkingu"))) {
                                    Log.i(TAG, "Checking ID matching: " + result.get(ii).getID());
                                    Address1 = result.get(ii).getMiasto();
                                    Address2 = result.get(ii).getUlica();
                                    Address3 = result.get(ii).getNr_ulicy().toString();
                                } else continue;
                            }
                            Log.i(TAG, "onPostExecute: check equaling users");
                            obj.setAdres(Address2 + " " + Address1 + " " + Address3);
                            obj.setData_reserwacji(
                                    parsing(
                                            jsonobject.getString("Data_reserwacji"),
                                            "yyyy-MM-dd HH:mm:ss"));
                            obj.setNumer_rejestracji(
                                    "Registration number: "
                                            + jsonobject.getString("Numer_rejestracji"));
                            obj.setKod_dostepu(
                                    "Access code: " + jsonobject.getString("Kod_dostepu"));
                            obj.setOd(parsing(jsonobject.getString("Od"), "yyyy-MM-dd HH:mm:ss"));
                            obj.setTheDo(
                                    parsing(jsonobject.getString("Do"), "yyyy-MM-dd HH:mm:ss"));
                            reservationList.add(obj);
                            Log.i(
                                    TAG,
                                    "Lista pobranych obiektow "
                                            + jsonobject.getString("ID_miejsca")
                                            + " "
                                            + jsonobject.getString("Kod_dostepu")
                                            + " "
                                            + jsonobject.getString("ID")
                                            + "\n");
                            Log.i(
                                    TAG,
                                    "Sprawdzenie zawartosci listy"
                                            + reservationList.get(i).getKod_dostepu()
                                            + "\n");

                        } else {
                            continue;
                        }
                    }
                    if (reservationList.isEmpty()) {
                        Toast.makeText(
                                        getActivity(),
                                        "You do not have any reservations",
                                        Toast.LENGTH_LONG)
                                .show();

                        return;
                    } else {
                        Log.i(
                                TAG,
                                "onPostExecute: Ending checking final array:"
                                        + reservationList.get(0).getKod_dostepu());

                        Collections.sort(
                                reservationList,
                                new Comparator<ParkingReservationResult>() {

                                    @Override
                                    public int compare(
                                            ParkingReservationResult o1,
                                            ParkingReservationResult o2) {
                                        return o1.getOd().compareTo(o2.getOd());
                                    }
                                });

                        ParkingHistoryArrayAdapter adapter =
                                new ParkingHistoryArrayAdapter(getActivity(), reservationList);
                        setListAdapter(adapter);
                        Log.i(TAG, "onPostExecute: Success");
                        return;
                    }

                } catch (Exception e) {
                    Log.e(TAG, "onPostExecute" + e.getMessage());
                }
            }
        }
    }
}
