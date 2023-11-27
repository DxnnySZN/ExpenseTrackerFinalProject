package com.example.expensetrackerproject.InitialUserData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.expensetrackerproject.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class WhatDayGetPaidWEEKLY extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_day_get_paid_weekly);

        Spinner spinner = findViewById(R.id.spinner);

        // Learnt this code from previous project, implemented it again
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set a listener to respond to item selections
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Do something with the selected item
                String selectedItem = parentView.getItemAtPosition(position).toString();
                // if monday, set this chron, etc.

                // IMPLEMENT FIRESTORE
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }
}
