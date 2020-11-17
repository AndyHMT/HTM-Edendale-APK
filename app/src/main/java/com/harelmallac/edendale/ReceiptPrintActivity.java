package com.harelmallac.edendale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.DailySalesListClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class ReceiptPrintActivity extends AppCompatActivity {
    String cusName;
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile  boolean stopWorker;
    Context c = ReceiptPrintActivity.this;
    DataBaseHelper db = new DataBaseHelper(this);
    @Override
    public void onBackPressed() {

        Intent mainIntent = new Intent( ReceiptPrintActivity.this, MenuActivity.class );
        startActivity(mainIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_print);

        if( getIntent().getExtras() != null) {
            cusName = getIntent().getStringExtra("cusName");
        }

        String salesType = "";
        if(customerType().contains("N01")) {
            salesType = "Cash";
        }else {
            salesType = "Credit";
        }

        TextView customerNameView = findViewById(R.id.customerNameView);
        customerNameView.setText(cusName);

        final TextView DateView = findViewById(R.id.dateView);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        DateView.setText(date);

        final TextView customerName = findViewById(R.id.customerNameView);
        final EditText txtAmount = findViewById(R.id.editTextNumberDecimal);
        final EditText txtCheckNum = findViewById(R.id.ChequeNumEdit);

        final TextView TimeView = findViewById(R.id.timeView);
        SimpleDateFormat sdf = new SimpleDateFormat("HH : mm", Locale.getDefault());
        String currentTimeHour = sdf.format(new Date());
//        sdf = new SimpleDateFormat("mm", Locale.getDefault());
//        String currentTimeMin = sdf.format(new Date());
        TimeView.setText(currentTimeHour);


        final Spinner spinnerPayType = findViewById(R.id.spinner2);
        Spinner spinner = findViewById(R.id.spinner3);

        final String dateValue = DateView.getText().toString();
        String timeValue =TimeView.getText().toString();

        Button btnPrint = findViewById(R.id.button2);
        final String finalSalesType = salesType;
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String customer = customerName.getText().toString();
                final String paymentType = spinnerPayType.getSelectedItem().toString();
                final String amount = txtAmount.getText().toString();
                final String checkNum = txtCheckNum.getText().toString();
                final String bank = txtCheckNum.getText().toString();

                String note = "";
                if(paymentType.equals("Cheque")) {
                    note = "\n\n\n\n           EDENDALE DISTRIBUTORS LTD\n        Anse Courtois, Les Pailles\n            Republic of Mauritius\n         Phone : (230) 286 4920\n     Fax : (230) 286 4654/ (230) 286 9479\n         Vat Reg No : VAT20362266\n           Bus Reg No : C06064211\n                    RECEIPT\n\n\nReceipt Num: " + generateReceiptNumber() + "\nDate : " + dateValue + "\nSalesman : " + "Joe" + "\n\n------------------------------------------\n\nCustomer name : " + customer + "\nSales Type : " + finalSalesType + "\nAmount : " + amount + "\nCheque Num: " + checkNum + "\nBank : " + bank + "\n\n------------------------------------------\n\n\nCustomer Signature:___________________________\n\nSalesman Signature:___________________________\n\n\n\n\n\n\n";
                }else {
                    note = "\n\n\n\n           EDENDALE DISTRIBUTORS LTD\n        Anse Courtois, Les Pailles\n            Republic of Mauritius\n         Phone : (230) 286 4920\n     Fax : (230) 286 4654/ (230) 286 9479\n         Vat Reg No : VAT20362266\n           Bus Reg No : C06064211\n                    RECEIPT\n\n\nReceipt Num: " + generateReceiptNumber() + "\nDate : " + dateValue + "\nSalesman : " + "Joe" + "\n\n------------------------------------------\n\nCustomer name : " + customer + "\nSales Type : " + finalSalesType + "\nAmount : " + amount + "\n\n------------------------------------------\n\n\nCustomer Signature:___________________________\n\nSalesman Signature:___________________________\n\n\n\n\n\n\n";
                }

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
                .setTitle("Receipt")
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
                Intent intent = new Intent(ReceiptPrintActivity.this, MenuActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
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

    //#Varun - Generate Receipt Number
    public String generateReceiptNumber() {
        int count = 0;
        String ReceiptNum;
        String Uid = "SR00010";
        DateFormat df = new SimpleDateFormat("ddMMyy");
        String date = df.format(Calendar.getInstance().getTime());
        //RE+SR00010+170920+1
        if(db.getReceiptCount() == 0) {
            count = 1;
            ReceiptNum = "RE"+Uid+date+count;
        }else{
            count = db.getInvoiceCount() + 1;
            ReceiptNum = "RE"+Uid+date+count;
        }
        return ReceiptNum;
    }

    public String customerType() {
        String result = "";
        Cursor res = db.getCustomerDetails(cusName);
        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve sales report data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                result = res.getString(5);
            }

        }
        return result;
    }
}