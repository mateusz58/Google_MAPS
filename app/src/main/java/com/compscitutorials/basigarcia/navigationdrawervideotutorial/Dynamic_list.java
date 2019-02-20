package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.navigationdrawervideotutorial.Testmodel.Model;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.beans.Booking;

import java.util.ArrayList;

public class Dynamic_list extends Activity {
    ListView listView;
    EditText editTextView;
    ArrayList<Model> ItemModelList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add_delete_view);
        listView = (ListView) findViewById(R.id.listview);
        editTextView = (EditText) findViewById(R.id.editTextView);
        ItemModelList = new ArrayList<Model>();
        customAdapter = new CustomAdapter(getApplicationContext(), ItemModelList);
        listView.setEmptyView(findViewById(android.R.id.empty));
        listView.setAdapter(customAdapter);
    }
    @SuppressLint("NewApi")
    public void addValue(View v) {
        String name = editTextView.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Plz enter Values",
                    Toast.LENGTH_SHORT).show();
        } else {
            Model md = new Model(name);
            ItemModelList.add(md);
            customAdapter.notifyDataSetChanged();
            editTextView.setText("");
        }
    }
}
