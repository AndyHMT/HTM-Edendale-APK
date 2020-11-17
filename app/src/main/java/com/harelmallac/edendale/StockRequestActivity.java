package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ListView;

import com.harelmallac.edendale.adapter.StockRequestMainAdapter;
import com.harelmallac.edendale.model.StockRequestMainClass;

import java.util.ArrayList;

public class StockRequestActivity extends AppCompatActivity {
    ListView LVstkMain;
    ArrayList<StockRequestMainClass> list = new ArrayList<>();

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_request);

        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.stock_request_popup);
        myDialog.show();



        LVstkMain = findViewById(R.id.LVstkRequest);
//
        list.add(new StockRequestMainClass("test1nodifosdifjoiwefwoefijwoifmwoefeiwoifw", "test1 qty"));
        list.add(new StockRequestMainClass("test2", "test2 qty"));
        list.add(new StockRequestMainClass("test3", "test3 qty"));
//
//
        StockRequestMainAdapter adapter = new StockRequestMainAdapter(this, R.layout.stock_request_main_list_layout, list);
        LVstkMain.setAdapter(adapter);

    }
}