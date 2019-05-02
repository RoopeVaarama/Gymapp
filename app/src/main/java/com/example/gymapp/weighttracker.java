package com.example.gymapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class weighttracker extends AppCompatActivity{

    private static final String TAG = "weightTracker";

    private LineChart lineChart;
    private SqlHelper sqlHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText setWeight;
    private Button insertButton, showButton;
    private SharedPreferences sharedPref;
    private float xValue;

    XAxis xAxis;

    LineDataSet lineDataSet = new LineDataSet(null,null);
    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weighttracker);

        sharedPref = getSharedPreferences("Load", MODE_PRIVATE);
        this.xValue = sharedPref.getFloat("xVALUE",0);

        //search for the line chart, edit texts and buttons
        lineChart = findViewById(R.id.chart);
        insertButton = findViewById(R.id.insertButton);
        showButton = findViewById(R.id.showButton);
        setWeight = findViewById(R.id.setWeight);

        sqlHelper = new SqlHelper(this);
        sqLiteDatabase = sqlHelper.getWritableDatabase();


        onInsertClick();
        onShowClick();

        //graph colors and fonts etc
        lineDataSet.setLineWidth(4);
        lineDataSet.setFillAlpha(110);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setValueTextSize(12f);

        lineChart.animateY(2000); // chart animation

        xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
    }

    @Override
    protected void onPause() {
        super.onPause();

        //save xValue
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putFloat("xVALUE",this.xValue);
        prefEditor.apply();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //get xValue
        this.xValue = sharedPref.getFloat("xVALUE",0);
    }

    private void onInsertClick() {

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //catch error if field is left empty
                try {
                    float yValue = Float.parseFloat(String.valueOf(setWeight.getText()));

                    if (yValue < 0) {
                        toastMessage(1);
                    } else {
                        //insert data to database, increment xValue and display message
                        sqlHelper.insertData(xValue, yValue);
                        xValue += 1;
                        toastMessage(2);
                    }

                }catch (Exception e){
                    toastMessage(3);
                }
            }
        });
    }

    //toast messages
    public void toastMessage(int num){
        if(num == 1){
            Toast.makeText(this,"Weight can't be negative.",Toast.LENGTH_SHORT).show();
        }else if(num == 2){
            Toast.makeText(this,"Value added to graph",Toast.LENGTH_SHORT).show();
        }else if(num == 3){
            Toast.makeText(this,"Field can't be empty.",Toast.LENGTH_SHORT).show();
        }

    }

    private void onShowClick() {

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineDataSet.setValues(getDataValues());
                lineDataSet.setLabel("Weight");

                dataSets.clear(); // clear old data
                dataSets.add(lineDataSet); // new data
                lineData = new LineData(dataSets);
                lineChart.clear(); // clear old data
                lineChart.setData(lineData); // new data
                lineChart.invalidate();  //refresh
            }
        });
    }

    private ArrayList<Entry> getDataValues(){
        ArrayList<Entry> dataValues = new ArrayList<>();
        String[] columns = {"xValues", "yValues"};
        Cursor cursor = sqLiteDatabase.query("myTable",columns,null,null,null,null,null);

        for(int i = 0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dataValues.add(new Entry(cursor.getFloat(0),cursor.getFloat(1)));
        }
        return dataValues;
    }

}
