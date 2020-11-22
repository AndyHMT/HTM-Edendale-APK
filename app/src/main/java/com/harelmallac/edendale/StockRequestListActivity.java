package com.harelmallac.edendale;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.harelmallac.edendale.adapter.SelectedProductAdapter;
import com.harelmallac.edendale.adapter.StockRequestProductAdapter;
import com.harelmallac.edendale.database.DataBaseHelper;
import com.harelmallac.edendale.database.PushToApi;
import com.harelmallac.edendale.model.IdentityModel;
import com.harelmallac.edendale.model.ProductLinesClass;
import com.harelmallac.edendale.model.StockRequestModel;
import com.harelmallac.edendale.model.StockRequestProductClass;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class StockRequestListActivity extends AppCompatActivity {
    ArrayList<String> selectedProduct = new ArrayList<>();
    ArrayList<String> selectedQty = new ArrayList<>();
    private static ArrayList<StockRequestProductClass> stockList = new ArrayList<>();
    private static ArrayList<StockRequestModel> stockModel = new ArrayList<>();
    private StockRequestProductAdapter adapter;
    private static ListView lvStock;
    DataBaseHelper db = new DataBaseHelper(this);

    Button btnCreate;
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile  boolean stopWorker;
    Context c = StockRequestListActivity.this;

    @Override
    public void onBackPressed() {
        stockList.clear();
        Intent mainIntent = new Intent( StockRequestListActivity.this, MenuActivity.class );
        startActivity(mainIntent);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_request_listview);

        lvStock = findViewById(R.id.lvStock);
        btnCreate = findViewById(R.id.btnCreate);

        if( getIntent().getExtras() != null) {
            selectedProduct = getIntent().getStringArrayListExtra("selectedProd");
            selectedQty = getIntent().getStringArrayListExtra("selectedQty");
        }

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-dd-mm HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        if(selectedProduct != null) {
            for (int i = 0; i < selectedProduct.size(); i++) {
                stockList.add(new StockRequestProductClass(selectedProduct.get(i), selectedQty.get(i)));
            }
            adapter = new StockRequestProductAdapter(this, stockList);
            lvStock.setAdapter(adapter);
        }

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());

                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-dd-mm HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                String header = "\n\n                TRANSFER REQUEST\n               \n\nPrepared by : " + "Joe" + "\nVehicle : " + "MV07" + "\n\nDate : " + date + "\tTime : " + time + "\nRequestId: " + generateRequestNumber() + "\n\nQTY\t\t     PRODUCT\n------------------------------------------------\n";
                String body = "";
                String footer = "";
                String note = "";
                int totalQty = 0;


                formattedDate = formattedDate.replace(" ", "T");
                if(stockList != null) {
                    for (int i = 0; i < stockList.size(); i++) {
                        String productId = db.getProductId(stockList.get(i).getStockProductName());
                        body += stockList.get(i).getStockProductQty() + "\t" + stockList.get(i).getStockProductName() + "\n\n";
                        stockModel.add(new StockRequestModel(Float.parseFloat(selectedQty.get(i)), generateRequestNumber(), "SR00010", new IdentityModel(productId), "MV07", "EDLL", formattedDate));
                        totalQty = totalQty + Integer.parseInt(stockList.get(i).getStockProductQty());
                    }
                    footer = "\n------------------------------------------------\nTotal : \t" + totalQty + "\n\n\n\n";
                    note = header + body + footer;
                }


                    PushToApi pushToMiddleware = new PushToApi();
                try {
                    pushToMiddleware.postStockRequest(c, stockModel);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    FindBluetoothDevice();
                    openBluetoothPrinter();
                    //printData(note);
                    //printCopy(c, note);
                    Thread.sleep(5000);
                    disconnectBt();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Generate generate request number
    public String generateRequestNumber() {
        String randomRequest = "RI" + Math.floor(Math.random() * (9999999 - 1000000 + 111111)) + 100000;
        return randomRequest;
    }

    public void postTransferRequest() {

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

    public void printCopy(final Context context, final String note) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Stock Request")
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
                Intent intent = new Intent(StockRequestListActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    public void delete(int position){
        stockList.remove(position);
        lvStock.invalidateViews();
    }
}