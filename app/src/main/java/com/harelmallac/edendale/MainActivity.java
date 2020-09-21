package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.UserModel;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);


        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel;
                userModel = new UserModel(5,"qawsdfg","asdfg","erfgn","hgfd","hygfds","hgbvc",true,"jnbvcx");

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(userModel);

                Toast.makeText(MainActivity.this, "Success = " + success, Toast.LENGTH_LONG).show();

                Intent loginIntent = new Intent( MainActivity.this,
                        MenuActivity.class );

                startActivity(loginIntent);
                finish();
            }
        });

    }
}