package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplaintFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_form);

        Button butNext = findViewById(R.id.button);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText customerName = findViewById(R.id.customerName);
                EditText customerAddress = findViewById(R.id.customerAddress);
                EditText customerPhone = findViewById(R.id.customerPhoneNo);
                EditText customerEmail = findViewById(R.id.customerEmail);

                String cusName = customerName.getText().toString();
                String cusAddress = customerAddress.getText().toString();
                String cusPhone = customerPhone.getText().toString();
                String cusEmail = customerEmail.getText().toString();


                if(cusName.isEmpty()==false && cusAddress.isEmpty()==false && cusPhone.isEmpty()==false && cusEmail.isEmpty()==false){
                    Boolean emailValidation = validate(cusEmail);
                    if(emailValidation==true) {
                        Intent intent = new Intent(ComplaintFormActivity.this, ComplaintProductActivity.class);
                        intent.putExtra("cusName", cusName);
                        intent.putExtra("cusAddress", cusAddress);
                        intent.putExtra("cusPhone", cusPhone);
                        intent.putExtra("cusEmail", cusEmail);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email not valid",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill in all of the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}