package com.example.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class pullprogram extends AppCompatActivity implements GestureDetector.OnGestureListener{

    public static final int SWIPE_TRESHOLD = 100;
    public static final int SWIPE_THRESHOLD = SWIPE_TRESHOLD;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    public static final int pagelimit = 7;
    private int pagenumber = 1;
    ImageView image;
    VideoView video;
    TextView pageheader;
    TextView info;
    Switch switch1;
    PullResourses res;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullprogram);

        switch1 = (Switch)findViewById(R.id.pullswitch);
        pageheader = findViewById(R.id.pullheader);
        image = findViewById(R.id.pullimage);
        video = findViewById(R.id.pullvideo);
        info = findViewById(R.id.pullinfo);
        res = new PullResourses();

        gestureDetector = new GestureDetector(this);

        update(pagenumber);

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
        //updatePicture(page);
    }
    /*private void updatePicture(int page) {
        switch (page){
            case 1:
                image.setImageResource(R.drawable.);
                break;
            case 2:
                image.setImageResource(R.drawable.);
                break;
            case 3:
                image.setImageResource(R.drawable.);
                break;
            case 4:
                image.setImageResource(R.drawable.);
                break;
            case 5:
                image.setImageResource(R.drawable.);
                break;
            case 6:
                image.setImageResource(R.drawable.);
                break;
            case 7:
                image.setImageResource(R.drawable.);
                break;
            case 8:
                image.setImageResource(R.drawable.);
                break;

        }
    }*/

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
