package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReceiptPrintActivity extends AppCompatActivity {

    String cusName;

    @Override
    public void onBackPressed() {

        Intent mainIntent = new Intent( ReceiptPrintActivity.this, MenuActivity.class );
        startActivity(mainIntent);
    }

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
        EditText txtChequeNum = findViewById(R.id.ChequeNumEdit);

        final TextView TimeView = findViewById(R.id.timeView);
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm", Locale.getDefault());
        String currentTimeHour = sdf.format(new Date());
//        sdf = new SimpleDateFormat("mm", Locale.getDefault());
//        String currentTimeMin = sdf.format(new Date());
        TimeView.setText(currentTimeHour);


        Spinner spinnerPayType = findViewById(R.id.spinner2);
        Spinner spinner = findViewById(R.id.spinner3);


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





        String dateValue = DateView.getText().toString();
        String timeValue =TimeView.getText().toString();
        final String customer = customerName.getText().toString();
//        final String paymentType = spinnerPayType.getSelectedItem().toString();
//        final String amount = txtAmount.getText().toString();
        final String chequeNum = txtChequeNum.getText().toString();
        final String bank = spinner.getSelectedItem().toString();


        final TextView amount2 = findViewById(R.id.editTextNumberDecimal);
        Button butPrint = findViewById(R.id.button2);
        butPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spinnerPayType2 = findViewById(R.id.spinner2);
                String paymentType = spinnerPayType2.getSelectedItem().toString();
                if (paymentType.equals("Cash")){
                    Log.d("amount", amount2.getText().toString());
                    DataBaseHelper db = new DataBaseHelper(getApplicationContext());
                    db.addSaleReceipt(customer, paymentType, amount2.getText().toString(),  null, null);
                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                }
                else if (paymentType.equals("Cheque")){

                    DataBaseHelper db = new DataBaseHelper(getApplicationContext());
                    db.addSaleReceipt(customer, paymentType, amount2.getText().toString(),  chequeNum, bank);
                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();

                }

            }
        });




//        Button btnPrint = findViewById(R.id.button2);
//
//        btnPrint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d("amount", amount);
//
//                if (paymentType.equals("Cash"))
//                {
//                    Log.d("amount", amount);
//                    //DataBaseHelper db = new DataBaseHelper(getApplicationContext());
//                    //db.addSaleReceipt(customer, paymentType, amount,  null, null);
//
//                    //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                }
//
//                if (paymentType.equals("Cheque"))
//                {
////                    DataBaseHelper db = new DataBaseHelper(getApplicationContext());
////                    String response = db.addSaleReceipt(customer, paymentType, amount,  chequeNum, bank);
////                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });





//        }
    }
}