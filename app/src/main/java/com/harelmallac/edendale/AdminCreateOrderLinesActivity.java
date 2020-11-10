package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.harelmallac.edendale.adapter.AdCreateOrderLinesAdapter;
import com.harelmallac.edendale.model.AdCreateOrderLinesClass;

import java.util.ArrayList;

public class AdminCreateOrderLinesActivity extends AppCompatActivity {

    ListView LVCord;
    ArrayList<AdCreateOrderLinesClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_order_lines);

        LVCord = findViewById(R.id.LVCord);

        list.add(new AdCreateOrderLinesClass("product1","2", "5", "10"));
        list.add(new AdCreateOrderLinesClass("product2","2", "5", "10"));
        list.add(new AdCreateOrderLinesClass("product3","2", "5", "10"));
        list.add(new AdCreateOrderLinesClass("product4","2", "5", "10"));
        list.add(new AdCreateOrderLinesClass("product5","2", "5", "10"));

        AdCreateOrderLinesAdapter adapter = new AdCreateOrderLinesAdapter(this, R.layout.ad_create_order_lines_layout, list);
        LVCord.setAdapter(adapter);
    }
}