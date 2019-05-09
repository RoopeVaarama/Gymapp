package com.example.gymapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

    private static final String CHANNEL_ID = "notifications";
    private static final int notifactionID = 666;

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

    @Override
    protected void onPause() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notifyUser();   //notify user when user closes this activity
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

    //method for notifications
    public void notifyUser(){

        //call notification channel
        newNotificationChannel();

        //new object and set icon, title, text and priority for notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.gym_app_icon);
        builder.setContentTitle("Step counter");
        builder.setContentText("You moved " + this.steps + " steps!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //for building and showing the notification
        NotificationManagerCompat notiManager = NotificationManagerCompat.from(this);
        notiManager.notify(notifactionID, builder.build());
    }

    //android versions 8 and above needs to create notification channel
    private void newNotificationChannel(){
        //check for version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //create NotificationChannel object and set notification importance
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("notification description");

            NotificationManager notiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notiManager.createNotificationChannel(notificationChannel);
        }
    }

}
