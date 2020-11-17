package com.harelmallac.edendale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.adapter.SearchInvoiceAdapter;
import com.harelmallac.edendale.adapter.SearchInvoiceApater;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.SearchInvoiceClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class SearchInvoiceActivity extends AppCompatActivity {
    private static DecimalFormat df2 = new DecimalFormat("###.##");
    private static ListView LVsInv;
    private static ArrayList<SearchInvoiceClass> list = new ArrayList<>();
    private TextView tvDate;
    DataBaseHelper db = new DataBaseHelper(this);

    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile  boolean stopWorker;
    Context c = SearchInvoiceActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_invoice);
        db.getAllCurrentDayInvoices();
        tvDate = findViewById(R.id.textView10);
        tvDate.setText(currentDate());

        LVsInv = findViewById(R.id.LVsearchInv);

        //#Varun - Load all current invoices
        getCurrentInvoices();

        SearchInvoiceAdapter adapter = new SearchInvoiceAdapter(this, list);
        LVsInv.setAdapter(adapter);

        Button cancelPage = findViewById(R.id.button5);
        cancelPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchInvoiceActivity.this, SearchInvoiceCancelActivity.class);
                startActivity(intent);
            }
        });



    }



    public void previewClick(Context context, String txt){
        Intent intent = new Intent(context, SearchInvoicePreviewPageActivity.class);
        intent.putExtra("name",txt);
        context.startActivity(intent);

    }

    public void printSelected(Context context, String invoice){
        String[] splitText = invoice.split("\\r?\\n");
        String invoiceNumber = splitText[1];
        String customerName = "";
        String salesTypeId = "";
        String customerVatNo = "";
        String deliveryNumber = "";
        String address = "";
        String customerBrn = "";

        Cursor res1 = db.getInvoiceDetails(invoiceNumber);
        if(res1.getCount() < 0){
            Log.e("Error","Invoice Header details Not found.");
        }
        else {
            while (res1.moveToNext()){
                customerName = res1.getString(9);
                salesTypeId = res1.getString(13);
                customerVatNo = res1.getString(12);
                deliveryNumber = res1.getString(4);
                address = res1.getString(15);
                customerBrn = res1.getString(10);
            }
        }

        Cursor res = db.getSelectedInvoice(invoiceNumber);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());


        String header = "\n\n\n           EDENDALE DISTRIBUTORS LTD\n           Anse Courtois, Les Pailles\n            Republic of Mauritius\n            Phone : (230) 286 4920\n      Fax : (230) 286 4654/ (230) 286 9479\n             Vat Reg No : VAT20362266\n             Bus Reg No : C06064211\n                    VAT INVOICE\n\n\nINV No : " + invoiceNumber + "\nDelivery No : " + deliveryNumber + "\nPrepared by : " + "Joe" + "\nDate : " + date + "\tTime : " + time + "\nCustomer : " + customerName + "\n" + address + "\n\nVat No : " + customerVatNo + "\nBRN : " + customerBrn + "\n\nProducts\tQty\tDISC\tPrice\tTotal\n------------------------------------------------\n\n";
        String body = "";
        String footer = "";

        Double disc = 0.00;
        Double total = 0.00;
        Double totalExclu = 0.00;
        Double discountAmt = 0.00;
        Double vatAmt = 0.00;
        Double totalIncluVat = 0.00;

        if(res.getCount() < 0){
            Log.e("Error","invoice details Not found.");
        }
        else {
            while (res.moveToNext()){
                disc = Double.parseDouble(res.getString(2)) * Double.parseDouble(res.getString(3));
                disc = Math.round(disc * 100) / 100.0;

                total = Double.parseDouble(res.getString(3)) - disc;
                total = Math.round(total * 100) / 100.0;

                totalExclu = totalExclu + Double.parseDouble(calculateTotal(res.getString(7), res.getString(4), Double.parseDouble(res.getString(2)) * 100 + ""));
                totalExclu = Math.round(totalExclu * 100) / 100.0;

                discountAmt = discountAmt + (Double.parseDouble(res.getString(2)) * (Double.parseDouble(res.getString(4))));
                discountAmt = Math.round(discountAmt * 100) / 100.0;

                String vatRate = generateProductVatRate(customerName, res.getString(10));
                if(vatRate.contains("0.0")) {
                    vatAmt = 0.00;
                    totalIncluVat = Double.parseDouble(df2.format(totalExclu));
                }else {
                    vatAmt = vatAmt + (Double.parseDouble(vatRate) / 100) * Double.parseDouble(calculateTotal(res.getString(7), res.getString(4), Double.parseDouble(res.getString(2)) * 100 + ""));
                    vatAmt = Math.round(vatAmt *100) / 100.0;
                    totalIncluVat = Double.parseDouble(df2.format(totalExclu+Math.round(vatAmt *100) / 100.0));
                }

                body += res.getString(6) + "\t" + res.getString(7) + "\t" + disc + "\t" + res.getString(3) + "\t" + total + "\t" + res.getString(10) + "\n\n";
            }
            footer = "------------------------------------------------\nTotal Excl Vat Rs:\t" + totalExclu + "\nTotal Discount Rs:\t" + discountAmt + "\nTotal Vat Amt Rs:\t" + vatAmt + "\nTotal Incl. Vat Rs:\t" + totalIncluVat + "\n\nPay Method : " + salesTypeId + "\n\n------------------------------------------------\nTotal Qty : " + res.getCount() + "\n\n\nCustomer Signature:___________________________\n\nSalesman Signature:___________________________\n\n------------------------------------------------\nGoods Once Sold Are Not Returnable\n------------------------------------------------\nIn case of recovery through an attorney a\ncommision of 10% and interest of 2% above bank  lending rate willbe charged on the overdue      amount. For return cheque/s, a fee of Rs100 will be charged.\n------------------------------------------------\n           Computer Generated Copy\n\n           --- Thank You/Merci ---\n\n\n\n";

        }
        String note = header + body + footer;
        try {
            FindBluetoothDevice();
            openBluetoothPrinter();
            printData(note);
            Thread.sleep(5000);
            disconnectBt();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
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

    //#Varun - Get current date
    public String currentDate() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }

    //#Varun - Get Product VAT rate
    public String generateProductVatRate(String customer, String product) {
        String vatRate = "";
        Cursor res = db.getProductVat(customer, product);
        if(res.getCount() < 0){
            Log.e("Error","No product vat found");
        }
        else {
            while (res.moveToNext()){
                vatRate = res.getString(3);
            }
        }
        return vatRate;
    }

    //#Varun - Get All invoices
    public void getCurrentInvoices() {
        Cursor res = db.getAllCurrentDayInvoices();
        if(res.getCount() < 0){
            Log.e("Error","Not able to retrieve product data");
        }
        else {
            while (res.moveToNext()) {
                String invoiceNum = res.getString(3);
                String customerName = res.getString(9);
                String statusPost = res.getString(21);
                list.add(new SearchInvoiceClass(customerName+System.lineSeparator()+invoiceNum+System.lineSeparator()+statusPost, R.drawable.ic_baseline_print, R.drawable.ic_baseline_preview, R.drawable.ic_baseline_cancel));
            }
        }
    }

    public void cancelClick(final Context context, final int position) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure, you want to delete this record?")
                .setPositiveButton("Yes", null)
                .setNegativeButton("Cancel", null)
                .show();

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() != 0) {
                    Toast.makeText(context, list.get(position).getsInvName(), Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    LVsInv.invalidateViews();
                } else {
                    Toast.makeText(context, "list size 0", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
//                list.remove(1);
//                LVsInv.invalidateViews();
            }
        });
    }

    //#Varun - calculate product total
    public String calculateTotal(String quantity, String price, String discount) {
        double percentageDisc, discountedPrice, total;
        percentageDisc = Double.parseDouble(discount) / 100;
        discountedPrice = Double.parseDouble(price) - (Double.parseDouble(price) * percentageDisc);
        total = (Double.parseDouble(quantity) * discountedPrice);
        return df2.format(total);
    }

}