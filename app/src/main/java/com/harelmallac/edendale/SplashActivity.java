package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.harelmallac.edendale.database.ApiRequest;
import com.harelmallac.edendale.database.DataBaseHelper;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT= 30000;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(SplashActivity.this, "Application starting... ", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.getDataFromMiddleware(this);

        dataBaseHelper = new DataBaseHelper(this);

        new Handler().postDelayed (new Runnable () {
            @Override
            public void run () {


                Intent loginIntent = new Intent( SplashActivity.this,
                        MainActivity.class );
                startActivity(loginIntent);
                finish();

            }
        } , SPLASH_TIME_OUT );
    }


}