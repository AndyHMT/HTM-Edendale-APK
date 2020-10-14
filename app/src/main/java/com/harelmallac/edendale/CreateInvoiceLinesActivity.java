package com.harelmallac.edendale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.harelmallac.edendale.model.ProductLinesAdapter;
import com.harelmallac.edendale.model.ProductLinesClass;

import java.util.ArrayList;

public class CreateInvoiceLinesActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity23";
    private static ArrayList<ProductLinesClass> ProList = new ArrayList<>();
    private ArrayList<ProductLinesClass> ProList1 = new ArrayList<>();
    public static ProductLinesAdapter adapter1;
    private static Context context;
    private static ListView LVLines;
    ProductLinesAdapter adapter;




    public void delete(int position){

        if (ProList.isEmpty())
        {
            Log.e("print", "Empty");

        }
        else
        {
            ProList.remove(position);
            adapter.notifyDataSetChanged();
        }

//        ProductLinesAdapter adapter = new ProductLinesAdapter(this, R.layout.list_view_lines_layout, ProList);
//        LVLines.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice_lines);
        String customerName = null;
        String AmountOwned = null;
        String SalesSite = null;
        String Type = null;
        String Date = null;
        String SalesType = null;
        String ShippingAddress = null;
        ArrayList<String> listName = new ArrayList<>();
        ArrayList<String> listTotal = new ArrayList<>();
        ArrayList<String> listDis = new ArrayList<>();
        ArrayList<String> listQuan = new ArrayList<>();


        if( getIntent().getExtras() != null) {
//            customerName = getIntent().getStringExtra("cusName");
//            AmountOwned = getIntent().getStringExtra("AmountOwned");
//            SalesSite = getIntent().getStringExtra("SalesSite");
//            Type = getIntent().getStringExtra("Type");
//            Date = getIntent().getStringExtra("Date");
//            SalesType = getIntent().getStringExtra("SalesType");
//            ShippingAddress = getIntent().getStringExtra("shippingAddress");
            listName = getIntent().getStringArrayListExtra("listName");
            listTotal = getIntent().getStringArrayListExtra("listTotal");
            listDis = getIntent().getStringArrayListExtra("listDiscount");
            listQuan = getIntent().getStringArrayListExtra("listQuantity");
        }


//            Log.e("print", listName.get(0) + " " + listTotal.get(0) + " " + listDis.get(0) + " "+ listQuan.get(0));
//            Log.e("print", listName.get(1));

        LVLines = findViewById(R.id.LVLines);

        if(listName != null) {
            for (int i = 0; i < listName.size(); i++) {
                ProList.add(new ProductLinesClass(listName.get(i), listQuan.get(i), "50", "55"));

            }



            ProductLinesAdapter adapter = new ProductLinesAdapter(this, R.layout.list_view_lines_layout, ProList);
            LVLines.setAdapter(adapter);

        }





        //===================================================================================

        Button butAdd = findViewById(R.id.butAdd);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateInvoiceLinesActivity.this, CreateInvoiceListOfItemsActivity.class);
                startActivity(intent);
            }
        });

        //===================================================================================




    }



//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//        view = (View) parent.getItemAtPosition(position);
//
//        Button cancel = view.findViewById(R.id.ProductCancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProList.remove(position);
//                ProductLinesAdapter adapter = new ProductLinesAdapter(context, R.layout.list_view_lines_layout, ProList);
//                LVLines.setAdapter(adapter);
//            }
//        });
//    }
}


