package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class ComplaintProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText purchaseDate;
    String productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_product);
        Spinner productName = findViewById(R.id.productName);
        final Calendar myCalendar = Calendar.getInstance();

        //=========================================================================

        //String productslist[] = {};
        ArrayList<String> productslist = new ArrayList<String>();


        DataBaseHelper db = new DataBaseHelper(this);
        Cursor cursor = db.getProduct();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve product data",Toast.LENGTH_SHORT).show();
        }
        else{
            //int i = 0;
            while(cursor.moveToNext()){
                productslist.add(cursor.getString(1));
            }
            ArrayAdapter<String> spinnerArrayAdapter;
            spinnerArrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, productslist);
            productName.setAdapter(spinnerArrayAdapter);
        }


        //=========================================================================
        Button butNext1 = findViewById(R.id.buttonProduct);
        purchaseDate = findViewById(R.id.purchaseDate);

        purchaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });

        purchaseDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                handleDateButton();
            }
        });

        butNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cusName = null;
                String cusAddress = null;
                String cusPhone = null;
                String cusEmail = null;

                Spinner ProductSpinner = findViewById(R.id.spinner);
                Spinner productName = findViewById(R.id.productName);
                EditText productDescription = findViewById(R.id.productDescription);



                EditText placeOfPurchase = findViewById(R.id.placeOfPurchase);

                if( getIntent().getExtras() != null)
                {
                    cusName = getIntent().getStringExtra("cusName");
                    cusAddress = getIntent().getStringExtra("cusAddress");
                    cusPhone = getIntent().getStringExtra("cusPhone");
                    cusEmail = getIntent().getStringExtra("cusEmail");
                }

                String proSpinner = ProductSpinner.getSelectedItem().toString();
                String proName = productName.getSelectedItem().toString();
                String proDescription = productDescription.getText().toString();
                String purDate = purchaseDate.getText().toString();
                String placeOfPur = placeOfPurchase.getText().toString();

                if(proDescription.isEmpty()==false && purDate.isEmpty()==false && placeOfPur.isEmpty()==false) {
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

            }

        });


        TextView customerView = findViewById(R.id.customerView);
        customerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ComplaintProductActivity.this, ComplaintFormActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void handleDateButton()
    {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String dateChosen= year + "/"+ (month+1) +"/"+dayOfMonth+"";
                purchaseDate.setText(dateChosen);

            }
        }, YEAR,MONTH,DATE);

        datePickerDialog.show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //==============================================================

    //==============================================================
}