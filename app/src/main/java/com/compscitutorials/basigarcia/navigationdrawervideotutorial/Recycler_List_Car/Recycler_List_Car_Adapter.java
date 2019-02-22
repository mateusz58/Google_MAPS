package com.compscitutorials.basigarcia.navigationdrawervideotutorial.Recycler_List_Car;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.R;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Car;


import android.support.v7.widget.SwitchCompat;

import java.util.HashSet;
import java.util.Set;

import android.widget.CompoundButton;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class Recycler_List_Car_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



private static String TAG="Recycler_List_Car_Adapter";


    private Context mContext;
    private ArrayList<Car> modelList;

    private OnItemClickListener mItemClickListener;

    private OnCheckedListener mOnCheckedListener;


    private Set<Integer> checkSet = new HashSet<>();


    public Recycler_List_Car_Adapter(Context context, ArrayList<Car> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<Car> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list_car, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final Car model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;



            try {


                genericViewHolder.item_car_id.setText("Identificator:"+model.getId());
                genericViewHolder.item_car_date_from.setText("Start:\n"+model.getDateFrom());
                genericViewHolder.item_car_date_to.setText("End:\n"+model.getDateTo());


                genericViewHolder.item_car_registration_plate.setText("Car registration plate:\n "+model.getRegistrationPlate().toUpperCase());
                genericViewHolder.item_car_status.setText("Status:"+model.getStatus());

                genericViewHolder.item_car_cost.setText("Cost: "+model.getCost().toString());
                genericViewHolder.item_car_switch_list.setOnCheckedChangeListener(null);

//                genericViewHolder.itemView.setBackground();
                //if true, your checkbox will be selected, else unselected
                genericViewHolder.item_car_switch_list.setChecked(checkSet.contains(position));
                if(!model.getStatus().contains("ACTIVE"))
                {
                    genericViewHolder.item_car_switch_list.setEnabled(false);
                    genericViewHolder.item_car_switch_list.setChecked(false);
                }
                if(model.getStatus().contains("ACTIVE"))
                {

                    genericViewHolder.item_car_switch_list.setEnabled(true);
                    genericViewHolder.itemView.setBackgroundColor(Color.argb(152,152,186,156));
                    genericViewHolder.item_car_switch_list.setChecked(true);

                }
                if(model.getStatus().contains("CANCELLED"))
                {
                    genericViewHolder.itemView.setBackgroundColor(Color.argb(100,255,153,153));
                    genericViewHolder.item_car_switch_list.setChecked(false);

                }
            } catch (Exception e) {
                Log.e(getClass().getSimpleName(), "Exception handled", e);
            }


            genericViewHolder.item_car_switch_list.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        checkSet.add(position);

                        Log.i(TAG, "onCheckedChanged:");


                    } else {
                        genericViewHolder.item_car_status.setText("Status:"+"CANCELLED");
                        buttonView.setEnabled(false);
                        checkSet.remove(position);
                        Log.i(TAG, "onCheckedChanged:");
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

    public class ViewHolder extends RecyclerView.ViewHolder {

//        private ImageView imgUser;
//        private TextView itemTxtTitle;
//        private TextView itemTxtMessage;


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



            this.item_car_img_state_active = (ImageView) itemView.findViewById(R.id.item_car_img_state_active);

            this.item_car_id=(TextView) itemView.findViewById(R.id.item_car_id);
            this.item_car_date_from = (TextView) itemView.findViewById(R.id.item_car_date_from);
            this.item_car_date_to = (TextView) itemView.findViewById(R.id.item_car_date_to);
            this.item_car_cost = (TextView) itemView.findViewById(R.id.item_car_cost);
            this.item_car_registration_plate=(TextView) itemView.findViewById(R.id.item_car_registration_plate);
            this.item_car_status=(TextView) itemView.findViewById(R.id.item_car_status);
            this.item_car_switch_list = (SwitchCompat) itemView.findViewById(R.id.item_car_switch_list);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

