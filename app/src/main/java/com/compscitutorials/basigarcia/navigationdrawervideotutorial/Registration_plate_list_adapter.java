package com.compscitutorials.basigarcia.navigationdrawervideotutorial;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Testmodel.Registration_plate;

import java.util.ArrayList;

public class Registration_plate_list_adapter extends BaseAdapter {
        Context context;
        ArrayList<Registration_plate> itemRegistrationplateList;
        public Registration_plate_list_adapter(Context context, ArrayList<Registration_plate> registrationplateList) {
            this.context = context;
            this.itemRegistrationplateList = registrationplateList;
        }
        @Override
        public int getCount() {
            return itemRegistrationplateList.size();
        }
        @Override
        public Object getItem(int position) {
            return itemRegistrationplateList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = null;
            if (convertView == null) {
                LayoutInflater mInflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.items, null);
                TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
                ImageView imgRemove = (ImageView) convertView.findViewById(R.id.imgRemove);
                Registration_plate m = itemRegistrationplateList.get(position);
                tvName.setText(m.getName());
                // click listiner for remove button
                imgRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemRegistrationplateList.remove(position);
                        notifyDataSetChanged();
                    }
                });
            }
            return convertView;
        }
    }



