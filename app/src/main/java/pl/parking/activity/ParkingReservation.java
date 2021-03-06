package pl.parking.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import pl.parking.controller.api.ApiController;
import pl.parking.controller.reponses.errorResponses.ErrorResponseBooking;
import pl.parking.controller.reponses.errorResponses.ErrorUtils;
import pl.parking.model.Booking;
import pl.parking.model.RegistrationPlate;
import pl.parking.service.ParkingService;
import pl.parking.view.RegistrationPlateListAdapter;
import retrofit2.Call;
import retrofit2.Response;

public class ParkingReservation extends AppCompatActivity implements View.OnClickListener {

    private static final String parkingIDparam = "ParkingID";

    final String TAG = "ParkingReservation";
    ListView listView;
    EditText editTextView;

    ArrayList<RegistrationPlate> itemRegistrationplateList;
    RegistrationPlateListAdapter registrationplatelistadapter;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button btnreserve;
    Button btnTimePicker;
    Button btnDatePicker;
    EditText txtDate_start_formatted;
    EditText txtDate_end_formatted;
    EditText txtTime_start_formatted;
    EditText txtTime_end_formatted;
    String Date_start;
    String Date_end;
    EditText reserve;
    Calendar start;
    Date START = Calendar.getInstance().getTime();
    Date END = Calendar.getInstance().getTime();
    private String parkingID;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String mParam1;
    private String mParam2;

    public ParkingReservation() {}

