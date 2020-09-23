package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

public class StockRequestActivity extends AppCompatActivity {

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_request);

        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.stock_request_popup);
        myDialog.show();
    }
}