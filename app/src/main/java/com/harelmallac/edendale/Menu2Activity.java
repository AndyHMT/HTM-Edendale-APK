package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        Button btnComplaints2 = findViewById(R.id.btnComplaints2);
        btnComplaints2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, AdminComplaintActivity.class);
                startActivity(intent);
            }
        });


        Button btnStockTake = findViewById(R.id.btnStockTake);
        btnStockTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, AdminStockTakeActivity.class);
                startActivity(intent);
            }
        });


        Button btnRejectedOrder = findViewById(R.id.btnRejectedOrder);
        btnRejectedOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, AdminSearchRejectedOrderActivity.class);
                startActivity(intent);
            }
        });


        Button btnSalesReport = findViewById(R.id.btnSalesReport);
        btnSalesReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, AdminSalesReportActivity.class);
                startActivity(intent);
            }
        });


        Button btnCreateOrder = findViewById(R.id.btnCreateOrder);
        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, AdminCreateOrderActivity.class);
                startActivity(intent);
            }
        });


        Button btnSearchOrder = findViewById(R.id.btnSearchOrder);
        btnSearchOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu2Activity.this, AdminCreateOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}