    public static void startActivity(Context context, String ParkingID) {

        Bundle extras = new Bundle();
        extras.putString(parkingIDparam, ParkingID);

        Intent intent = new Intent(context, ParkingReservation.class);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    private String convert_time_format(String input) throws ParseException {
        String pattern = "EEEEE dd MMMMM yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "PL"));

        Date date = simpleDateFormat.parse(input);
        return date.toString();
    }

    boolean validate_reservation_number(EditText reserve) {

        if (txtDate_start_formatted.equals(null)) {
            return false;
        }

        if (txtDate_end_formatted.equals(null)) {
            return false;
        }

        if (txtTime_start_formatted.equals(null)) {

            return false;
        }

        if (txtTime_end_formatted.equals(null)) {

            return false;
        }

        if (reserve.getText().length() < 6) {
            reserve.setError(getResources().getString(R.string.validate_reservation_number));
            return false;
        }
        if (reserve.getText().length() > 9) {
            reserve.setError(getResources().getString(R.string.validate_reservation_number));
            return false;

        } else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parkingreservation_v2);

        Bundle extras = getIntent().getExtras();
        parkingID = extras.getString(parkingIDparam);
        btnDatePicker = (Button) findViewById(R.id.btn_end);
        btnTimePicker = (Button) findViewById(R.id.btn_start);
        btnreserve = (Button) findViewById(R.id.btn_reserve);
        txtDate_start_formatted = (EditText) findViewById(R.id.in_start_date);
        txtTime_start_formatted = (EditText) findViewById(R.id.in_start_time);
        reserve = (EditText) findViewById(R.id.in_reserve);
        txtDate_end_formatted = (EditText) findViewById(R.id.in_end_date);
        txtTime_end_formatted = (EditText) findViewById(R.id.in_end_time);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnreserve.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listview);
        editTextView = (EditText) findViewById(R.id.editTextView);
        itemRegistrationplateList = new ArrayList<RegistrationPlate>();
        registrationplatelistadapter =
                new RegistrationPlateListAdapter(
                        getApplicationContext(), itemRegistrationplateList);
        listView.setEmptyView(findViewById(android.R.id.empty));
        listView.setAdapter(registrationplatelistadapter);
    }

    @SuppressLint("NewApi")
    public void addValue(View v) {
        String name = editTextView.getText().toString();
        int size = editTextView.getText().toString().length();
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Pls enter Values", Toast.LENGTH_SHORT).show();
        }

        if (size < 6 || size > 10) {
            Toast.makeText(
                            getApplicationContext(),
                            "Registration plate car number must consist of at least 6 characters and maximum of 10 characters ",
                            Toast.LENGTH_LONG)
                    .show();
        }
        if (itemRegistrationplateList.contains(name)) {
            Toast.makeText(getApplicationContext(), R.string.plate_unique, Toast.LENGTH_LONG)
                    .show();
        } else {
            RegistrationPlate registrationPlate = new RegistrationPlate(name);
            itemRegistrationplateList.add(registrationPlate);
            registrationplatelistadapter.notifyDataSetChanged();
            editTextView.setText("");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_start:
                {
                    Log.i(TAG, "onClick: btn_start");

                    Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog(
                                    this,
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(
                                                DatePicker view,
                                                int year,
                                                int monthOfYear,
                                                int dayOfMonth) {

                                            monthOfYear = monthOfYear + 1;
                                            String dayOfMonth_string = String.valueOf(dayOfMonth);
                                            String monthOfYear_string = String.valueOf(monthOfYear);

                                            if (dayOfMonth_string.length() == 1) {
                                                dayOfMonth_string = "0" + dayOfMonth_string;
                                            }
                                            if (monthOfYear_string.length() == 1) {
                                                monthOfYear_string = "0" + monthOfYear_string;
                                            }

                                            txtDate_start_formatted.setText(
                                                    year
                                                            + "-"
                                                            + monthOfYear_string
                                                            + "-"
                                                            + dayOfMonth_string);
                                        }
                                    },
                                    mYear,
                                    mMonth,
                                    mDay);
                    START.setYear(mYear);
                    START.setMonth(mMonth);
                    START.setDate(mDay);
                    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    datePickerDialog.show();
                    c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog =
                            new TimePickerDialog(
                                    this,
                                    TimePickerDialog.THEME_HOLO_LIGHT,
                                    new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(
                                                TimePicker view, int hourOfDay, int minute) {

                                            String hourOfDay_string = String.valueOf(hourOfDay);
                                            String minute_string = String.valueOf(minute);

                                            if (hourOfDay_string.length() == 1) {
                                                hourOfDay_string = "0" + hourOfDay_string;
                                            }
                                            if (minute_string.length() == 1) {
                                                minute_string = "0" + minute_string;
                                            }

                                            txtTime_start_formatted.setText(
                                                    hourOfDay_string + ":" + minute_string);
                                        }
                                    },
                                    mHour,
                                    mMinute,
                                    true);
                    START.setHours(mHour);
                    START.setMinutes(mMinute);

                    Log.i(
                            TAG,
                            "onClick:"
                                    + mHour
                                    + " "
                                    + mMinute
                                    + " "
                                    + mYear
                                    + " "
                                    + mMonth
                                    + " "
                                    + mDay
                                    + "\n");

                    Log.i(
                            TAG,
                            "Checking calendar btn_start:"
                                    + START.getDay()
                                    + " "
                                    + START.getMonth()
                                    + " "
                                    + START.getYear()
                                    + " "
                                    + START.getHours()
                                    + " "
                                    + START.getMinutes()
                                    + " "
                                    + START.getSeconds());

                    start = Calendar.getInstance();
                    start.setTime(START);
                    timePickerDialog.show();
                }
                break;

            case R.id.btn_end:
                {
                    Log.i(TAG, "onClick: btn_end");

                    Calendar border = Calendar.getInstance();

                    border.set(START.getYear(), START.getMonth(), START.getDay());

                    Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    Log.i(
                            TAG,
                            "Checking calendar btn_end:"
                                    + START.getDay()
                                    + " "
                                    + START.getMonth()
                                    + " "
                                    + START.getYear()
                                    + " "
                                    + START.getHours()
                                    + " "
                                    + START.getMinutes()
                                    + " "
                                    + START.getSeconds());
                    Log.i(TAG, "Checking  time_ in mili for border:" + border.getTimeInMillis());
                    Log.i(TAG, "Checking  time_ in mili for c:" + c.getTimeInMillis());

                    long difference = c.getTimeInMillis() - border.getTimeInMillis();

                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog(
                                    this,
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(
                                                DatePicker view,
                                                int year,
                                                int monthOfYear,
                                                int dayOfMonth) {

                                            monthOfYear = monthOfYear + 1;
                                            String dayOfMonth_string = String.valueOf(dayOfMonth);
                                            String monthOfYear_string = String.valueOf(monthOfYear);

                                            if (dayOfMonth_string.length() == 1) {
                                                dayOfMonth_string = "0" + dayOfMonth_string;
                                            }
                                            if (monthOfYear_string.length() == 1) {
                                                monthOfYear_string = "0" + monthOfYear_string;
                                            }

                                            txtDate_end_formatted.setText(
                                                    year
                                                            + "-"
                                                            + monthOfYear_string
                                                            + "-"
                                                            + dayOfMonth_string);
                                        }
                                    },
                                    mYear,
                                    mMonth,
                                    mDay);
                    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                    datePickerDialog.show();

                    c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog =
                            new TimePickerDialog(
                                    this,
                                    new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(
                                                TimePicker view, int hourOfDay, int minute) {

                                            String hourOfDay_string = String.valueOf(hourOfDay);
                                            String minute_string = String.valueOf(minute);
                                            if (hourOfDay_string.length() == 1) {
                                                hourOfDay_string = "0" + hourOfDay_string;
                                            }
                                            if (minute_string.length() == 1) {
                                                minute_string = "0" + minute_string;
                                            }

                                            txtTime_end_formatted.setText(
                                                    hourOfDay_string + ":" + minute_string);
                                        }
                                    },
                                    mHour,
                                    mMinute,
                                    true);
                    timePickerDialog.show();
                }
                break;

            case R.id.btn_reserve:
                {
                    if (itemRegistrationplateList.size() < 1) {
                        Toast.makeText(
                                        ParkingReservation.this,
                                        "You cannot register parking place for less than one car",
                                        Toast.LENGTH_SHORT)
                                .show();
                        break;
                    } else {
                        new RegisterTask().execute();
                    }
                }
        }
    }

    public class RegisterTask extends AsyncTask<Void, Void, Boolean> {

        String Date_From =
                txtDate_start_formatted.getText().toString()
                        + "T"
                        + txtTime_start_formatted.getText().toString()
                        + ":00Z";
        String Date_To =
                txtDate_end_formatted.getText().toString()
                        + "T"
                        + txtTime_end_formatted.getText().toString()
                        + ":00Z";

        SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
        String Token = prefs.getString("Token", null);
        String user_id = prefs.getString("User_id", null);
        int size = itemRegistrationplateList.size();
        int userid2 = Integer.parseInt(user_id);
        int parkingID2 = Integer.parseInt(parkingID);
        String size2 = String.valueOf(size);

        String date_from_test = "2019-02-21T16:46:19Z";
        String date_to_test = "2019-02-23T16:46:19Z";

        private String response_message;

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean result = false;

            try {
                String registration_plate = "";
                for (int i = 0; i < itemRegistrationplateList.size(); i++) {
                    if (i == 0) {
                        registration_plate =
                                registration_plate + itemRegistrationplateList.get(i).getName();
                    } else {
                        registration_plate =
                                registration_plate
                                        + ","
                                        + itemRegistrationplateList.get(i).getName();
                    }
                }
                ApiController apiEndpoints =
                        ParkingService.getRetrofitInstance().create(ApiController.class);
                Call<Booking> call =
                        apiEndpoints.postBooking(
                                Token,
                                Date_From,
                                Date_To,
                                registration_plate,
                                parkingID2,
                                userid2,
                                size);
                Response<Booking> response = call.execute();
                if (response.isSuccessful()) {
                    result = true;

                } else {
                    ErrorResponseBooking error_response_booking =
                            ErrorUtils.parse_Error_Booking(response);

                    response_message = error_response_booking.getErrorMessage();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                return result;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {

                DialogInterface.OnClickListener dialogClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_NEUTRAL:
                                        break;
                                }
                            }
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(ParkingReservation.this);
                builder.setMessage(R.string.Reservation_success)
                        .setNeutralButton("Ok", dialogClickListener)
                        .show();

            } else {
                Log.i("RESPONSE", "Unsuccessful login");
                DialogInterface.OnClickListener dialogClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_NEUTRAL:
                                        break;
                                }
                            }
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(ParkingReservation.this);
                builder.setMessage(response_message)
                        .setNeutralButton("Ok", dialogClickListener)
                        .show();
            }
        }
    }
}
