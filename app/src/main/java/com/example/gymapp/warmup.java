package com.example.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class warmup extends AppCompatActivity {
    TextView warmupinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmup);

        warmupinfo = findViewById(R.id.infoView);
        warmupinfo.setText("For warmup always remember to atleast stretch the area of muscles you are using for training.\n" +
                "It's recommended to walk atleast 500-1000 steps. Easiest way to track the steps is using the apps step counter.");
    }
}
