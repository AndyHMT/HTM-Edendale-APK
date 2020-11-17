package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        final DataBaseHelper dB = new DataBaseHelper(MainActivity.this);
        final ArrayList<UserModel> userList = new ArrayList<UserModel>();
        final EditText usernametxt = findViewById(R.id.usernameTxt);
        final EditText passwordtxt = findViewById(R.id.passwordTxt);
//List of users
        Cursor res = dB.getData();
        if(res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()){

            String id = res.getString(0);
            String password = res.getString(1);
            String salesRepId = res.getString(2);
            String salesSiteId = res.getString(3);
            String role = res.getString(4);
            String username = res.getString(5);
            String bank = res.getString(6);
            Boolean active = Boolean.parseBoolean(res.getString(7));
            String mainsite = res.getString(8);
            UserModel user = new UserModel(id,password,salesRepId,salesSiteId,role,username,bank,active,mainsite);
            userList.add(user);
            //Log.e("dfghjk",password);
        }

        final Intent loginIntent = new Intent( MainActivity.this,
                MenuActivity.class );

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginUsername = usernametxt.getText().toString();
                String loginPassword = passwordtxt.getText().toString();
                String generatedPassword = null;
                Log.e("qwerty",loginPassword);

                try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(loginPassword.getBytes());
                    //Get the hash's bytes
                    byte[] bytes = md.digest();
                    //This bytes[] has bytes in decimal format;
                    //Convert it to hexadecimal format
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i< bytes.length ;i++)
                    {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    //Get complete hashed password in hex format
                    generatedPassword = sb.toString();
                    Log.e("asdfgh",generatedPassword);
                }
                catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }

                //String text = dB.getData().toString();

                Boolean login = dB.checkCredentils(loginUsername,generatedPassword);
                if(login)
                {
                    Log.e("asd","true");
                    editor.putString("username",loginUsername);
                    editor.commit();
                    String value = sharedpreferences.getString("username", "");
                    Log.e("Session value",value);

                    Toast.makeText(MainActivity.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                    TextView wrongPassTxt = findViewById(R.id.wrongPassTxt);
                    wrongPassTxt.setVisibility(View.INVISIBLE);
                    startActivity(loginIntent);
                    finish();

                }

                else
                {
                    Log.e("asd","false");
                    //Toast.makeText(MainActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();
                    TextView wrongPassTxt = findViewById(R.id.wrongPassTxt);
                    wrongPassTxt.setVisibility(View.VISIBLE);
                }



            }
        });

    }
}