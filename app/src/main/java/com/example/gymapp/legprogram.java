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

public class legprogram extends AppCompatActivity implements GestureDetector.OnGestureListener{

    public static final int SWIPE_TRESHOLD = 100;
    public static final int SWIPE_THRESHOLD = SWIPE_TRESHOLD;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int pagelimit = 6;
    private int pagenumber = 1;
    ImageView image;
    VideoView video;
    TextView pageheader;
    TextView info;
    Switch switch1;
    LegResourses res;
    private GestureDetector gestureDetector;
    MediaController mediaC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legprogram);

        switch1 = (Switch)findViewById(R.id.legswitch);
        pageheader = findViewById(R.id.legheader);
        image = findViewById(R.id.legimage);
        video = findViewById(R.id.legvideo);
        info = findViewById(R.id.leginfo);
        res = new LegResourses();
        mediaC = new MediaController(this);

        gestureDetector = new GestureDetector(this);

        update(pagenumber);
        imageShow(image);
        image.setImageResource(R.drawable.giphy);

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
        info.setText(tuloste);//updateVideo(page);
        //updateVideo(page);
        updatePicture(page);
    }

    /*private void updateVideo(int page) {
            String videopath;
        switch (page){
            case 1:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri1 = Uri.parse(videopath);
                video.setVideoURI(uri1);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 2:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri2 = Uri.parse(videopath);
                video.setVideoURI(uri2);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 3:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri3 = Uri.parse(videopath);
                video.setVideoURI(uri3);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 4:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri4 = Uri.parse(videopath);
                video.setVideoURI(uri4);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 5:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri5 = Uri.parse(videopath);
                video.setVideoURI(uri5);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 6:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri6 = Uri.parse(videopath);
                video.setVideoURI(uri6);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 7:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri7 = Uri.parse(videopath);
                video.setVideoURI(uri7);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;
            case 8:
                videopath = "android.resource://com.example.gymapp/"+R.drawable.;
                Uri uri8 = Uri.parse(videopath);
                video.setVideoURI(uri8);
                video.setMediaController(mediaC);
                mediaC.setAnchorView(video);
                video.start();
                break;

        }
    }*/
    private void updatePicture(int page) {
        switch (page){
            case 0:
                image.setImageResource(R.drawable.leg1);
                break;
            case 1:
                image.setImageResource(R.drawable.leg1);
                break;
            case 2:
                image.setImageResource(R.drawable.leg2);
                break;
            case 3:
                image.setImageResource(R.drawable.leg3);
                break;
            case 4:
                image.setImageResource(R.drawable.leg4);
                break;
            case 5:
                image.setImageResource(R.drawable.leg5);
                break;
            case 6:
                image.setImageResource(R.drawable.leg6);
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
        if(pagenumber > 0 ){
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
