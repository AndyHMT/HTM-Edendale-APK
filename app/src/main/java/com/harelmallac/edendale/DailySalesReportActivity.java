package com.harelmallac.edendale;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.DailySalesListAdapter;
import com.harelmallac.edendale.model.DailySalesListClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DailySalesReportActivity extends AppCompatActivity {

    ListView LVDSList;
    ArrayList<DailySalesListClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sales_report);

        LVDSList = findViewById(R.id.LVDSList);

        list.add(new DailySalesListClass("8", "Anchor fcmp sachet 1kg"));
        list.add(new DailySalesListClass("7", "Anchor fcmp sachet 1kg"));
        list.add(new DailySalesListClass("8", "Anchor fcmp sachet 1kg"));
        list.add(new DailySalesListClass("6", "Anchor fcmp sachet 1kg"));
        list.add(new DailySalesListClass("5", "Anchor fcmp sachet 1kg"));
        list.add(new DailySalesListClass("4", "Anchor fcmp sachet 1kg"));

        DailySalesListAdapter adapter = new DailySalesListAdapter(this, R.layout.daily_sales_list_layout, list);
        LVDSList.setAdapter(adapter);


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = findViewById(R.id.textView75);
        dateView.setText(date);

        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        TextView timeView = findViewById(R.id.textView76);
        timeView.setText(time);
    }
}