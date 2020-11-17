package com.harelmallac.edendale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
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
import com.harelmallac.edendale.model.ComplaintsModel;
import com.harelmallac.edendale.model.IdentityModel;
import com.harelmallac.edendale.model.SalesmanComplaintsModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class ComplaintDescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    OutputStream outputStream;
    InputStream inputStream;
    Thread thread;
    byte[] readBuffer;
    int readBufferPosition;
    volatile  boolean stopWorker;
    Context c = ComplaintDescriptionActivity.this;

    DataBaseHelper db = new DataBaseHelper(this);
    String cusName = null;
    String cusAddress = null;
    String cusPhone = null;
    String cusEmail = null;

    String proSpinner = null;
    String proName = null;
    String proDescription = null;
    String purDate = null;
    String placeOfPur = null;
    Boolean dairy = false;
    Boolean dry = false;
    Boolean liquid = false;
    Boolean frozen = false;
    Boolean productQuality = false;
    Boolean taste = false;
    Boolean deposit = false;
    Boolean packaging = false;
    Boolean solubitlity = false;
    Boolean expiry = false;
    Boolean prodOther = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_description);
        Spinner ComSpinner = findViewById(R.id.spinnerComplaint);
        Toast.makeText(getApplicationContext(),ComSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
        ComSpinner.setOnItemSelectedListener(this);

        final String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
        final String time = new SimpleDateFormat("HH : mm", Locale.getDefault()).format(new Date());

        Button butNext = findViewById(R.id.button);

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( getIntent().getExtras() != null)
                {
                    cusName = getIntent().getStringExtra("cusName");
                    cusAddress = getIntent().getStringExtra("cusAddress");
                    cusPhone = getIntent().getStringExtra("cusPhone");
                    cusEmail = getIntent().getStringExtra("cusEmail");
                    proSpinner = getIntent().getStringExtra("proSpinner");
                    proName = getIntent().getStringExtra("proName");
                    proDescription = getIntent().getStringExtra("proDescription");
                    purDate = getIntent().getStringExtra("purDate");
                    placeOfPur = getIntent().getStringExtra("placeOfPur");
                }

                Spinner ComSpinner = findViewById(R.id.spinnerComplaint);
                EditText other =findViewById(R.id.otherSpecification);
                EditText comDescription =findViewById(R.id.complaintDescription);
                EditText comResponse = findViewById(R.id.complaintResponse);

                String comSpinner = ComSpinner.getSelectedItem().toString();
                String comOther = other.getText().toString();
                String comDescrip = comDescription.getText().toString();
                String comResp = comResponse.getText().toString();

                if (comDescrip.isEmpty()==false && comResp.isEmpty()==false ){
                    if(comSpinner.equals("Other")){
                        if (comOther.isEmpty()==false){
                            Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Please fill in all of the fields",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        //==============================
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill in all of the fields",Toast.LENGTH_SHORT).show();
                }
                    String prodDesc = "";
                    if (proSpinner.equals("Dairy"))
                    {
                        dairy= true;
                        frozen = false;
                        liquid = false;
                        dry = false;
                        prodDesc = prodDesc + "- Dairy\n";
                    }

                if (proSpinner.equals("Frozen"))
                {
                    dairy= false;
                    frozen = true;
                    liquid = false;
                    dry = false;
                    prodDesc = prodDesc + "- Frozen\n";
                }

                if (proSpinner.equals("Liquid"))
                {
                    dairy= false;
                    frozen = false;
                    liquid = true;
                    dry = false;
                    prodDesc = prodDesc + "- Liquid";
                }

                if (proSpinner.equals("Dry"))
                {
                    dairy= false;
                    frozen = false;
                    liquid = false;
                    dry = true;
                    prodDesc = prodDesc + "- Dry\n";
                }

                String complaintCat = "";

                if(comSpinner.equals("Product Quality"))
                {
                    productQuality = true;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                    complaintCat = complaintCat + "- Product Quality\n";
                }

                if(comSpinner.equals("Taste"))
                {
                    productQuality = false;
                    taste = true;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                    complaintCat = complaintCat + "- Taste\n";
                }

                if(comSpinner.equals("Deposit"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = true;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                    complaintCat = complaintCat + "- Deposit\n";
                }

                if(comSpinner.equals("Packaging"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = true;
                    solubitlity = false;
                    expiry = false;
                    prodOther = false;
                    complaintCat = complaintCat + "- Packaging\n";
                }

                if(comSpinner.equals("Solubility"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = true;
                    expiry = false;
                    prodOther = false;
                    complaintCat = complaintCat + "- Solubility\n";
                }

                if(comSpinner.equals("Expiry"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = true;
                    prodOther = false;
                    complaintCat = complaintCat + "- Expiry";
                }

                if(comSpinner.equals("Other"))
                {
                    productQuality = false;
                    taste = false;
                    deposit = false;
                    packaging = false;
                    solubitlity = false;
                    expiry = false;
                    prodOther = true;
                }
                Log.e("Product Name",proName);

                String productId = db.getProductId(proName);
                IdentityModel product = new IdentityModel(productId);



                SalesmanComplaintsModel complaints = new SalesmanComplaintsModel(comDescrip,cusAddress,cusName,dairy,deposit,dry,cusEmail,expiry,comResp,frozen,liquid,comOther,packaging,cusPhone,product,proDescription,productQuality,purDate,placeOfPur,solubitlity,taste,purDate);
                db.addComplaint(complaints);

                Log.e("Error Message",proSpinner+"");

                String note = "\n\n\n\n           EDENDALE DISTRIBUTORS LTD\n        Anse Courtois, Les Pailles\n            Republic of Mauritius\n         Phone : (230) 286 4920\n     Fax : (230) 286 4654/ (230) 286 9479\n         Vat Reg No : VAT20362266\n           Bus Reg No : C06064211\n                  COMPLAINT\n\n\nDate : " + date + "\tTime : " + time + "\nSalesMan : " + "Joe" + "\n\n------------------------------------------\nCUSTOMER DETAILS\n------------------------------------------\n\nCustomer name : " + cusName + "\nAddress : " + cusAddress + "\nPhone Num : " + cusPhone + "\nEmail : " + cusEmail + "\n------------------------------------------\nPRODUCT DETAILS\n------------------------------------------\n\nName: " + proName + "\n\nProduct Description : \n" + prodDesc + "\n\nMore details : " + proDescription + "\n\nDate of Purchase : " + purDate + "\n\nPlace of Purchase : " + placeOfPur + "\n------------------------------------------\nCOMPLAINT\n------------------------------------------\n\nCategory : \n" + complaintCat + "\n\nOthers (Specify) : " + comOther + "\n\nProblem Description : " + comDescrip + "\nCorrective Action : " + comResp + "\n------------------------------------------\n\nCustomer Signature:___________________________\n\nSalesman Signature:___________________________\n\n\n\n\n\n\n";

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

        TextView customerView2 = findViewById(R.id.customerView2);
        customerView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ComplaintDescriptionActivity.this, ComplaintFormActivity.class);
                startActivity(intent2);
            }
        });

        TextView productView = findViewById(R.id.productView);
        productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ComplaintDescriptionActivity.this, ComplaintProductActivity.class);
                startActivity(intent3);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String z = parent.getSelectedItem().toString();
        //Toast.makeText(this,z,Toast.LENGTH_SHORT).show();
        EditText specification = findViewById(R.id.otherSpecification);
        if(z.equals("Other"))
        {
            specification.setEnabled(true);
            specification.setHintTextColor(getResources().getColor(R.color.red));
        }
        else
        {
            specification.setText("");
            specification.setEnabled(false);
            specification.setHintTextColor(getResources().getColor(R.color.darkGray));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void cancelClick(final Context context, final String note) {
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("Complaint")
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
                Intent intent = new Intent(ComplaintDescriptionActivity.this, MenuActivity.class);
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