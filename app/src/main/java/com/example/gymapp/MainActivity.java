package com.example.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Intent weightTracker;
    Intent stepCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, gymprogram.class);
        weightTracker = new Intent(this, weighttracker.class);
        stepCounter = new Intent(this, Stepcounter.class);
    }

    public void onClick(View v) {
        startActivity(intent);
    }

    public void openWeightTracker(View v) {
        startActivity(weightTracker);
    }

    public void openStepCounter(View v) {
        startActivity(stepCounter);
    }
}