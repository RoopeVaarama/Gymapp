package com.example.gymapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {

    //constructor
    public SqlHelper(Context context) {
        super(context, "MyDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table myTable (xValues REAL, yValues REAL);";  //SQL for create table
        db.execSQL(createTable);  //execute

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //delete table and call oncreate to create new table
    public void clearDB(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS myTable");
        onCreate(db);
    }

    //delete last row
    public void clearLast(SQLiteDatabase db){
        db.execSQL("delete from myTable where xValues = ( select max(xValues) from myTable );");
    }

    //insert data into DB
    public Boolean insertData(float valueX, float valueY){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("xValues", valueX);
        contentValues.put("yValues", valueY);

        sqLiteDatabase.insert("myTable", null, contentValues);
        return true;
    }
}
