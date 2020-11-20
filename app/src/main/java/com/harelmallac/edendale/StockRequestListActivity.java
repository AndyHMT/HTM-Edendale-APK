package com.harelmallac.edendale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.SelectedProductAdapter;
import com.harelmallac.edendale.adapter.StockRequestProductAdapter;
import com.harelmallac.edendale.model.ProductLinesClass;
import com.harelmallac.edendale.model.StockRequestProductClass;

import java.util.ArrayList;

public class StockRequestListActivity extends AppCompatActivity {
    ArrayList<String> selectedProduct = new ArrayList<>();
    ArrayList<String> selectedQty = new ArrayList<>();
    private static ArrayList<StockRequestProductClass> stockList = new ArrayList<>();
    private StockRequestProductAdapter adapter;
    private static ListView lvStock;
    Context c = StockRequestListActivity.this;

    @Override
    public void onBackPressed() {

        stockList.clear();
        Intent mainIntent = new Intent( StockRequestListActivity.this, StockRequestActivity.class );
        startActivity(mainIntent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_request_listview);

        lvStock = findViewById(R.id.lvStock);

        if( getIntent().getExtras() != null) {
            selectedProduct = getIntent().getStringArrayListExtra("selectedProd");
            selectedQty = getIntent().getStringArrayListExtra("selectedQty");
        }

        if(selectedProduct != null) {
            for (int i = 0; i < selectedProduct.size(); i++) {
                stockList.add(new StockRequestProductClass(selectedProduct.get(i), selectedQty.get(i)));
            }
            adapter = new StockRequestProductAdapter(this, stockList);
            lvStock.setAdapter(adapter);
        }
    }

    public void delete(int position){
        stockList.remove(position);
        lvStock.invalidateViews();
    }
}