package pl.parking.view.RecyclerListCars;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.parking.controller.api.ApiController;
import pl.parking.controller.api.ApiStaticData;
import pl.parking.controller.reponses.errorResponses.ErrorResponseCar;
import pl.parking.controller.reponses.errorResponses.ErrorUtils;
import pl.parking.model.Car;
import pl.parking.model.CarBooking;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/** A custom adapter to use with the RecyclerView widget. */
public class RecyclerListCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = "RecyclerListCarAdapter";
    private Context mContext;
    private String Token;
    private List<Car> modelList;
    private OnItemClickListener mItemClickListener;
    private OnCheckedListener mOnCheckedListener;
    private Set<Integer> checkSet = new HashSet<>();

    public RecyclerListCarAdapter() {}

    public RecyclerListCarAdapter(Context context, List<Car> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    private static SharedPreferences getprefs(Context context) {
        return context.getSharedPreferences("Token.txt", MODE_PRIVATE);
    }

    private static String getToken(Context context) {
        return getprefs(context).getString("Token", null);
    }

    public void updateList(List<Car> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_recycler_list_car, viewGroup, false);

        return new ViewHolder(view);
    }

    private String time_convert(String input) {
        String[] parts = input.split("-");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];
        String time = input.substring(input.indexOf("T") + 1);
        String[] time_array = time.split(":");
        String hour = time_array[0];
        String minute = time_array[1];
        String date_coverted = day + "." + month + "." + year;
        String time_converted = hour + ":" + minute;
        String full_time = date_coverted + " " + time_converted;

        return full_time;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder) {
            final Car model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

            try {

                Date start =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(model.getDateFrom().substring(0, 19).replace("T", " "));
                Date end =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(model.getDateTo().substring(0, 19).replace("T", " "));

                genericViewHolder.item_car_id.setText("Identificator:" + model.getId());
                genericViewHolder.item_car_date_from.setText("Start:\n" + start.toLocaleString());
                genericViewHolder.item_car_date_to.setText("End:\n" + end.toLocaleString());
                genericViewHolder.item_car_registration_plate.setText(
                        "Car registration plate:\n " + model.getRegistrationPlate().toUpperCase());
                genericViewHolder.item_car_status.setText("Status:" + model.getStatus());

                genericViewHolder.item_car_cost.setText("Cost: " + model.getCost().toString());
                genericViewHolder.item_car_switch_list.setOnCheckedChangeListener(null);

                genericViewHolder.item_car_switch_list.setChecked(checkSet.contains(position));
                if (!model.getStatus().contains("ACTIVE")) {
                    genericViewHolder.item_car_switch_list.setEnabled(false);
                }
                if (model.getStatus().contains("ACTIVE")) {
                    genericViewHolder.item_car_switch_list.setEnabled(true);
                    genericViewHolder.itemView.setBackgroundColor(Color.argb(152, 152, 186, 156));
                    genericViewHolder.item_car_switch_list.setChecked(true);
                }

                if (model.getStatus().contains("CANCELLED")) {
                    genericViewHolder.itemView.setBackgroundColor(Color.argb(100, 255, 153, 153));
                    genericViewHolder.item_car_switch_list.setChecked(false);
                    genericViewHolder.item_car_img_state_active.setImageResource(
                            R.drawable.ic_close_black_24dp);
                }
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Exception handled", e);
            }

            Token = RecyclerListCarAdapter.getToken(mContext);

            genericViewHolder.item_car_switch_list.setOnCheckedChangeListener(
                    new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (isChecked) {
                                checkSet.add(position);

                                Log.i(TAG, "onCheckedChanged:");

                            } else {

                                put_car car = new put_car(Token, model);
                                car.execute();

                                genericViewHolder.item_car_status.setText("Status:" + "CANCELLED");
                                model.setStatus("CANCELLED");
                                buttonView.setEnabled(false);
                                checkSet.remove(position);
                                Log.i(TAG, "onCheckedChanged:");
                                genericViewHolder.itemView.setBackgroundColor(
                                        Color.argb(100, 255, 153, 153));
                                genericViewHolder.item_car_img_state_active.setImageResource(
                                        R.drawable.ic_close_black_24dp);
                                genericViewHolder.item_car_cost.setText("Cost: 0");
                            }

                            mOnCheckedListener.onChecked(buttonView, isChecked, position, model);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void SetOnCheckedListener(final OnCheckedListener onCheckedListener) {
        this.mOnCheckedListener = onCheckedListener;
    }

    private Car getItem(int position) {
        return modelList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, Car model);
    }

    public interface OnCheckedListener {
        void onChecked(View view, boolean isChecked, int position, Car model);
    }

    public class Getcar_booking extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            SharedPreferences prefs = mContext.getSharedPreferences("Token.txt", MODE_PRIVATE);
            String Token = prefs.getString("Token", null);

            String result = "1";
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

                                } else {

                                    try {
                                        ApiStaticData.bookings = response.body();
                                    } catch (Exception e) {
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
    }

    class put_car extends AsyncTask<Void, Void, String> implements Serializable {

        String Token;
        Car car;
        boolean result;

        put_car(String Token, Car car) {
            this.Token = Token;
            this.car = car;
            car.setStatus("CANCELLED");
        }

        @Override
        protected String doInBackground(Void... params) {

            String result = "";

            ErrorResponseCar error;
            String parametr = String.valueOf(car.getId());

            try {
                ApiController apiEndpoints =
                        ParkingService.getRetrofitInstance().create(ApiController.class);
                Call<List<Car>> call =
                        apiEndpoints.putCarTokenPut(
                                Token, car.getId().toString(), car.getBooking().toString());
                Response response = call.execute();

                if (response.isSuccessful()) {
                    result = "Success";
                }
                if (!response.isSuccessful()) {

                    error = ErrorUtils.parse_Error_Car(response);
                    return error.getErrorMessage();
                }
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Exception handled", e);
            } finally {
                return result;
            }
        }

        @Override
        protected void onPostExecute(String out) {

            if (out.contains("Success")) {

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
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Status has been changed")
                        .setNeutralButton("Ok", dialogClickListener)
                        .show();
                this.result = true;

            } else {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Connectivity problem")
                        .setNeutralButton("Ok", dialogClickListener)
                        .show();
                this.result = false;
            }
        }

        private boolean myMethod() {
            boolean result = this.result;
            return result;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_car_img_state_active;
        private TextView item_car_id;
        private TextView item_car_date_from;
        private TextView item_car_date_to;
        private TextView item_car_cost;
        private TextView item_car_registration_plate;
        private TextView item_car_status;
        private SwitchCompat item_car_switch_list;

        private SwitchCompat itemSwitchList;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.item_car_img_state_active =
                    (ImageView) itemView.findViewById(R.id.item_car_img_state_active);

            this.item_car_id = (TextView) itemView.findViewById(R.id.item_car_id);
            this.item_car_date_from = (TextView) itemView.findViewById(R.id.item_car_date_from);
            this.item_car_date_to = (TextView) itemView.findViewById(R.id.item_car_date_to);
            this.item_car_cost = (TextView) itemView.findViewById(R.id.item_car_cost);
            this.item_car_registration_plate =
                    (TextView) itemView.findViewById(R.id.item_car_registration_plate);
            this.item_car_status = (TextView) itemView.findViewById(R.id.item_car_status);
            this.item_car_switch_list =
                    (SwitchCompat) itemView.findViewById(R.id.item_car_switch_list);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mItemClickListener.onItemClick(
                                    itemView,
                                    getAdapterPosition(),
                                    modelList.get(getAdapterPosition()));
                        }
                    });
        }
    }
}
