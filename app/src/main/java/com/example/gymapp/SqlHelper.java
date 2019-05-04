package com.example.gymapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

<<<<<<< HEAD
import androidx.annotation.Nullable;

=======
>>>>>>> origin/master
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
