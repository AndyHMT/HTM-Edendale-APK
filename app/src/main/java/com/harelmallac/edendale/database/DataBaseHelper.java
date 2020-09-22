package com.harelmallac.edendale.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.harelmallac.edendale.SplashActivity;
import com.harelmallac.edendale.model.*;

import java.util.ArrayList;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "tbl_user";
    public SQLiteDatabase db;


    //Function for the first time a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //FOR USER PART
        String dropTableStatement="DROP TABLE IF EXISTS " + USER_TABLE;
        db.execSQL(dropTableStatement);
        Log.i("Drop Table User","Table User Dropped");
        String createTableStatement="CREATE TABLE "+ USER_TABLE+"(userId INTEGER PRIMARY KEY, password VARCHAR, repNum VARCHAR(500), salesSiteId VARCHAR, username VARCHAR, role VARCHAR, mainSite VARCHAR, bank VARCHAR, active NUMERIC)";
        db.execSQL(createTableStatement);
        Log.i("Create Table User","Table User Created");
    }

    public void userValues(SQLiteDatabase db)
    {
        String dropTableStatement="DROP TABLE IF EXISTS " + USER_TABLE;
        db.execSQL(dropTableStatement);
        Log.i("Drop Table User","Table User Dropped");
        String createTableStatement="CREATE TABLE "+ USER_TABLE+"(userId INTEGER PRIMARY KEY, password VARCHAR, repNum VARCHAR(500), salesSiteId VARCHAR, username VARCHAR, role VARCHAR, mainSite VARCHAR, bank VARCHAR, active NUMERIC)";
        db.execSQL(createTableStatement);
        Log.i("Create Table User","Table User Created");
    }

    //function when version number changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(UserModel userModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userId",userModel.getUserId());
        cv.put("password",userModel.getPassword());
        cv.put("repNum",userModel.getRepNum());
        cv.put("salesSiteId",userModel.getSalesSiteId());
        cv.put("username",userModel.getUsername());
        cv.put("role",userModel.getRole());
        cv.put("mainSite",userModel.getMainsite());
        cv.put("bank",userModel.getBank());
        cv.put("active",userModel.isActive());

        long insert = db.insert(USER_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }


    }


    public DataBaseHelper(@Nullable Context context) {
        super(context,"edendale.db",null,1);
    }

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DataBaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    public ArrayList<UserModel> getAllElements() {

        ArrayList<UserModel> list = new ArrayList<UserModel>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        UserModel obj = new UserModel();
                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        return list;
    }


}
