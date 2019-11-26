package pl.parking.view.RecyclerLists;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.parking.model.CarBooking;

public class BookingViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "BookingViewAdapter";
    private Context mContext;
    private List<CarBooking> modelList;
    private OnItemClickListener mItemClickListener;

    public BookingViewAdapter(Context context, List<CarBooking> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(List<CarBooking> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view =
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_recycler_list_car_booking_cards, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        // Here you can fill your row view
        try {
            if (holder instanceof ViewHolder) {
                final CarBooking model = getItem(position);
                ViewHolder genericViewHolder = (ViewHolder) holder;

                Date start =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(model.getDateFrom().substring(0, 19).replace("T", " "));
                Date end =
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                .parse(model.getDateTo().substring(0, 19).replace("T", " "));

                genericViewHolder.item_booking_id.setText("Identificator:" + model.getId());
                genericViewHolder.item_booking_date_from.setText(
                        "Start:\n" + start.toLocaleString());
                genericViewHolder.item_booking_date_to.setText("End:\n" + end.toLocaleString());
                genericViewHolder.item_booking_cost.setText("Total Cost:\n " + model.getCost());
                genericViewHolder.item_booking_number_of_cars.setText(
                        "Number of cars:\n " + model.getNumberOfCars());

                genericViewHolder.item_booking_Parking_street_city.setText(
                        "Street: "
                                + model.getParkingStreet()
                                + "\n"
                                + "City: "
                                + model.getParkingCity());
                if (model.getActive().toString().contains("true")) {
                    genericViewHolder.itemView.setBackgroundColor(Color.argb(152, 152, 186, 156));
                    genericViewHolder.item_booking_active.setText("Status: ACTIVE");
                } else {
                    genericViewHolder.itemView.setBackgroundColor(Color.argb(100, 255, 153, 153));
                    genericViewHolder.item_booking_active.setText("Status: INACTIVE");
                }
            }
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Exception handled", e);
        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private CarBooking getItem(int position) {
        return modelList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, CarBooking model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView itemTxtTitle;
        private TextView itemTxtMessage;

        private TextView item_booking_id;
        private TextView item_booking_active;
        private TextView item_booking_cost;
        private TextView item_booking_date_from;
        private TextView item_booking_date_to;
        private TextView item_booking_Parking_street_city;
        private ImageView item_booking_img_car;
        private TextView item_booking_number_of_cars;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.item_booking_active = (TextView) itemView.findViewById(R.id.item_booking_active);
            this.item_booking_id = (TextView) itemView.findViewById(R.id.item_booking_id);
            this.item_booking_cost = (TextView) itemView.findViewById(R.id.item_booking_cost);
            this.item_booking_date_from =
                    (TextView) itemView.findViewById(R.id.item_booking_date_from);
            this.item_booking_date_to = (TextView) itemView.findViewById(R.id.item_booking_date_to);
            this.item_booking_Parking_street_city =
                    (TextView) itemView.findViewById(R.id.item_booking_Parking_street_city);
            this.item_booking_img_car =
                    (ImageView) itemView.findViewById(R.id.item_booking_icon_car);
            this.item_booking_number_of_cars =
                    (TextView) itemView.findViewById(R.id.item_booking_number_of_cars);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            int a = 2;

                            mItemClickListener.onItemClick(
                                    itemView,
                                    getAdapterPosition(),
                                    modelList.get(getAdapterPosition()));

                            Log.i(getClass().getSimpleName(), "ViewHolder");
                        }
                    });
        }
    }
}
