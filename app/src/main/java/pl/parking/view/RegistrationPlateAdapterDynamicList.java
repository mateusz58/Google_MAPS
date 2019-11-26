package pl.parking.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.util.ArrayList;

import pl.parking.model.RegistrationPlate;

public class RegistrationPlateAdapterDynamicList extends Activity {
    ListView listView;
    EditText editTextView;
    ArrayList<RegistrationPlate> itemRegistrationplateList;
    RegistrationPlateListAdapter registrationplatelistadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add_delete_view);
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
        if (name.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Plz enter Values", Toast.LENGTH_SHORT).show();
        } else {
            RegistrationPlate md = new RegistrationPlate(name);
            itemRegistrationplateList.add(md);
            registrationplatelistadapter.notifyDataSetChanged();
            editTextView.setText("");
        }
    }
}
