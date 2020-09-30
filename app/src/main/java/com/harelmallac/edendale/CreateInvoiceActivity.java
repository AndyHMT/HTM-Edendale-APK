package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormatSymbols;

public class CreateInvoiceActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ListView LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);

        LV = findViewById(R.id.cusListView);

        String[] months = new DateFormatSymbols().getMonths();
        //ArrayAdapter<String> cusListViewAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, months);
        ArrayAdapter<String> cusListViewAdapter = new ArrayAdapter<>(this, R.layout.customer_list_view_layout, months);
        LV.setAdapter(cusListViewAdapter);
        LV.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String month = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(),month,Toast.LENGTH_SHORT).show();
    }
}