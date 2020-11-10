package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReceiptPrintActivity extends AppCompatActivity {

    String cusName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_print);

        if( getIntent().getExtras() != null) {
            cusName = getIntent().getStringExtra("cusName");
        }

        TextView customerNameView = findViewById(R.id.customerNameView);
        customerNameView.setText(cusName);

        TextView DateView = findViewById(R.id.dateView);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        DateView.setText(date);

        TextView customerName = findViewById(R.id.customerNameView);
        EditText txtAmount = findViewById(R.id.editTextNumberDecimal);
        EditText txtCheckNum = findViewById(R.id.ChequeNumEdit);

        final TextView TimeView = findViewById(R.id.timeView);
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm", Locale.getDefault());
        String currentTimeHour = sdf.format(new Date());
//        sdf = new SimpleDateFormat("mm", Locale.getDefault());
//        String currentTimeMin = sdf.format(new Date());
        TimeView.setText(currentTimeHour);


        Spinner spinnerPayType = findViewById(R.id.spinner2);
        Spinner spinner = findViewById(R.id.spinner3);

        String dateValue = DateView.getText().toString();
        String timeValue =TimeView.getText().toString();
        String customer = customerName.getText().toString();
        final String paymentType = spinnerPayType.getSelectedItem().toString();
        String amount = txtAmount.getText().toString();
        String checkNum = txtCheckNum.getText().toString();
        String bank = txtCheckNum.getText().toString();




        Button btnPrint = findViewById(R.id.button2);

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (paymentType.equals("Cash"))
                {

                }

            }
        });





//        }
    }
}