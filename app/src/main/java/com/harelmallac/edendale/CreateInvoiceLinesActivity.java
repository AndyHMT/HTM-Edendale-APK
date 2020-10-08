package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.harelmallac.edendale.model.ItemClass;

import java.util.ArrayList;

public class CreateInvoiceLinesActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity23";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice_lines);
        String customerName = null;
        String AmountOwned = null;
        String SalesSite = null;
        String Type = null;
        String Date = null;
        String SalesType = null;
        String ShippingAddress = null;
        ArrayList<String> listName = new ArrayList<>();
        ArrayList<String> listDis = new ArrayList<>();
        ArrayList<String> listQuan = new ArrayList<>();

        if( getIntent().getExtras() != null) {
            Toast.makeText(getApplicationContext(),"test1",Toast.LENGTH_SHORT);
//            customerName = getIntent().getStringExtra("cusName");
//            AmountOwned = getIntent().getStringExtra("AmountOwned");
//            SalesSite = getIntent().getStringExtra("SalesSite");
//            Type = getIntent().getStringExtra("Type");
//            Date = getIntent().getStringExtra("Date");
//            SalesType = getIntent().getStringExtra("SalesType");
//            ShippingAddress = getIntent().getStringExtra("shippingAddress");
            listName = getIntent().getStringArrayListExtra("list");
        }


        Toast.makeText(getApplicationContext(),listName.get(1),Toast.LENGTH_SHORT);

    }
}