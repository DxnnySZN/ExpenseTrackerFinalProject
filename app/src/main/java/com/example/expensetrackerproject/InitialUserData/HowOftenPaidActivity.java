package com.example.expensetrackerproject.InitialUserData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.expensetrackerproject.R;
import com.google.android.material.snackbar.Snackbar;

public class HowOftenPaidActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_often_paid);
        layout = findViewById(R.id.howOftenPaidAct);
        Button nextButton = findViewById(R.id.buttonNext1);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup rG = findViewById(R.id.radioGroup1);
                int selectedRadioButtonId = rG.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    // At least one RadioButton is selected
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    String selectedText = selectedRadioButton.getText().toString();
                    if (selectedText.equals("Weekly")) {
                        Intent intent = new Intent(HowOftenPaidActivity.this, WhatDayGetPaidWEEKLY.class);
                        startActivity(intent);
                    }
                    if (selectedText.equals("Biweekly")) {
                        Intent intent = new Intent(HowOftenPaidActivity.this, WhatDayGetPaidWEEKLY.class);
                        startActivity(intent);
                    }
                    if (selectedText.equals("Monthly")) {
                        Intent intent = new Intent(HowOftenPaidActivity.this, WhatDayGetPaidMONTHLY.class);
                        startActivity(intent);
                    }
                }
                else {
                    snackbar("Please select an option!");
                }
                // Now you can access the selected RadioButton's properties


                // IMPORTANT: ADD THIS INFO TO FIREBASE
            }
        });


    }
    private void snackbar(String msg) {
        Snackbar.make(layout, msg,
                        Snackbar.LENGTH_SHORT)
                .show();
    }
}