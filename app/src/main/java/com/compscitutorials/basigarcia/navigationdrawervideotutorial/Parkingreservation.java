package com.compscitutorials.basigarcia.navigationdrawervideotutorial;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Parkingreservation extends AppCompatActivity implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //time

    private static final String parkingIDparam = "ParkingID";

    private String parkingID;



   private int mYear, mMonth, mDay, mHour, mMinute;
    //testings
    final String TAG="Parkingreservation";
    private String mParam1;
    private String mParam2;
    Button btnreserve;
    Button btnTimePicker;
    Button btnDatePicker;
    EditText txtDate_start;
    EditText txtTime_start;
    EditText reserve;
    EditText txtDate_end;
    EditText txtTime_end;

     int SMONTH;
    int SYEAR;
    int SDAY;
    int SMINUTE;
    int SHOUR;
    int SSECOND;

    int KMONTH;
    int KYEAR;
    int KDAY;
    int KMINUTE;
    int KHOUR;
    int KSECOND;


    Calendar start;

Date START=Calendar.getInstance().getTime();
    Date END=Calendar.getInstance().getTime();



    public Parkingreservation() {
        // Required empty public constructor
    }

boolean validate_reservation_number(EditText reserve)
{

    if(txtDate_start.equals(null))
    {
        return false;
    }


    if(txtDate_end.equals(null))
    {
        return false;
    }

    if(txtTime_start.equals(null))
   {

       return false;
   }


    if(txtTime_end.equals(null))
    {

        return false;
    }

    if (reserve.getText().length() < 6)
    {
        reserve.setError(getResources().getString(R.string.validate_reservation_number));
        return false;

    }
    if (reserve.getText().length() > 9)
    {
        reserve.setError(getResources().getString(R.string.validate_reservation_number));
  return false;

    }
   else
    {
      return true;
    }


}
    public static void startActivity(Context context, String ParkingID) {
        // Build extras with passed in parameters
        Bundle extras = new Bundle();
        extras.putString(parkingIDparam, ParkingID);
        // Create and start intent for this activity
        Intent intent = new Intent(context, Parkingreservation.class);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parkingreservation);

        Bundle extras = getIntent().getExtras();
        parkingID=extras.getString(parkingIDparam);
        btnDatePicker=(Button)findViewById(R.id.btn_end);
        btnTimePicker=(Button)findViewById(R.id.btn_start);
        btnreserve=(Button)findViewById(R.id.btn_reserve);
        txtDate_start =(EditText)findViewById(R.id.in_start_date);
        txtTime_start =(EditText)findViewById(R.id.in_start_time);
        reserve =(EditText)findViewById(R.id.in_reserve);


        txtDate_end =(EditText)findViewById(R.id.in_end_date);
        txtTime_end =(EditText)findViewById(R.id.in_end_time);

        btnreserve.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {


                case R.id.btn_start:///start

                {
                    Log.i(TAG, "onClick: btn_start");


                    Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

///Launch date and time picker
                    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txtDate_start.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    START.setYear(mYear);
                    START.setMonth(mMonth);
                    START.setDate(mDay);

                    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    datePickerDialog.show();

                    c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);


                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txtTime_start.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    START.setHours(mHour);
                    START.setMinutes(mMinute);
                   // start.setTime(START);
                    Log.i(TAG, "onClick:"+ mHour+" "+mMinute+" "+mYear+" "+mMonth+" "+mDay+"\n");

                    Log.i(TAG, "Checking calendar btn_start:"+ START.getDay()+" "+START.getMonth()+" "+START.getYear()+" "+START.getHours()+" "+START.getMinutes()+" "+START.getSeconds());

                    start = Calendar.getInstance();
                    start.setTime(START);
                    timePickerDialog.show();
                }
                break;



                case R.id.btn_end: { ////end


                    Log.i(TAG, "onClick: btn_end");

                    //END=date;
                Calendar border=Calendar.getInstance();




                  border.set(START.getYear(),START.getMonth(),START.getDay());

                    Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    Log.i(TAG, "Checking calendar btn_end:"+ START.getDay()+" "+START.getMonth()+" "+START.getYear()+" "+START.getHours()+" "+START.getMinutes()+" "+START.getSeconds());
                    Log.i(TAG, "Checking  time_ in mili for border:"+border.getTimeInMillis());
                    Log.i(TAG, "Checking  time_ in mili for c:"+c.getTimeInMillis());

                    long difference=c.getTimeInMillis()-border.getTimeInMillis();


                    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txtDate_end.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    datePickerDialog.show();

                    c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txtTime_end.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
                    break;


                case R.id.btn_reserve:///start
                {
                    //dodanie

                    if (!validate_reservation_number(reserve)) {

                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_NEUTRAL:
                                        txtTime_start.setText("");
                                        txtTime_end.setText("");
                                        txtDate_start.setText("");
                                        txtDate_end.setText("");
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                        builder.setMessage("Wrong data ").setPositiveButton(R.string.yes, dialogClickListener)
                                .setNegativeButton(R.string.no, dialogClickListener).show();


                    }

                    if(LoginActivity.token=="none")
                    {
                        Toast.makeText(Parkingreservation.this, "Login to reserve parking", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(this,MainActivity.class);
                        this.startActivity(myIntent);//to jest wazne

                    }

                    else {
                        // TODO: 19.05.16 Rezerwacja parkingu
                        Log.i(TAG, "onClick: Reservation click test");
                        Log.i(TAG, "onClick: check user login"+API.userid);
                        Log.i(TAG, "onClick: check parking ID"+parkingID);
                        //region proces of postdata sending
                        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
                        String url = "https://polar-plains-14145.herokuapp.com/reservation/add";
                        StringRequest MyStringRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i(TAG,"Response is: "+response);
                                Toast.makeText(Parkingreservation.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e(TAG,"Response is: "+error.getMessage());
                                Toast.makeText(Parkingreservation.this, R.string.internet_error, Toast.LENGTH_SHORT).show();

                            }
                        }) {
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("idpark",parkingID);
                                params.put("idplace","1");
                                params.put("nrregister",reserve.getText().toString());
                                params.put("iduser",API.userid);
                                params.put("from",txtDate_start.getText().toString()+"T"+txtTime_start.getText().toString());
                                params.put("to",txtDate_end.getText().toString()+"T"+txtTime_end.getText().toString()); //Add the data you'd like to send to the server.
                                return params;
                            }
                        };

                        MyRequestQueue.add(MyStringRequest);

                        //endregion

                       Toast.makeText(this, R.string.Reservation_success, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onClick: MyRequestQueue: "+MyRequestQueue.getSequenceNumber());
                        Intent myIntent = new Intent(this,MainActivity.class);
                        this.startActivity(myIntent);//to jest wazne
//
                    }
                }
                break;

            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

    }

}
