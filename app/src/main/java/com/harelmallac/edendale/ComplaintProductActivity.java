package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ComplaintProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_product);
        Spinner spinner = findViewById(R.id.spinner);



        Button butNext = findViewById(R.id.buttonProduct);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cusName = null;
                String cusAddress = null;
                String cusPhone = null;
                String cusEmail = null;

                Spinner ProductSpinner = findViewById(R.id.spinner);
                EditText productName = findViewById(R.id.productName);
                EditText productDescription = findViewById(R.id.productDescription);
                EditText purchaseDate = findViewById(R.id.purchaseDate);
                EditText placeOfPurchase = findViewById(R.id.placeOfPurchase);

                if( getIntent().getExtras() != null)
                {
                    cusName = getIntent().getStringExtra("cusName");
                    cusAddress = getIntent().getStringExtra("cusAddress");
                    cusPhone = getIntent().getStringExtra("cusPhone");
                    cusEmail = getIntent().getStringExtra("cusEmail");
                }

                String proSpinner = ProductSpinner.getSelectedItem().toString();
                String proName = productName.getText().toString();
                String proDescription = productDescription.getText().toString();
                String purDate = purchaseDate.getText().toString();
                String placeOfPur = placeOfPurchase.getText().toString();

                if(proName.isEmpty()==false && proDescription.isEmpty()==false && purDate.isEmpty()==false && placeOfPur.isEmpty()==false) {
                    Intent intent = new Intent(ComplaintProductActivity.this, ComplaintDescriptionActivity.class);
                    intent.putExtra("cusName", cusName);
                    intent.putExtra("cusAddress", cusAddress);
                    intent.putExtra("cusPhone", cusPhone);
                    intent.putExtra("cusEmail", cusEmail);
                    intent.putExtra("proSpinner", proSpinner);
                    intent.putExtra("proName", proName);
                    intent.putExtra("proDescription", proDescription);
                    intent.putExtra("purDate", purDate);
                    intent.putExtra("placeOfPur", placeOfPur);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill in all of the fields",Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(),ProductSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),cusName,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),cusAddress,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),cusPhone,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),cusEmail,Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}