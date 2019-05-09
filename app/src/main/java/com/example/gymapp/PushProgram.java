package com.example.gymapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class PushProgram extends AppCompatActivity implements GestureDetector.OnGestureListener {

    public static final int SWIPE_TRESHOLD = 100;
    public static final int SWIPE_THRESHOLD = SWIPE_TRESHOLD;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int pagelimit = 8;
    private int pagenumber = 1;
    ImageView image;
    VideoView video;
    TextView pageheader;
    TextView info;
    Switch switch1;
    PushResourses res;
    private GestureDetector gestureDetector;
    MediaController mediaC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_program);

        switch1 = (Switch)findViewById(R.id.pushswitch);
        pageheader = findViewById(R.id.pushheader);
        image = findViewById(R.id.pushimage);
        video = findViewById(R.id.pushvideo);
        info = findViewById(R.id.pushinfo);
        res = new PushResourses();

        gestureDetector = new GestureDetector(this);
        mediaC = new MediaController(this);

        update(pagenumber);
        imageShow(image);

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

    public void videoShow(View view1){
        image.setVisibility(View.GONE);
        video.setVisibility(View.VISIBLE);
    }

    public void imageShow(View view){
        image.setVisibility(View.VISIBLE);
        video.setVisibility(View.GONE);
    }

    public void update(int page){
        String nimituloste = res.getLiike(page);
        pageheader.setText("Move " + pagenumber + ": " + nimituloste);
        String tuloste = res.getInfo(page);
        info.setText(tuloste);
        updateVideo(page);
        updatePicture(page);
    }

    private void updateVideo(int page) {
        String videopath;
        switch (page){
            case 1:
                videopath = "android.resource://com.example.gymapp/"+R.raw.bench_press;
                Uri uri1 = Uri.parse(videopath);
                video.setVideoURI(uri1);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 2:
                videopath = "android.resource://com.example.gymapp/"+R.raw.inclinebench;
                Uri uri2 = Uri.parse(videopath);
                video.setVideoURI(uri2);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 3:
                videopath = "android.resource://com.example.gymapp/"+R.raw.overheadpress;
                Uri uri3 = Uri.parse(videopath);
                video.setVideoURI(uri3);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 4:
                videopath = "android.resource://com.example.gymapp/"+R.raw.frenchbench;
                Uri uri4 = Uri.parse(videopath);
                video.setVideoURI(uri4);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 5:
                videopath = "android.resource://com.example.gymapp/"+R.raw.triceppushdown;
                Uri uri5 = Uri.parse(videopath);
                video.setVideoURI(uri5);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 6:
                videopath = "android.resource://com.example.gymapp/"+R.raw.triceppushdown;
                Uri uri6 = Uri.parse(videopath);
                video.setVideoURI(uri6);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 7:
                videopath = "android.resource://com.example.gymapp/"+R.raw.lateralraise;
                Uri uri7 = Uri.parse(videopath);
                video.setVideoURI(uri7);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 8:
                videopath = "android.resource://com.example.gymapp/"+R.raw.facepull;
                Uri uri8 = Uri.parse(videopath);
                video.setVideoURI(uri8);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;

        }
    }

    private void updatePicture(int page) {
        switch (page){
            case 1:
                image.setImageResource(R.drawable.benchpress);
                break;
            case 2:
                image.setImageResource(R.drawable.inclinebench);
                break;
            case 3:
                image.setImageResource(R.drawable.overheadpress);
                break;
            case 4:
                image.setImageResource(R.drawable.frenchbench);
                break;
            case 5:
                image.setImageResource(R.drawable.triceppushdown);
                break;
            case 6:
                image.setImageResource(R.drawable.triceppushdown);
                break;
            case 7:
                image.setImageResource(R.drawable.lateralraises);
                break;
            case 8:
                image.setImageResource(R.drawable.facepulls);
                break;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();
        if(Math.abs(diffX) > Math.abs(diffY)){
            // left or right
            if(Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                if(diffX > 0){
                    //swipe right
                    onSwipeRight();
                }else{
                    //swipe left
                    onSwipeLeft();
                }
                result = true;
            }
        }else{
            // up or down
            if(Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD){
                if(diffY > 0){
                    //swipe down
                    onSwipeDown();
                }else{
                    //swipe top
                    onSwipeUp();
                }
            }
        }

        return result;
    }

    private void onSwipeUp() {
        Toast.makeText(getApplicationContext(), "Up", Toast.LENGTH_LONG).show();
    }

    private void onSwipeDown() {
        Toast.makeText(getApplicationContext(), "Down", Toast.LENGTH_LONG).show();
    }

    private void onSwipeLeft() {
        Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_LONG).show();
        if(pagenumber < pagelimit){
            pagenumber++;
            update(pagenumber);
        }else{
            Toast.makeText(getApplicationContext(), "Last page", Toast.LENGTH_LONG).show();
        }
    }

    private void onSwipeRight() {
        Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_LONG).show();
        if(pagenumber > 1 ){
            pagenumber--;
            update(pagenumber);
        }else{
            Toast.makeText(getApplicationContext(), "First page", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
