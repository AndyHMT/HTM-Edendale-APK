package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.harelmallac.edendale.adapter.AdCreateOrderProductAdapter;
import com.harelmallac.edendale.model.AdCreateOrderLinesClass;
import com.harelmallac.edendale.model.AdCreateOrderProductClass;

import java.util.ArrayList;

public class AdminCreateOrderLinesProductActivity extends AppCompatActivity {

    ListView LVCordPro;
    ArrayList<AdCreateOrderProductClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_order_lines_product);

        LVCordPro = findViewById(R.id.LVCordPro);

        list.add(new AdCreateOrderProductClass("product 1", "25", "10"));
        list.add(new AdCreateOrderProductClass("product 2", "25", "10"));
        list.add(new AdCreateOrderProductClass("product 3", "25", "10"));
        list.add(new AdCreateOrderProductClass("product 4", "25", "10"));
        list.add(new AdCreateOrderProductClass("product 5", "25", "10"));

        AdCreateOrderProductAdapter adapter = new AdCreateOrderProductAdapter(this, R.layout.ad_create_order_lines_product_layout, list);
        LVCordPro.setAdapter(adapter);
    }
}