package com.example.gymapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class weighttracker extends AppCompatActivity{

    private static final String TAG = "weightTracker";

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weighttracker);

        //search for the linechart
        lineChart = findViewById(R.id.chart);

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        ArrayList<Entry> weightData = new ArrayList<>();

        //test values
        weightData.add(new Entry(0,5f));
        weightData.add(new Entry(1,15f));
        weightData.add(new Entry(2,20f));
        weightData.add(new Entry(3,17f));
        weightData.add(new Entry(4,45f));
        weightData.add(new Entry(5,66f));
        weightData.add(new Entry(6,70f));

        LineDataSet set1 = new LineDataSet(weightData, "Weight (KG)");

        //set colors and fonts
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(2f);
        set1.setValueTextSize(12f);



        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1

        lineChart.setData(data);

        //mLineChart.invalidate(); // refresh

        /**
         * useful stuff
         *
         * saveToGallery(String title): Saves the current chart state as an image to the gallery.
         * Don't forget to add "WRITE_EXTERNAL_STORAGE" permission to your manifest.
         *
         * isEmpty(): Will return true if the charts data object is null, or if it contains no entries.
         *
         * clear(): Clears the chart of all data (by setting the data object to null). Calls invalidate() to refresh the chart.
         * clearValues(): Clears the chart of all DataSet objects and thereby all Entries. Does not remove the provided x-values from the chart. Calls invalidate() to refresh the chart.
         */

    }
}
