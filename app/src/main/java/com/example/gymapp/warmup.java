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
        warmupinfo.setText("Muista lämmitellä");
    }
}
