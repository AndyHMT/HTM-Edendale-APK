package com.harelmallac.edendale;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ItemClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class DayEndSummaryActivity extends AppCompatActivity {

    TextView tvTypeCash, tvCountCash, tvAmtCash, tvTypeCredit, tvCountCredit, tvAmtCredit, tvCountCrn, tvAmtCrn, tvCashRep1, tvCashRep2, tvCashInHand, tvCheque, tvRemiAmt, tvCancelCreditCount, tvProductiveCall, tvCancelCash, tvCancelCredit, tvCancelCashCount, tvCancelCashQty, tvCancelCreditQty;
    DataBaseHelper db = new DataBaseHelper(this);
    Button btnPrint;
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile  boolean stopWorker;
    Context c = DayEndSummaryActivity.this;
    ArrayList<String> productivCalls = new ArrayList<>();
    ArrayList<String> tvCountCashArray = new ArrayList<>();
    ArrayList<String> tvCountCreditArray = new ArrayList<>();
    ArrayList<String> tvCountCrnArray = new ArrayList<>();
    ArrayList<String> tvAmtCashArray = new ArrayList<>();
    ArrayList<String> tvAmtCreditArray = new ArrayList<>();
    ArrayList<String> tvAmtCrnArray = new ArrayList<>();
    ArrayList<String> tvCashRep1Array = new ArrayList<>();
    ArrayList<String> tvCashRep2Array = new ArrayList<>();
    ArrayList<String> tvCashInHandArray = new ArrayList<>();
    ArrayList<String> tvChequeArray = new ArrayList<>();
    ArrayList<String> tvRemiAmtArray = new ArrayList<>();
    ArrayList<String> tvCancelCashCountArray = new ArrayList<>();
    ArrayList<String> tvCancelCashArray = new ArrayList<>();
    ArrayList<String> tvCancelCashQtyArray = new ArrayList<>();
    ArrayList<String> tvCancelCreditCountArray = new ArrayList<>();
    ArrayList<String> tvCancelCreditArray = new ArrayList<>();
    ArrayList<String> tvCancelCreditQtyArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_end_summary);
        tvCountCash = findViewById(R.id.textView25);
        tvCountCredit = findViewById(R.id.textView32);
        tvCountCrn = findViewById(R.id.textView39);
        tvTypeCash = findViewById(R.id.textView27);
        tvAmtCash = findViewById(R.id.textView26);
        tvTypeCredit = findViewById(R.id.textView34);
        tvAmtCredit = findViewById(R.id.textView33);
        tvAmtCrn = findViewById(R.id.textView40);
        tvCashRep1 = findViewById(R.id.textView44);
        tvCashRep2 = findViewById(R.id.textView45);
        tvCashInHand = findViewById(R.id.textView50);
        tvCheque = findViewById(R.id.textView51);
        tvRemiAmt = findViewById(R.id.textView52);
        tvProductiveCall = findViewById(R.id.textView68);
        tvCancelCashCount = findViewById(R.id.textView57);
        tvCancelCash = findViewById(R.id.textView58);
        tvCancelCashQty = findViewById(R.id.textView60);
        tvCancelCreditCount = findViewById(R.id.textView57);
        tvCancelCredit = findViewById(R.id.textView58);
        tvCancelCreditQty = findViewById(R.id.textView60);
        btnPrint = findViewById(R.id.button3);

        retrieveCashInvoices();
        retrieveAllInvoicesCount();
        retrieveCreditInvoices();
        retrieveCrnInvoices();
        retrieveSalesReceipt();
        retrieveCashInHand();
        retrieveCheque();
        retrieveRemittance();
        retrieveCancelledCashCount();
        retrieveCancelledCreditCount();
        retrieveCancelledCash();
        retrieveCancelledCredit();
        retrieveProductiveCalls();

        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());

        final String note = "\n\n\n           EDENDALE DISTRIBUTORS LTD\n        Anse Courtois, Les Pailles\n            Republic of Mauritius\n         Phone" +
                " : (230) 286 4920\n     Fax : (230) 286 4654/ (230) 286 9479\n         Vat Reg No : VAT20362266\n           Bus Reg No : C06064211\n\nDate : " + date + "\t" + time + "\nSalesMan :" + "Joe" + " \n\n------------------------------------------\nDAY END SUMMARY\n------------------------------------------\n" +
                "Type   : CASH\nCount  : " + tvCountCashArray.get(0) + "\nAmount : " + tvAmtCashArray.get(0) + "\n------------------------------------------\n" +
                "Type   : CREDIT\nCount  : " + tvCountCreditArray.get(0) + "\nAmount : " + tvAmtCreditArray.get(0) + "\n------------------------------------------\n" +
                "Type   : CRN\nCount  : " + tvCountCrnArray.get(0) + "\nAmount : " + tvAmtCrnArray.get(0) + "\n------------------------------------------\n" +
                "Cash Recp = " + tvCashRep1Array.get(0) + "\t" + tvCashRep2Array.get(0) + "\n------------------------------------------\n" +
                "CASH IN HAND = " + tvCashInHandArray.get(0) + "\nCheque = " + tvChequeArray.get(0) + "\nRemittance Amt = " + tvRemiAmtArray.get(0) + "\n------------------------------------------\n" +
                "CANCELLED TRANSACTIONS\n------------------------------------------\nCASH = " + tvCancelCashCountArray.get(0) + "\t" + tvCancelCashArray.get(0) + "   Qty : " + tvCancelCashQtyArray.get(0) + "\nCREDIT = " + tvCancelCreditCountArray.get(0) + "\t" + tvCancelCreditArray.get(0) + "   Qty : " + tvCancelCreditQtyArray.get(0) + "\n\nNo. Of Productive Call(s) : " + productivCalls.get(0) + "\n\n\n\n";

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FindBluetoothDevice();
                    openBluetoothPrinter();
                    printData(note);
                    cancelClick(c, note);
                    Thread.sleep(5000);
                    disconnectBt();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void cancelClick(final Context context, final String note) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("DAY END SUMMARY report")
                .setMessage("Do you want to print a copy?")
                .setPositiveButton("Yes", null)
                .setNegativeButton("Cancel", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FindBluetoothDevice();
                    openBluetoothPrinter();
                    printData(note);
                    Thread.sleep(5000);
                    disconnectBt();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        });
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DayEndSummaryActivity.this, ReportActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    public void retrieveCashInvoices() {
        String[] result = new String[4];
        Cursor res  = db.getAllInvoicesCashCount();
        double total;
        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cash summary data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                String amount = res.getString(1);
                String vat = res.getString(2);
                Boolean checkAmount = res.isNull(1);
                tvTypeCash.setText("Cash");
                if(checkAmount != true){
                    total = Double.parseDouble(amount) + Double.parseDouble(vat);
                    total = Math.round(total*100)/100.0;

                    tvAmtCash.setText(total+"");
                    tvAmtCashArray.add(total+"");
                }else {
                    tvAmtCash.setText("0.00");
                    tvAmtCashArray.add("0.00");

                    tvCountCash.setText("0");
                    tvCountCashArray.add("0");
                }
            }
        }
    }

    public void retrieveCrnInvoices() {
        Cursor res  = db.getAllInvoicesCrnCount();
        double total;
        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve crn summary data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                String amount = res.getString(0);
                String vat = res.getString(1);
                Boolean checkAmount = res.isNull(0);
                if(checkAmount != true){
                    total = Double.parseDouble(amount) + Double.parseDouble(vat);
                    total = Math.round(total*100)/100.0;

                    tvAmtCrn.setText(total+"");
                    tvAmtCrnArray.add(total+"");
                }else {
                    tvAmtCrn.setText("0.00");
                    tvAmtCrnArray.add("0.00");

                    tvCountCrn.setText("0");
                    tvCountCrnArray.add("0");
                }
            }
        }
    }

    public void retrieveCreditInvoices() {
        Cursor res  = db.getAllInvoicesCreditCount();
        double total;
        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve credit summary data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean checkAmount = res.isNull(1);
                String vat = res.getString(2);
                String amount = res.getString(1);

                tvTypeCredit.setText("Credit");
                if(checkAmount == true){
                    tvAmtCredit.setText("0.00");
                    tvAmtCreditArray.add("0.00");

                    tvCountCredit.setText("0");
                    tvCountCreditArray.add("0");
                }else {
                    total = Double.parseDouble(amount) + Double.parseDouble(vat);
                    total = Math.round(total*100)/100.0;
                    tvAmtCredit.setText(total+"");
                    tvAmtCreditArray.add(total+"");

                }
            }
        }
    }

    public void retrieveSalesReceipt() {
        Cursor res  = db.getAllSalesReceiptCount();
        double total;
        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve sales receipt summary data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean checkRepAmt = res.isNull(0);
                String repAmt = res.getString(0);
                String repCount = res.getString(1);

                if(checkRepAmt == true){
                    tvCashRep1.setText("0");
                    tvCashRep1Array.add("0");

                    tvCashRep2.setText("0.00");
                    tvCashRep2Array.add("0.00");
                }else {
                    tvCashRep1.setText(repCount+"");
                    tvCashRep1Array.add(repCount+"");

                    tvCashRep2.setText(repAmt+"");
                    tvCashRep2Array.add(repAmt+"");

                }
            }
        }
    }

    public void retrieveCashInHand() {
        Cursor res  = db.cashInHand();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cash in hand summary data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean cashRep = res.isNull(0);
                String cashReceipt = res.getString(0);

                if(cashRep == true){
                    tvCashInHand.setText("0.00");
                    tvCashInHandArray.add("0.00");
                }else {
                    Double cashRcp = Math.round(Double.parseDouble(cashReceipt) *100)/100.0;
                    tvCashInHand.setText(cashRcp+"");
                    tvCashInHandArray.add(cashRcp+"");
                }
            }
        }
    }

    public void retrieveCheque() {
        Cursor res  = db.getCheque();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cheque summary data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean chequeRep = res.isNull(0);
                String chequeReceipt = res.getString(0);

                if(chequeRep == true){
                    tvCheque.setText("0.00");
                    tvChequeArray.add("0.00");
                }else {
                    Double chequeRcp = Math.round(Double.parseDouble(chequeReceipt) *100)/100.0;
                    tvCheque.setText(chequeRcp+"");
                    tvChequeArray.add(chequeRcp+"");
                }
            }
        }
    }

    public void retrieveRemittance() {
        Cursor res  = db.getRemittance();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve remittance data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean remitanceRep = res.isNull(0);
                String remitanceReceipt = res.getString(0);

                if(remitanceRep == true){
                    tvRemiAmt.setText("0.00");
                    tvRemiAmtArray.add("0.00");
                }else {
                    Double remitanceRcp = Math.round(Double.parseDouble(remitanceReceipt) *100)/100.0;
                    tvRemiAmt.setText(remitanceRcp+"");
                    tvRemiAmtArray.add(remitanceRcp+"");
                }
            }
        }
    }

    public void retrieveCancelledCash() {
        Cursor res  = db.getCancelledCash();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cancelled cash data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean aCount = res.isNull(0);
                String qty = res.getString(1);
                String amount = res.getString(0);
                String vat = res.getString(2);
                String discount = res.getString(3);

                if(aCount == true){
                    tvCancelCash.setText("0.00");
                    tvCancelCashArray.add("0.00");

                    tvCancelCashQty.setText("0");
                    tvCancelCashQtyArray.add("0");
                }else {
                    Double total = (Double.parseDouble(amount) + Double.parseDouble(vat)) - Double.parseDouble(discount);
                    total = Math.round(total*100)/100.0;
                    tvCancelCash.setText(total+"");
                    tvCancelCashArray.add(total+"");

                    tvCancelCashQty.setText(qty+"");
                    tvCancelCashQtyArray.add(qty+"");
                }
            }
        }
    }

    public void retrieveCancelledCredit() {
        Cursor res  = db.getCancelledCredit();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cancelled credit data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean aCount = res.isNull(0);
                String qty = res.getString(1);
                String amount = res.getString(0);
                String vat = res.getString(2);
                String discount = res.getString(3);

                if(aCount == true){
                    tvCancelCredit.setText("0.00");
                    tvCancelCreditArray.add("0.00");

                    tvCancelCreditQty.setText("0");
                    tvCancelCreditQtyArray.add("0");
                }else {
                    Double total = (Double.parseDouble(amount) + Double.parseDouble(vat)) - Double.parseDouble(discount);
                    total = Math.round(total*100)/100.0;
                    tvCancelCredit.setText(total+"");
                    tvCancelCreditArray.add(total+"");

                    tvCancelCreditQty.setText(qty+"");
                    tvCancelCreditQtyArray.add(qty+"");
                }
            }
        }
    }

    public void retrieveCancelledCreditCount() {
        Cursor res  = db.getCancelledCreditCount();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cancelled credit data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean aCount = res.isNull(0);
                String totalCount = res.getString(0);

                if(aCount == true){
                    tvCancelCreditCount.setText("0");
                    tvCancelCreditCountArray.add("0");
                }else {
                    tvCancelCreditCount.setText(totalCount+"");
                    tvCancelCreditCountArray.add(totalCount+"");
                }
            }
        }
    }

    public void retrieveCancelledCashCount() {
        Cursor res  = db.getCancelledCreditCount();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve cancelled cash data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean aCount = res.isNull(0);
                String totalCount = res.getString(0);

                if(aCount == true){
                    tvCancelCashCount.setText("0");
                    tvCancelCashCountArray.add("0");
                }else {
                    tvCancelCashCount.setText(totalCount+"");
                    tvCancelCashCountArray.add(totalCount+"");
                }
            }
        }
    }

    public void retrieveProductiveCalls() {
        Cursor res  = db.getProductiveCalls();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve productive data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                Boolean tCount = res.isNull(0);
                String totalCount = res.getString(0);

                if(tCount == true){
                    tvProductiveCall.setText("0");
                    productivCalls.add("0");
                }else {
                    tvProductiveCall.setText(totalCount+"");
                    productivCalls.add(totalCount+"");
                }
            }
        }
    }

    public void retrieveAllInvoicesCount() {
        Cursor res  = db.getAllInvoicesCount();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve all invoices count data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                String type = res.getString(0);
                String total = res.getString(1);
                if(type.contains("cash")){
                    tvCountCash.setText(total);
                    tvCountCashArray.add(total);
                }else if(type.contains("credit")) {
                    tvCountCredit.setText(total);
                    tvCountCreditArray.add(total);
                }else if(type.contains("crn")){
                    tvCountCrn.setText(total);
                    tvCountCrnArray.add(total);
                }
            }

        }
    }

    //Start - Printing
    void FindBluetoothDevice(){
        try{
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if(bluetoothAdapter == null)
            {
                Log.e("Bluetooth Printer","No bluetooth device found");
            }
            if(bluetoothAdapter.isEnabled())
            {
                Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(enableBt,0);
            }
            Set<BluetoothDevice> pairedDevice = bluetoothAdapter.getBondedDevices();
            if(pairedDevice.size()>0)
            {
                for(BluetoothDevice pairedDev:pairedDevice)
                {
                    //My Bluetooth printer name is Eden
                    if(pairedDev.getName().equals("Mobile Printer"))
                    {
                        bluetoothDevice = pairedDev;
                        Toast.makeText(this, "Connected to"+pairedDev.getName(), Toast.LENGTH_SHORT).show();
                        Log.e("Connection State","Connected to"+pairedDev.getName());
                        break;
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public void openBluetoothPrinter() throws IOException
    {
        try
        {
            UUID uuidString = UUID.fromString(("00001101-0000-1000-8000-00805F9B34FB"));
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuidString);
            bluetoothSocket.connect();
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();
            beginListenData();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void beginListenData()
    {
        try{
            final Handler handler = new Handler();
            final byte delimiter = 10;
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!Thread.currentThread().isInterrupted()  && !stopWorker)
                    {
                        try{
                            int byteAvailable = inputStream.available();
                            if(byteAvailable>0)
                            {
                                byte[] packetByte = new byte[byteAvailable];
                                inputStream.read(packetByte);
                                for (int i=0; i<byteAvailable; i++)
                                {
                                    byte b = packetByte[i];
                                    if(b==delimiter)
                                    {
                                        byte[] encodeByte = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer,0,
                                                encodeByte,0,
                                                encodeByte.length
                                        );
                                        final String data = new String(encodeByte,"US-ASCII");
                                        readBufferPosition = 0;
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.e("Data","Data = "+data);
                                            }
                                        });
                                    }
                                    else
                                    {
                                        readBuffer[readBufferPosition++]=b;
                                    }
                                }
                            }
                        }catch (Exception ex)
                        {
                            stopWorker = true;
                            ex.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void printData (String msg) throws IOException
    {
        try {
            outputStream.write(msg.getBytes(Charset.forName("UTF-8")));
            Toast.makeText(this, "Printing Text...", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    //
    public void disconnectBt() throws IOException
    {
        try{
            stopWorker = true;
            outputStream.close();
            inputStream.close();
            if (bluetoothAdapter != null)
            {
                bluetoothAdapter.cancelDiscovery();
            }
            // bluetoothSocket.close();
            Toast.makeText(this, "Printer Disconnected", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
    //End - Printing


}