package com.example.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class gymprogram extends AppCompatActivity {

    Intent pull;
    Intent push;
    Intent leg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram);
        pull = new Intent(this, pullprogram.class);
        push = new Intent(this, pushprogram.class);
        leg = new Intent(this, legprogram.class);
    }
}
