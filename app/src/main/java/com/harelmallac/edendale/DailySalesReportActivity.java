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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.DailySalesListAdapter;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.model.ChequeReportClass;
import com.harelmallac.edendale.model.DailySalesListClass;

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

public class DailySalesReportActivity extends AppCompatActivity {
    DataBaseHelper db = new DataBaseHelper(this);
    ListView LVDSList;
    ArrayList<DailySalesListClass> list = new ArrayList<>();
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
    Context c = DailySalesReportActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_sales_report);

        LVDSList = findViewById(R.id.LVDSList);
        btnPrint = findViewById(R.id.button5);

        populateSalesReportProduct();

        DailySalesListAdapter adapter = new DailySalesListAdapter(this, R.layout.daily_sales_list_layout, list);
        LVDSList.setAdapter(adapter);


        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView dateView = findViewById(R.id.textView75);
        dateView.setText(date);

        String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());
        TextView timeView = findViewById(R.id.textView76);
        timeView.setText(time);

        String header = "\n\n\n       DAILY SALES REPORT - BY PRODUCT\n               \n\nPrepared by : " + "Joe" + "\n\nDate : " + date + "\nTime : " + time + "\n\nSTK\tQTY\t     PRODUCT\n------------------------------------------\n";
        String body = "";
        int grossTotal = 0;
        for(int i = 0; i < list.size(); i++) {
            grossTotal = Integer.parseInt(list.get(i).getDSQty()) + grossTotal;
            body += "___\t" + list.get(i).getDSQty() + "\t" + list.get(i).getDSProduct() + "\n\n";
        }
        String footer = "------------------------------------------\nTotal =\t" + grossTotal + "\n\n\n";
        final String note = header + body + footer;

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

    public void populateSalesReportProduct() {
        Cursor res = db.getSalesReportProduct();

        if(res.getCount() < 0){
            Toast.makeText(getApplicationContext(),"Not able to retrieve sales report data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (res.moveToNext()){
                String qty = res.getString(0);
                String productName = res.getString(1);
                list.add(new DailySalesListClass(qty, productName));
            }

        }
    }

    public void cancelClick(final Context context, final String note) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Inventory Stock")
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
                Intent intent = new Intent(DailySalesReportActivity.this, ReportActivity.class);
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
}