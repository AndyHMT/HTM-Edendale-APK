package com.harelmallac.edendale;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.StockRequestProductAdapter;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.StockRequestProductClass;

import java.util.ArrayList;

public class StockRequestProductListActivity extends AppCompatActivity {
    ListView LVprods;
    ArrayList<StockRequestProductClass> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_request_product_list);

        LVprods = findViewById(R.id.LVproducts);


//        ==============================================================
        DataBaseHelper db = new DataBaseHelper(this);
        Cursor res = db.getProduct();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve product data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                String productName = res.getString(1);
                list.add(new StockRequestProductClass(productName, "0"));
            }

        }
//        ======================================================================


        StockRequestProductAdapter adapter = new StockRequestProductAdapter(this, R.layout.stock_product_list_layout, list);
        LVprods.setAdapter(adapter);

    }
}