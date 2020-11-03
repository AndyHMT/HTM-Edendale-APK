package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Button btnDayEndSummary = findViewById(R.id.btnDayEndSummary);
        btnDayEndSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportActivity.this, DayEndSummaryActivity.class);
                startActivity(intent);
            }
        });
    }
}