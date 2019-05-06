package com.example.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class gymprogram extends AppCompatActivity {

    Intent pull;
    Intent push;
    Intent leg;
    Intent warmup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gymprogram);
        pull = new Intent(this, pullprogram.class);
        push = new Intent(this, PushProgram.class);
        leg = new Intent(this, legprogram.class);
        warmup = new Intent(this, warmup.class);
    }

    public void openPull(View v){
        startActivity(pull);
    }

    public void openPush(View v){
        startActivity(push);
    }

    public void openLeg(View v){
        startActivity(leg);
    }

    public void openWarmup(View v){
        startActivity(warmup);
    }
}
