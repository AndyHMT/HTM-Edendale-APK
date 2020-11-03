package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {
    Button btnDaySummary, btnRemittance, btnCheque, btnDailySales, btnTruckStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        btnDaySummary = findViewById(R.id.btnDayEndSummary);
        btnDaySummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, DayEndSummaryActivity.class);
                startActivity(intent);
            }
        });

        btnRemittance = findViewById(R.id.btnRemittance);
        btnRemittance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, RemittanceActivity.class);
                startActivity(intent);
            }
        });

        btnCheque = findViewById(R.id.btnCheque);
        btnCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, ChequeReportActivity.class);
                startActivity(intent);
            }
        });

        btnDailySales = findViewById(R.id.btnDailySales);
        btnDailySales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, DailySalesReportActivity.class);
                startActivity(intent);
            }
        });

        btnTruckStock = findViewById(R.id.btnTruckStock);
        btnTruckStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, CurrentStockReportActivity.class);
                startActivity(intent);
            }
        });
    }
}