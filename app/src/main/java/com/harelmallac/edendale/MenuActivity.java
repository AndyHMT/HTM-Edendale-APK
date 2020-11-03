package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.harelmallac.edendale.database.ApiRequest;
import com.harelmallac.edendale.database.DataBaseHelper;

public class MenuActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnSync = findViewById(R.id.btnSync);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Synchronize();
            }
        });

        Button btnComplaints = findViewById(R.id.btnComplaints);
        btnComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ComplaintFormActivity.class);
                startActivity(intent);
            }
        });

        Button btnCreateInvoice =findViewById(R.id.btnCreateInvoice);
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MenuActivity.this, CreateInvoiceActivity.class);
                startActivity(intent1);
            }
        });

        Button btnReceipt = findViewById(R.id.btnReceipt);

        btnReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ReceiptCustomerListActivity.class);
                startActivity(intent);
            }
        });

        Button btnReport = findViewById(R.id.btnSearchInvoice2);

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

    }

    public void Synchronize()
    {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.getProducts(this);
        apiRequest.getAllCustomers(this,"SR00010");
        apiRequest.getAllAdresses(this,"SR00010");
        apiRequest.getAllPrices(this,"SR00010","MV07");
        apiRequest.getVat(this);
        apiRequest.syncTransfer(this,"MV07");

    }

}