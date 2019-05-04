package com.example.gymapp;

import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Stepcounter extends AppCompatActivity implements SensorEventListener {

    TextView stepView;
    SensorManager sensorManager;
    boolean running = false;
    boolean start = false;
    float steps;
    float startval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepcounter);


        stepView = findViewById(R.id.stepView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        running = true;
        Sensor  countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }


    protected void Onpause(){
        super.onPause();
        running = false;

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(start){
            startval = (event.values[0]);
            start = false;
        }
        if(running){
            steps = (event.values[0]);
            Update();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onClickReset(View view){
        Start();
        Update();
    }
    public void Update(){

        float o = steps-startval;
        stepView.setText(Float.toString(o));
    }
    public void Start(){
        start = true;
    }
}
