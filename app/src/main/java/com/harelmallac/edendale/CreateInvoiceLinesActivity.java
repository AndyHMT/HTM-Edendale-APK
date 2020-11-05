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


import com.harelmallac.edendale.adapter.SelectedProductAdapter;
import com.harelmallac.edendale.adapter.TotalInfoAdapter;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.IdentityModel;
import com.harelmallac.edendale.model.InvoiceProductModel;
import com.harelmallac.edendale.model.ProductLinesAdapter;
import com.harelmallac.edendale.model.ProductLinesClass;
import com.harelmallac.edendale.model.TotalInfoClass;
//import com.harelmallac.edendale.service.Printing;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class CreateInvoiceLinesActivity extends AppCompatActivity {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static final String TAG = "MainActivity23";
    private static ArrayList<ProductLinesClass> ProList = new ArrayList<>();
    private static ArrayList<TotalInfoClass> totalList = new ArrayList<>();
    private static ArrayList<InvoiceProductModel> invoiceProductList = new ArrayList<>();

    private static Context context;
    private static ListView LVLines;
    private static ListView LVTotals;
    private SelectedProductAdapter adapter;
    DataBaseHelper db = new DataBaseHelper(this);
   // Printing print = new Printing(this, CreateInvoiceLinesActivity.this);

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
        ArrayList<String> selectedProduct = new ArrayList<>();
        ArrayList<String> selectedPid = new ArrayList<>();
        ArrayList<String> selectedTotal = new ArrayList<>();
        ArrayList<String> selectedDiscount = new ArrayList<>();
        ArrayList<String> selectedQuantity = new ArrayList<>();

        Log.e("Selected product", selectedProduct+"");
        Log.e("Selected product", selectedProduct+"");
        if( getIntent().getExtras() != null) {
            customerName = getIntent().getStringExtra("cusName");
            AmountOwned = getIntent().getStringExtra("AmountOwned");
            SalesSite = getIntent().getStringExtra("SalesSite");
            Type = getIntent().getStringExtra("Type");
            Date = getIntent().getStringExtra("Date");
            SalesType = getIntent().getStringExtra("SalesType");
            ShippingAddress = getIntent().getStringExtra("shippingAddress");
            selectedPid = getIntent().getStringArrayListExtra("productId");
            selectedProduct = getIntent().getStringArrayListExtra("productName");
            selectedTotal = getIntent().getStringArrayListExtra("productTotal");
            selectedDiscount = getIntent().getStringArrayListExtra("productDiscount");
            selectedQuantity = getIntent().getStringArrayListExtra("productQuantity");

        }

        LVLines = findViewById(R.id.LVLines);
        LVTotals = findViewById(R.id.LVTotals);

        while (totalList.size() != 0){
            totalList.remove(0);
        }

        totalList.add(new TotalInfoClass("Total Excl. Vat Rs:", "0"));
        totalList.add(new TotalInfoClass("Total Discount Rs:", "0"));
        totalList.add(new TotalInfoClass("Total Vat Amount Rs", "0"));
        totalList.add(new TotalInfoClass("Total Incl Vat Rs", "0"));

        TotalInfoAdapter totalAdapter = new TotalInfoAdapter(this, R.layout.total_info_layout, totalList);
        LVTotals.setAdapter(totalAdapter);

        double total = 0.00;
        double discount = 0.00;
        if(selectedProduct != null) {
            for (int i = 0; i < selectedProduct.size(); i++) {
                //#Varun - Update prodlist by auto-calculate total based on quantity selected
                ProList.add(new ProductLinesClass(selectedPid.get(i), selectedProduct.get(i), selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), calculateTotal(selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), selectedDiscount.get(i)), selectedDiscount.get(i)));

                //#Varun - Calculate Total Exclu VAT
                total = total + Double.parseDouble(calculateTotal(selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), selectedDiscount.get(i)));
                totalList.get(0).setNum(df2.format(total)+"");
                totalList.get(3).setNum(df2.format(total)+"");

                //#Varun - Calculate total discount
                discount = discount + (Double.parseDouble(selectedDiscount.get(i))/100) * (Double.parseDouble(getProductPrice(selectedProduct.get(i))));
                totalList.get(1).setNum(df2.format(discount)+"");

                //#Varun - Calculate VAT amount
                //Log.e("discount", discount+"");
            }

            adapter = new SelectedProductAdapter(this, ProList);
            LVLines.setAdapter(adapter);

        }


        Button btnCreateInvoice = findViewById(R.id.btnCreate);
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //#Varun - invoice number
                generateInvoiceNumber();

                if(ProList != null) {
                    for (int i = 0; i < ProList.size(); i++) {

                        //#Varun - Update product qty upon create receipt
                        updateQty(ProList.get(i).getName(), ProList.get(i).getQty());

                        Double selectedQty =  Double.parseDouble(ProList.get(i).getQty());
                        Double discountedPrice = (Double.parseDouble(ProList.get(i).getPrice())) - ((Double.parseDouble(ProList.get(i).getPrice()) * Double.parseDouble(ProList.get(i).getDiscount())/100));

                        //#Varun - Insert into tbl_invoice
                        invoiceProductList.add(new InvoiceProductModel(discountedPrice, Double.parseDouble(ProList.get(i).getDiscount())/100, 100.00, generateInvoiceNumber(), new IdentityModel(ProList.get(i).getId()), selectedQty, 20));
                    }
                    db.createInvoice(invoiceProductList);
                }

                //#Varun - Clear listview upon create invoice
                ProList.clear();
                totalList.get(0).setNum("0.00");
                totalList.get(1).setNum("0.00");
                totalList.get(2).setNum("0.00");
                totalList.get(3).setNum("0.00");
                LVLines.invalidateViews();
                LVTotals.invalidateViews();

                //print.checkBluetooth();
                //print.feintBluetoothDeviceDiscovery();
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
    public String calculateTotal(String quantity, String price, String discount) {
        double percentageDisc, discountedPrice, total;
        percentageDisc = Double.parseDouble(discount) / 100;
        discountedPrice = Double.parseDouble(price) - (Double.parseDouble(price) * percentageDisc);
        total = (Double.parseDouble(quantity) * discountedPrice);
        return df2.format(total);
    }

    //#Varun - update qty in tbl_selectedinvoice
    public void updateQty(String productName, String qty) {
        int initalQty = db.getProductQty(db.getProductId(productName));
        int newQty = initalQty - Integer.parseInt(qty);
        db.updateSelectedProductQty(db.getProductId(productName), newQty+"");
    }

    //#Varun - Generate Invoice Number
    public String generateInvoiceNumber() {
        db.createTblInvoice();
        int count = 0;
        String InvoiceNum;
        String Uid = "SR00010";
        DateFormat df = new SimpleDateFormat("ddMMyy");
        String date = df.format(Calendar.getInstance().getTime());
        //INV+SR00010+170920+1
        if(db.getInvoiceCount() == 0) {
            count = 1;
            InvoiceNum = "INV"+Uid+date+count;
        }else{
            count = db.getInvoiceCount() + 1;
            InvoiceNum = "INV"+Uid+date+count;
        }
        return InvoiceNum;
    }

    //#Varun - Generate Delivery Number
    public String generateDeliveryNumber() {
        db.createTblInvoice();
        int count = 0;
        String DeliveryNum;
        String Uid = "SR00010";
        DateFormat df = new SimpleDateFormat("ddMMyy");
        String date = df.format(Calendar.getInstance().getTime());
        //DEL+SR00010+170920+1
        if(db.getInvoiceCount() == 0) {
            count = 1;
            DeliveryNum = "DEL"+Uid+date+count;
        }else{
            count = db.getInvoiceCount() + 1;
            DeliveryNum = "DEL"+Uid+date+count;
        }
        return DeliveryNum;
    }

    public void delete(int position){
        //remove item from itemlist based on index
        double total = 0.00;
        double discount = 0.00;
        for(int i = 0; i < ProList.size(); i++){
            total = total + Double.parseDouble(ProList.get(i).getTotal());
            discount = discount + ((Double.parseDouble(ProList.get(i).getDiscount())/100) * (Double.parseDouble(ProList.get(i).getPrice())));
        }

        double newTotal = total - Double.parseDouble(ProList.get(position).getTotal());
        double newDiscount = discount - ((Double.parseDouble(ProList.get(position).getDiscount())/100) * (Double.parseDouble(ProList.get(position).getPrice())));
        ProList.remove(position);

        //Update Total Exclu VAT on remove
        totalList.get(0).setNum(df2.format(newTotal)+"");
        totalList.get(1).setNum(df2.format(newDiscount)+"");
        totalList.get(3).setNum(df2.format(newTotal)+"");

        //Update listview based on item position
        LVLines.invalidateViews();
        LVTotals.invalidateViews();
    }

}


