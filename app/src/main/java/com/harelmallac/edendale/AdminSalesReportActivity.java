package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.harelmallac.edendale.adapter.AdSalesReportAdapter;
import com.harelmallac.edendale.model.AdSalesReportClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminSalesReportActivity extends AppCompatActivity {

    ListView LVAdSalesReport;
    ArrayList<AdSalesReportClass> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sales_report);

        LVAdSalesReport = findViewById(R.id.LVAdSalesReport);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = findViewById(R.id.textView98);
        dateView.setText(date);

        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        TextView timeView = findViewById(R.id.textView100);
        timeView.setText(time);

        list.add(new AdSalesReportClass("1", "milk"));
        list.add(new AdSalesReportClass("2", "salt"));

        AdSalesReportAdapter adapter = new AdSalesReportAdapter(this, R.layout.daily_sales_list_layout, list);
        LVAdSalesReport.setAdapter(adapter);

    }
}