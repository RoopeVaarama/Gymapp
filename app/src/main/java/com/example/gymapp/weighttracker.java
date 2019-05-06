package com.example.gymapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
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

public class weighttracker extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

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
        this.xValue = sharedPref.getFloat("xVALUE",1);

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

        //background color, draw chart borders etc
        lineChart.setBackgroundColor(Color.LTGRAY);
        lineChart.setDrawGridBackground(true);
        lineChart.setDrawBorders(true);
        lineChart.setBorderColor(Color.BLACK);

        //set no data text and color
        lineChart.setNoDataText("Press refresh to show graph data");
        lineChart.setNoDataTextColor(Color.BLACK);

        //chart description fonts, colors, text
        Description description = new Description();
        description.setText("Weight data");
        description.setTextColor(Color.RED);
        description.setTextSize(15);
        lineChart.setDescription(description);

        lineChart.getAxisRight().setEnabled(false); // y axis numbers only on left side of the chart

        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // x axis values under the chart
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
    }

    @Override
    protected void onPause() {
        super.onPause();

        //save xValue
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putFloat("xVALUE",this.xValue);
        prefEditor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //get xValue
        this.xValue = sharedPref.getFloat("xVALUE",1);
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

    //method for popup menu
    public void popup_Menu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    //handle popupmenu items
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.setGoal:
                Toast.makeText(this, "1",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.clearGoal:
                Toast.makeText(this, "2",Toast.LENGTH_SHORT).show();
                sqlHelper.clearLast(sqLiteDatabase);
                return true;

            case R.id.clearLast:
                //clear last entry from data base and decrement xValue
                sqlHelper.clearLast(sqLiteDatabase);

                if(xValue >= 2) {
                    this.xValue -= 1;
                }
                Toast.makeText(this, "Last entry deleted.",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.clearDB:
                //clear database and set xValue to 1
                sqlHelper.clearDB(sqLiteDatabase);
                this.xValue = 1;
                Toast.makeText(this, "Data deleted!",Toast.LENGTH_SHORT).show();
                return true;

                default:
                    return false;
        }
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
