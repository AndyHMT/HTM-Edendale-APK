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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DataBaseHelper dB = new DataBaseHelper(MainActivity.this);
        final List<UserModel>[] userList = new List[]{new ArrayList<UserModel>()};




        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userList[0] = dB.getAllElements();

                Intent loginIntent = new Intent( MainActivity.this,
                        MenuActivity.class );

                startActivity(loginIntent);
                finish();
            }
        });

    }
}