package com.example.gymapp;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class PushProgram extends AppCompatActivity {

    private int pagenumber = 1;
    ImageView image;
    VideoView video;
    TextView pageheader;
    TextView info;
    Switch switch1;
    PushResourses res;

    public void videoShow(View view1){
        image.setVisibility(View.GONE);
        video.setVisibility(View.VISIBLE);
    }

    public void imageShow(View view){
        image.setVisibility(View.VISIBLE);
        video.setVisibility(View.GONE);
    }

    public void update(int page){
        pageheader.setText("Liike " + pagenumber);
        //info.setText(res.getInfo(page));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_program);

        switch1 = (Switch)findViewById(R.id.switch1);
        pageheader = findViewById(R.id.textView2);
        image = findViewById(R.id.imageView);
        video = findViewById(R.id.videoView);
        info = findViewById(R.id.textView3);

        update(1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Video", Toast.LENGTH_LONG).show();
                    videoShow(image);
                }else{
                    Toast.makeText(getApplicationContext(), "Picture", Toast.LENGTH_LONG).show();
                    imageShow(video);
                }
            }
        });
    }
}
