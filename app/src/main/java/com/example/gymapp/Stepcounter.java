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
    boolean moving = false;
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
        moving = true;
        Sensor  countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);

        } else {
            //Toast to see if your phone doesn't have the required sensor
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }


    protected void Onpause(){
        super.onPause();
        moving = false;

    }
    //Method for detecting the sensor change = adding steps when walking
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(start){
            startval = (event.values[0]);
            start = false;
        }
        if(moving){
            steps = (event.values[0]);
            Update();
        }
    }
    //method that is not used
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //onclick for resetting stepcount
    public void onClickReset(View view){
        startval = steps;
        Update();
    }

    //Update method for updating the step count
    public void Update(){

        float o = steps-startval;
        stepView.setText(Float.toString(o));
    }
    //method to set the starting count to 0, because only way to reset stepcounter sensor in android is to restart the phome
    public void Start(){
        start = true;
    }
}
