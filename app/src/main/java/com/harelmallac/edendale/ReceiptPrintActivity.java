package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        final TextView TimeView = findViewById(R.id.timeView);
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm", Locale.getDefault());
        String currentTimeHour = sdf.format(new Date());
//        sdf = new SimpleDateFormat("mm", Locale.getDefault());
//        String currentTimeMin = sdf.format(new Date());
        TimeView.setText(currentTimeHour);


        Spinner spinnerPayType = findViewById(R.id.spinner2);

        spinnerPayType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("Cash"))
                {
                    Log.d("check", "cash");
                    TextView chequeNumView = findViewById(R.id.chequeNumView);
                    chequeNumView.setVisibility(View.INVISIBLE);

                    TextView bankView = findViewById(R.id.bankView);
                    bankView.setVisibility(View.INVISIBLE);

                    EditText chequeNumEdit = findViewById(R.id.ChequeNumEdit);
                    chequeNumEdit.setVisibility(View.INVISIBLE);

                    Spinner spinner3 = findViewById(R.id.spinner3);
                    spinner3.setVisibility(View.INVISIBLE);

                    TextView arrow2 = findViewById(R.id.arrow2);
                    arrow2.setVisibility(View.INVISIBLE);
                }



                else if (selectedItem.equals("Cheque")){
                    Log.d("check", "cheque");

                    TextView chequeNumView = findViewById(R.id.chequeNumView);
                    chequeNumView.setVisibility(View.VISIBLE);

                    TextView bankView = findViewById(R.id.bankView);
                    bankView.setVisibility(View.VISIBLE);

                    EditText chequeNumEdit = findViewById(R.id.ChequeNumEdit);
                    chequeNumEdit.setVisibility(View.VISIBLE);

                    Spinner spinner3 = findViewById(R.id.spinner3);
                    spinner3.setVisibility(View.VISIBLE);

                    TextView arrow2 = findViewById(R.id.arrow2);
                    arrow2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


//        String payType = spinnerPayType.getSelectedItem().toString();
//        Log.d("check", payType);
//        if(payType.equals("Cash")){
//            Log.d("check", "cash");
//        }
//        else if (payType.equals("Cheque")){
//            Log.d("check", "cheque");
//        }
    }
}