package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AdminCreateOrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_order_details);
        Spinner customerAddress = findViewById(R.id.customerAddress);
        String customerName = null;
        if (getIntent().getExtras() != null) {
            customerName = getIntent().getStringExtra("cusName");
        }
        TextView customerNameDisplay = findViewById(R.id.customerNameDisplay);
        customerNameDisplay.setText(customerName);
        //=========================================================================

        ArrayList<String> addresslist = new ArrayList<String>();

        DataBaseHelper db = new DataBaseHelper(this);
        Cursor cursor = db.getAddress(customerName);
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Not able to retrieve customer Address", Toast.LENGTH_SHORT).show();
        } else {
            //int i = 0;
            while (cursor.moveToNext()) {
                //Toast.makeText(getApplicationContext(),cursor.getString(0),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),cursor.getString(1),Toast.LENGTH_SHORT).show();
                //i += 1;


                String address = cursor.getString(3) + " " + cursor.getString(4);
                addresslist.add(address);


                //addresslist.add(cursor.getString(4));
                //productslist.add(i + "|  " + cursor.getString(1));
            }
//        addresslist.add("Hello");
            ArrayAdapter<String> spinnerArrayAdapter;
            spinnerArrayAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, addresslist);
            customerAddress.setAdapter(spinnerArrayAdapter);
        }


        //=========================================================================


        Cursor cursor1 = db.getCustomerCreateInvoiceInfo(customerName);
        if (cursor1.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "Not able to retrieve customer information", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor1.moveToNext()) {
//                Toast.makeText(getApplicationContext(), "zx", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), cursor1.getString(7), Toast.LENGTH_SHORT).show();
                TextView AmountOwnedDis = findViewById(R.id.AmountOwnedDis);
                if (cursor1.getString(7).equals("null")) {
                    AmountOwnedDis.setText("0");
                } else {
                    AmountOwnedDis.setText(cursor1.getString(7));
                }

            }
        }

        //=========================================================================
        String SalesSite = "Edendale Distribution Ltd";
        final TextView SalesSiteDis = findViewById(R.id.SalesSiteDis);
        SalesSiteDis.setText(SalesSite);
        //=========================================================================
        final TextView DateDis = findViewById(R.id.DateDis);
        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        DateDis.setText(formattedDate);
        //=========================================================================
        String Type = "Invoice";
        TextView TypeDis = findViewById(R.id.TypeDis);
        TypeDis.setText(Type);
        //=========================================================================
        final String SalesType = "Cash";
        TextView SalesTypeDis = findViewById(R.id.SalesTypeDis);
        SalesTypeDis.setText(SalesType);
        //=========================================================================
        final EditText poNum = findViewById(R.id.EdTxtPOnum);

        //=========================================================================

        Button butcusInfo = findViewById(R.id.ButCusInfo);
        butcusInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView customerNameDisplay = findViewById(R.id.customerNameDisplay);
                TextView AmountOwnedDisplay = findViewById(R.id.AmountOwnedDis);
                TextView TypeDis = findViewById(R.id.TypeDis);
                TextView SalesTypeDis = findViewById(R.id.SalesTypeDis);
                Spinner ShippingAddress = findViewById(R.id.customerAddress);
                Intent intent = new Intent(AdminCreateOrderDetailsActivity.this, AdminCreateOrderLinesActivity.class);
                intent.putExtra("cusName", customerNameDisplay.getText().toString());
                intent.putExtra("AmountOwned", AmountOwnedDisplay.getText().toString());
                intent.putExtra("SalesSite", SalesSiteDis.getText().toString());
                intent.putExtra("Type", TypeDis.getText().toString());
                intent.putExtra("Date", DateDis.getText().toString());
                intent.putExtra("SalesType", SalesTypeDis.getText().toString());
                intent.putExtra("shippingAddress", ShippingAddress.getSelectedItem().toString());
                if (poNum.getText().toString().equals("") == false) {
                    intent.putExtra("poNum", poNum.getText().toString());
                }
                startActivity(intent);
            }
        });


    }

}