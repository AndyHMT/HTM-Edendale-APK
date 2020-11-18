package com.harelmallac.edendale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.harelmallac.edendale.database.ApiRequest;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.database.PushToApi;

import org.json.JSONException;

public class MenuActivity extends AppCompatActivity {

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnSync = findViewById(R.id.btnSync);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Synchronize();
                final AlertDialog dialog = new AlertDialog.Builder(MenuActivity.this)
                        .setTitle("Sync Data")
                        .setMessage("All invoices and receipts will be DELETED locally. Do you want to continue?")
                        .setPositiveButton("Yes", null)
                        .setNegativeButton("Cancel", null)
                        .show();

                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        Synchronize();
                        final ProgressDialog pdialog = ProgressDialog.show(MenuActivity.this, "",
                                "Loading. Please wait while the application is synchronizing", true);
//                        new CountDownTimer(120000, 1000) {
                            new CountDownTimer(30000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onFinish() {
                                // TODO Auto-generated method stub

                                pdialog.dismiss();
                            }
                        }.start();
                    }
                });
            }
        });

        Button stockRequest = findViewById(R.id.btnStockRequest);
        stockRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, StockRequestActivity.class);
                startActivity(intent);
            }
        });

        Button btnComplaints = findViewById(R.id.btnComplaints);
        btnComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ComplaintFormActivity.class);
                startActivity(intent);
            }
        });

        Button btnCreateInvoice =findViewById(R.id.btnCreateInvoice);
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MenuActivity.this, CreateInvoiceActivity.class);
                startActivity(intent1);
            }
        });


        Button btnSearchInvoice = findViewById(R.id.btnSearchInvoice);
        btnSearchInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SearchInvoiceActivity.class);
                startActivity(intent);
            }
        });

        Button btnReport = findViewById(R.id.btnSearchInvoice2);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        Button btnReceipt = findViewById(R.id.btnReceipt);
        btnReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ReceiptCustomerListActivity.class);
                startActivity(intent);
            }
        });

        Button btnPush = findViewById(R.id.btnPush);
        btnPush.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    Push();
                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                    Log.e("Push Error",e.toString());
                }
            }
        });
    }

    public void Synchronize()
    {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.getProducts(this);
        apiRequest.getAllCustomers(this,"SR00010");
        apiRequest.getAllAdresses(this,"SR00010");
        apiRequest.getAllPrices(this,"SR00010","MV07");
        apiRequest.getVat(this);
        apiRequest.syncTransfer(this,"MV07");

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Push() throws JSONException, InterruptedException {
        PushToApi push = new PushToApi();

            push.postInvoicesHeader(this);
            Thread.sleep(2000);
            push.postInvoiceProducts(this);
            Thread.sleep(10000);
            //push.postSalesmanComplaints(this);

    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void Push() throws JSONException {
//        PushToApi push = new PushToApi();
//
//        try{
//            //push.postSalesmanComplaints(this);
//            push.postInvoicesHeader(this);
//        } catch (Exception e){
//            Log.e("Push Complaint Exception",e.toString());
//        }
//
//
//    }

}