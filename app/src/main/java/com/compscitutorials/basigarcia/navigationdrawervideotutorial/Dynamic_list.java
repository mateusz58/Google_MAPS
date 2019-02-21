package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Testmodel.Registration_plate;

import java.util.ArrayList;

public class Dynamic_list extends Activity {
    ListView listView;
    EditText editTextView;
    ArrayList<Registration_plate> itemRegistrationplateList;
    Registration_plate_list_adapter registrationplatelistadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add_delete_view);
        listView = (ListView) findViewById(R.id.listview);
        editTextView = (EditText) findViewById(R.id.editTextView);
        itemRegistrationplateList = new ArrayList<Registration_plate>();
        registrationplatelistadapter = new Registration_plate_list_adapter(getApplicationContext(), itemRegistrationplateList);
        listView.setEmptyView(findViewById(android.R.id.empty));
        listView.setAdapter(registrationplatelistadapter);
    }
    @SuppressLint("NewApi")
    public void addValue(View v) {
        String name = editTextView.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Plz enter Values",
                    Toast.LENGTH_SHORT).show();
        } else {
            Registration_plate md = new Registration_plate(name);
            itemRegistrationplateList.add(md);
            registrationplatelistadapter.notifyDataSetChanged();
            editTextView.setText("");
        }
    }
}
