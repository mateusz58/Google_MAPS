package pl.parking.view;

/** Created by zlo on 10.05.16. */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.util.ArrayList;
import java.util.Calendar;

import pl.parking.model.ParkingReservationResult;

public class ParkingHistoryArrayAdapter extends ArrayAdapter<ParkingReservationResult> {
    private final Context context;
    private final ArrayList<ParkingReservationResult> values;

    public ParkingHistoryArrayAdapter(Context context, ArrayList<ParkingReservationResult> values) {
        super(context, -1, (values));
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView =
                inflater.inflate(R.layout.fragment_parking_history_array_adapter, parent, false);
        TextView Firstline = (TextView) rowView.findViewById(R.id.firstLine);
        TextView Secondline = (TextView) rowView.findViewById(R.id.secondline);
        TextView Thirdline = (TextView) rowView.findViewById(R.id.thirdline);
        TextView forthline = (TextView) rowView.findViewById(R.id.forthline);
        TextView adressline = (TextView) rowView.findViewById(R.id.Address_line);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_state);
        Firstline.setText(values.get(position).getOd().toString());
        adressline.setText(values.get(position).getAdres());
        Secondline.setText(values.get(position).getTheDo().toString());
        Thirdline.setText(values.get(position).getKod_dostepu());
        forthline.setText(values.get(position).getNumer_rejestracji().toUpperCase());
        imageView.setImageResource(R.drawable.ic_check_black_24dp);
        rowView.setBackgroundColor(Color.argb(100, 204, 255, 204));
        Calendar cal = Calendar.getInstance();
        // rezerwacja niewazna
        if (values.get(position).getTheDo().before(cal.getTime())) {
            imageView.setImageResource(R.drawable.ic_close_black_24dp);
            rowView.setBackgroundColor(Color.argb(100, 255, 153, 153));
        }
        return rowView;
    }
}
