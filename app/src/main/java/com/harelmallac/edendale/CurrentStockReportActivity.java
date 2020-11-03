package com.harelmallac.edendale;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.CurrentStockReportAdapter;
import com.harelmallac.edendale.model.CurrentStockReportClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CurrentStockReportActivity extends AppCompatActivity {

    ListView LVCSRList;
    ArrayList<CurrentStockReportClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_stock_report);
        LVCSRList = findViewById(R.id.LVCSRList);

        list.add(new CurrentStockReportClass("4", "Anchor fcmp sachet 10kg"));
        list.add(new CurrentStockReportClass("6", "Anchor fcmp sachet 12kg"));
        list.add(new CurrentStockReportClass("8", "Anchor fcmp sachet 13kg"));
        list.add(new CurrentStockReportClass("10", "Anchor fcmp sachet 14kg"));
        list.add(new CurrentStockReportClass("12", "Anchor fcmp sachet 15kg"));
        list.add(new CurrentStockReportClass("14", "Anchor fcmp sachet 16kg"));

        CurrentStockReportAdapter adapter = new CurrentStockReportAdapter(this, R.layout.daily_sales_list_layout, list);
        LVCSRList.setAdapter(adapter);


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = findViewById(R.id.CSRdate);
        dateView.setText(date);

        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        TextView timeView = findViewById(R.id.CSRtime);
        timeView.setText(time);
    }
}