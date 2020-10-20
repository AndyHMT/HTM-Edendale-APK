package com.harelmallac.edendale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ProductLinesAdapter;
import com.harelmallac.edendale.model.ProductLinesClass;
import com.harelmallac.edendale.service.Printing;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CreateInvoiceLinesActivity extends AppCompatActivity {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static final String TAG = "MainActivity23";
    private static ArrayList<ProductLinesClass> ProList = new ArrayList<>();
    private static Context context;
    private static ListView LVLines;
    DataBaseHelper db = new DataBaseHelper(this);
    Printing print = new Printing(this, CreateInvoiceLinesActivity.this);
    ProductLinesAdapter adapter;

    public void delete(int position){
        //remove item from itemlist based on index
        ProList.remove(position);

        //Update listview based on item position
        LVLines.invalidateViews();
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
            customerName = getIntent().getStringExtra("cusName");
            AmountOwned = getIntent().getStringExtra("AmountOwned");
            SalesSite = getIntent().getStringExtra("SalesSite");
            Type = getIntent().getStringExtra("Type");
            Date = getIntent().getStringExtra("Date");
            SalesType = getIntent().getStringExtra("SalesType");
            ShippingAddress = getIntent().getStringExtra("shippingAddress");
            listName = getIntent().getStringArrayListExtra("listName");
            listTotal = getIntent().getStringArrayListExtra("listTotal");
            listDis = getIntent().getStringArrayListExtra("listDiscount");
            listQuan = getIntent().getStringArrayListExtra("listQuantity");
        }

        LVLines = findViewById(R.id.LVLines);

        if(listName != null) {
            for (int i = 0; i < listName.size(); i++) {
                //#Varun - Update prodlist by auto-calculate total based on quantity selected
                ProList.add(new ProductLinesClass(listName.get(i), listQuan.get(i), getProductPrice(listName.get(i)), calculateTotal(listQuan.get(i), getProductPrice(listName.get(i)))));
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

        Button btnCreateInvoice = findViewById(R.id.btnCreate);
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProList != null) {
                    for (int i = 0; i < ProList.size(); i++) {
                        //#Varun - Update product qty upon create receipt
                        updateQty(ProList.get(i).getName(), ProList.get(i).getQty());
                        ProList.clear();
                        LVLines.invalidateViews();

                    }
                }
                //print.checkBluetooth();
                print.feintBluetoothDeviceDiscovery();
                //print.printData();
            }
        });
    }

    //#Varun - get product price
    public String getProductPrice(String productName) {
        String productId = db.getProductId(productName);
        double price = db.getProductPrice(productId);
        return df2.format(price);
    }

    //#Varun - calculate product total
    public String calculateTotal(String quantity, String price) {
        double total = Double.parseDouble(quantity) * Double.parseDouble(price);
        return df2.format(total);
    }

    //#Varun - update qty in tbl_selectedinvoice
    public void updateQty(String productName, String qty) {
        int initalQty = db.getProductQty(db.getProductId(productName));
        int newQty = initalQty - Integer.parseInt(qty);
        db.updateSelectedProductQty(db.getProductId(productName), newQty+"");
    }
}


