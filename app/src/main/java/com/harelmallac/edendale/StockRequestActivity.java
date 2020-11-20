package com.harelmallac.edendale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.StockRequestMainAdapter;
import com.harelmallac.edendale.database.ApiRequest;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.StockRequestMainClass;

import java.util.ArrayList;

public class StockRequestActivity extends AppCompatActivity {
    ListView LVstkMain;
    Button btnProceed;
    ArrayList<StockRequestMainClass> list = new ArrayList<>();
    DataBaseHelper db = new DataBaseHelper(this);
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_request);

        dialogRequest(this);

        LVstkMain = findViewById(R.id.LVstkRequest);

        StockRequestMainAdapter adapter = new StockRequestMainAdapter(this, list);
        LVstkMain.setAdapter(adapter);

        btnProceed = findViewById(R.id.button3);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> aQty = new ArrayList<>();
                ArrayList<String> aProductName = new ArrayList<>();
                for(int i = 0; i < list.size(); i++){
                    String productName = list.get(i).getStkprodName();
                    String qty = list.get(i).getStkprodQty();
                    if ((qty != "0")) {
                        Log.e("selected values ", "Product: "+productName+"Quantity: "+qty);
                        aQty.add(qty);
                        aProductName.add(productName);
                    }
                }
                Intent intent = new Intent(StockRequestActivity.this, StockRequestListActivity.class);
                intent.putExtra("selectedProd", aProductName);
                intent.putExtra("selectedQty", aQty);
                startActivity(intent);
            }
        });

    }

    public void dialogRequest(final Context context) {
        final androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Stock Request")
                .setMessage("Do you want to perform a transfer request?")
                .setPositiveButton("Receive Stock", null)
                .setNegativeButton("Requisition", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.getProducts(context);
                dialog.dismiss();
                list.add(new StockRequestMainClass("", ""));
                Intent intent = new Intent(StockRequestActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getAllProducts();
                dialog.dismiss();
                if(res.getCount() < 0){
                    Toast.makeText(getApplicationContext(),"Not able to retrieve product data",Toast.LENGTH_SHORT).show();
                }
                else {
                    while (res.moveToNext()){
                        String productName = res.getString(0);
                        list.add(new StockRequestMainClass(productName, "0"));
                    }
                }
                LVstkMain.invalidateViews();
            }
        });
    }


}