package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.harelmallac.edendale.adapter.AdSearchOrderAdapter;
import com.harelmallac.edendale.adapter.ChequeReportListAdapter;
import com.harelmallac.edendale.model.AdSearchOrderClass;
import com.harelmallac.edendale.model.ChequeReportClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminSearchOrderActivity extends AppCompatActivity {

    ListView LVSord;
    ArrayList<AdSearchOrderClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_order);

        LVSord = findViewById(R.id.LVSord);

        list.add(new AdSearchOrderClass("sam", "port louis"));
        list.add(new AdSearchOrderClass("Jane", "port louis"));
        list.add(new AdSearchOrderClass("Sarah", "port louis"));
        list.add(new AdSearchOrderClass("Albert", "port louis"));


        AdSearchOrderAdapter adapter = new AdSearchOrderAdapter(this, R.layout.ad_search_order_list_layout, list);
        LVSord.setAdapter(adapter);

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = findViewById(R.id.sOrdDate);
        dateView.setText(date);
    }
}