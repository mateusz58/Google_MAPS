package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.TEMP.Parking_reservationsResult;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.TEMP._ParkingsResult;
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
public class Parkinghistory extends ListFragment{

///Trashes
    public static ArrayList<Parking_reservationsResult> Reservations_List=new ArrayList<>();

    List<Address> geocodeMatches = null;
    Geocoder gc;
    String Address1;
    String Address2;
    String Address3;
//    public static ArrayList<Parking_reservationsResult> Reservations_List=new ArrayList<>();

    final String TAG="Parkinghistory";

    final String URL="https://polar-plains-14145.herokuapp.com/allocations";
    public Parkinghistory() {
        // Required empty public constructor
    }

    public Date parsing(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(date);
    }

 public class GetParking_reservation extends AsyncTask<Void, Void, String> {
        @Override
        protected  String doInBackground(Void... params) {
            try {

                if(API.userid.equals("0"))
                {
                   return "1";

                }

                String out = new Scanner(new URL(URL).openStream(), "UTF-8").useDelimiter("\\A").next();
                Log.i(TAG,"doInBackground"+out);


                return out;
            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
                return "0";
            }
        }


        @Override
        protected void onPostExecute(String out) {


//            ArrayList<Parking_reservationsResult> Reservations_List=new ArrayList<>();

            if (out.equals("1")) {
                Toast.makeText(getActivity(), "You must log in to check your reservations" ,Toast.LENGTH_SHORT).show();


                Intent myIntent = new Intent(getActivity(),MainActivity.class);
                getActivity().startActivity(myIntent);//to jest wazne



                return;
            }

            if (out.equals("0")) {
                Toast.makeText(getActivity(), R.string.internet_error, Toast.LENGTH_SHORT).show();
                return;


            } else {
                Log.i(TAG, "Pobrany URL" + out);




                try {

                    Call<List<_ParkingsResult>> callObject = MainActivity.Parking_list.get_Parkings(null);
                    List<_ParkingsResult> result = callObject.get();
       gc = new Geocoder(getActivity());

                    //parkinglist=new Vector<Parking>();
                    JSONArray jsonarray = new JSONArray(out);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        Parking_reservationsResult obj = new Parking_reservationsResult();
                        obj.setiD_uzytkownika(jsonobject.getString("ID_uzytkownika"));


                        if (obj.getID_uzytkownika().equals(API.userid)) {

                            for (int ii = 0; ii <result.size() ; ii++) {
                                Log.i(TAG, "Checking results: "+result.get(ii).getID());
                                Log.i(TAG, "Checking results from json: "+jsonobject.getString("ID_parkingu"));
                    if(result.get(ii).getID().toString().equals(jsonobject.getString("ID_parkingu")))
                                {
                                    Log.i(TAG, "Checking ID matching: "+result.get(ii).getID());
                                    Address1 = result.get(ii).getMiasto();
                                    Address2 = result.get(ii).getUlica();
                                    Address3 = result.get(ii).getNr_ulicy().toString();
                                }
                                else
                        continue;
                            }
                            Log.i(TAG, "onPostExecute: check equaling users");
                            obj.setAdres(Address2+" "+Address1+" "+Address3);
                            obj.setData_reserwacji(parsing(jsonobject.getString("Data_reserwacji"),"yyyy-MM-dd HH:mm:ss"));
                            obj.setNumer_rejestracji("Registration number: "+jsonobject.getString("Numer_rejestracji"));
                            obj.setKod_dostepu("Access code: "+jsonobject.getString("Kod_dostepu"));
                            obj.setOd(parsing(jsonobject.getString("Od"),"yyyy-MM-dd HH:mm:ss"));
                            obj.setTheDo(parsing(jsonobject.getString("Do"),"yyyy-MM-dd HH:mm:ss"));
                            Reservations_List.add(obj);
                            Log.i(TAG, "Lista pobranych obiektow " + jsonobject.getString("ID_miejsca") + " " + jsonobject.getString("Kod_dostepu") + " " + jsonobject.getString("ID") + "\n");
                            Log.i(TAG, "Sprawdzenie zawartosci listy" + Reservations_List.get(i).getKod_dostepu() + "\n");
                            //Parkinghistory.Reservations_List=Reservations_List;
                       }
                        else
                        {
                            continue;
                        }

                    }
                   if(Reservations_List.isEmpty())
                   {
                       Toast.makeText(getActivity(), "You do not have any reservations", Toast.LENGTH_LONG).show();

                       return;
                   }
              else {
                       Log.i(TAG, "onPostExecute: Ending checking final array:" + Reservations_List.get(0).getKod_dostepu());


                       Collections.sort(Reservations_List, new Comparator<Parking_reservationsResult>(){

                           @Override
                           public int compare(Parking_reservationsResult o1, Parking_reservationsResult o2) {
                               return o1.getOd().compareTo(o2.getOd());
                           }
                       });



                       Custom_Array_Adapter_for_Parking_History adapter = new Custom_Array_Adapter_for_Parking_History(getActivity(), Reservations_List);
                       setListAdapter(adapter);
                       Log.i(TAG, "onPostExecute: Success");
                       return;
                   }

                } catch (Exception e) {
                    Log.e(TAG,"onPostExecute"+ e.getMessage());
                }

            }
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        boolean success = false;
        try {
            GetParking_reservation obj=new GetParking_reservation();
        obj.execute();
            Log.i(TAG, "onViewCreated: View has been created");
            success = true;
//           ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, copyArr);
//           setListAdapter(adapter);
        } catch (Exception e) {
            Log.e(TAG, "onViewCreated: " + e.getMessage());
        } finally {
            if (!success) {

                Toast.makeText(getActivity(), "Could not load parking history", Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       // String item = (String) getListAdapter().getItem(position);
        Toast.makeText(getActivity()," selected", Toast.LENGTH_LONG).show();
   }

}

