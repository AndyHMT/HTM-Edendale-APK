package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.harelmallac.edendale.model.ChequeReportClass;
import com.harelmallac.edendale.model.ChequeReportListAdapter;
import com.harelmallac.edendale.model.DailySalesListAdapter;
import com.harelmallac.edendale.model.DailySalesListClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChequeReportActivity extends AppCompatActivity {

    ListView LVCRList;
    ArrayList<ChequeReportClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque_report);

        LVCRList = findViewById(R.id.LVCRList);

        list.add(new ChequeReportClass("1", "Anchor fcmp sachet 1kg"));
        list.add(new ChequeReportClass("2", "Anchor fcmp sachet 2kg"));
        list.add(new ChequeReportClass("3", "Anchor fcmp sachet 3kg"));
        list.add(new ChequeReportClass("4", "Anchor fcmp sachet 4kg"));
        list.add(new ChequeReportClass("5", "Anchor fcmp sachet 5kg"));
        list.add(new ChequeReportClass("5", "Anchor fcmp sachet 6kg"));

        ChequeReportListAdapter adapter = new ChequeReportListAdapter(this, R.layout.daily_sales_list_layout, list);
        LVCRList.setAdapter(adapter);


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = findViewById(R.id.CRdate);
        dateView.setText(date);

        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        TextView timeView = findViewById(R.id.CRtime);
        timeView.setText(time);
    }
}