package com.harelmallac.edendale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
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
import com.harelmallac.edendale.model.ItemClass;
import com.harelmallac.edendale.model.ProductLinesAdapter;
import com.harelmallac.edendale.model.ProductLinesClass;
import com.harelmallac.edendale.model.SaleInvoiceModel;
import com.harelmallac.edendale.model.TotalInfoClass;
//import com.harelmallac.edendale.service.Printing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class CreateInvoiceLinesActivity extends AppCompatActivity {
    private static DecimalFormat df2 = new DecimalFormat("###.##");
    private static final String TAG = "MainActivity23";
    private static ArrayList<ProductLinesClass> ProList = new ArrayList<>();
    private static ArrayList<TotalInfoClass> totalList = new ArrayList<>();
    private static ArrayList<InvoiceProductModel> invoiceProductList = new ArrayList<>();
    private static ArrayList<SaleInvoiceModel> invoiceSaleList = new ArrayList<>();

    private static Context context;
    private static ListView LVLines;
    private static ListView LVTotals;
    private SelectedProductAdapter adapter;
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

   // Printing print = new Printing(this, CreateInvoiceLinesActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice_lines);

        ArrayList<String> selectedProduct = new ArrayList<>();
        ArrayList<String> selectedPid = new ArrayList<>();
        ArrayList<String> selectedTotal = new ArrayList<>();
        ArrayList<String> selectedDiscount = new ArrayList<>();
        ArrayList<String> selectedQuantity = new ArrayList<>();

        String customerName = null;
        String AmountOwned = null;
        String SalesSite = null;
        String Type = null;
        String Date = null;
        String SalesType = null;
        String ShippingAddress = null;

        if( getIntent().getExtras() != null) {
            customerName = getIntent().getExtras().getString("customerName");
            AmountOwned = getIntent().getStringExtra("AmountOwned");
            SalesSite = getIntent().getStringExtra("salessite");
            Type = getIntent().getStringExtra("type");
            Date = getIntent().getStringExtra("Date");
            SalesType = getIntent().getStringExtra("salestype");
            ShippingAddress = getIntent().getStringExtra("address");
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
        double totalVatAmt = 0.00;
        if(selectedProduct != null) {
            for (int i = 0; i < selectedProduct.size(); i++) {
                //#Varun - Product VAT Rate
                String vatRate = generateProductVatRate(customerName, selectedProduct.get(i));

                //#Varun - Update prodlist by auto-calculate total based on quantity selected
                ProList.add(new ProductLinesClass(selectedPid.get(i), selectedProduct.get(i), selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), calculateTotal(selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), selectedDiscount.get(i)), selectedDiscount.get(i), vatRate));

                //#Varun - Calculate Total Exclu VAT
                total = total + Double.parseDouble(calculateTotal(selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), selectedDiscount.get(i)));
                totalList.get(0).setNum(df2.format(total)+"");

                //#Varun - Calculate total discount
                discount = discount + (Double.parseDouble(selectedDiscount.get(i))/100) * (Double.parseDouble(getProductPrice(selectedProduct.get(i))));
                totalList.get(1).setNum(df2.format(discount)+"");


                if(vatRate.contains("0.0")) {
                    totalList.get(2).setNum("0.00");
                    totalList.get(3).setNum(df2.format(total)+"");
                }else {
                    totalVatAmt = totalVatAmt + (Double.parseDouble(vatRate) / 100) * Double.parseDouble(calculateTotal(selectedQuantity.get(i), getProductPrice(selectedProduct.get(i)), selectedDiscount.get(i)));
                    totalList.get(2).setNum(Math.round(totalVatAmt *100) / 100.0+"");
                    totalList.get(3).setNum(df2.format(total+Math.round(totalVatAmt *100) / 100.0)+"");
                }


            }

            adapter = new SelectedProductAdapter(this, ProList);
            LVLines.setAdapter(adapter);

        }


        Button btnCreateInvoice = findViewById(R.id.btnCreate);
        final String finalShippingAddress = ShippingAddress;
        final String finalCustomerName = customerName;
        final String finalSalesSite = SalesSite;
        final String finalSalesType = SalesType;
        final String finalType = Type;
        btnCreateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //#Varun - invoice number
                generateInvoiceNumber();
                double vatAmount;
                double total = 0.00;
                double totalIncVat = 0.00;
                double discount = 0.00;
                String header = "";
                String body = "";
                String footer = "";
                if(ProList != null) {
                    for (int i = 0; i < ProList.size(); i++) {

                        //#Varun - Update product qty upon create receipt
                        updateQty(ProList.get(i).getName(), ProList.get(i).getQty());

                        Double selectedQty =  Double.parseDouble(ProList.get(i).getQty());
                        Double discountedPrice = (Double.parseDouble(ProList.get(i).getPrice())) - ((Double.parseDouble(ProList.get(i).getPrice()) * Double.parseDouble(ProList.get(i).getDiscount())/100));

                        String vatRate = ProList.get(i).getVatRate();

                        //Total amt exclu VAT
                        total = total + Double.parseDouble(calculateTotal(ProList.get(i).getQty(), getProductPrice(ProList.get(i).getName()), ProList.get(i).getDiscount()));

                        if(vatRate.contains("0.0")) {
                            vatAmount = 0.00;
                            totalIncVat = total;
                        }else {
                            vatAmount = ((Double.parseDouble(vatRate) / 100) * Double.parseDouble(ProList.get(i).getTotal())) + Double.parseDouble(ProList.get(i).getTotal());
                            totalIncVat = total+Math.round(vatAmount *100) / 100.0;
                        }

                        //Total discount
                        discount = discount + (Double.parseDouble(ProList.get(i).getDiscount())/100) * (Double.parseDouble(getProductPrice(ProList.get(i).getName())));

                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String date = df.format(Calendar.getInstance().getTime());

                        String customerVatNo = db.getCustomerVatCode(finalCustomerName);
                        Cursor res = db.getCustomerDetails(finalCustomerName);
                        String brn = "";
                        if(res.getCount() < 0){
                            Log.e("Error","Customer details Not found.");
                        }
                        else {
                            while (res.moveToNext()){
                                brn = res.getString(2);
                            }
                        }

                        String[] splitDate = date.split(" ");

                        String currentTime = splitDate[1];

                        Double calDiscount = (Double.parseDouble(ProList.get(i).getDiscount()) / 100.0) * Double.parseDouble(ProList.get(i).getPrice());
                        calDiscount = Math.round(calDiscount*100) /100.0;

                        //#Varun - Insert into tbl_invoice
                        invoiceProductList.add(new InvoiceProductModel(discountedPrice, Double.parseDouble(ProList.get(i).getDiscount())/100, Double.parseDouble(getProductPrice(ProList.get(i).getName())), generateInvoiceNumber(), new IdentityModel(ProList.get(i).getId()), selectedQty, vatAmount));
                        invoiceSaleList.add(new SaleInvoiceModel(ProList.get(i).getId(), date, generateDeliveryNumber(), generateInvoiceNumber(), "Open", finalShippingAddress, finalCustomerName, finalSalesSite, finalSalesType, finalType, "1", "", "", "RESR0001012121", "EDLL", "", date, total+""));

                        header = "\n\n\n           tEDENDALE DISTRIBUTORS LTD\n           Anse Courtois, Les Pailles\n            Republic of Mauritius\n            Phone : (230) 286 4920\n      Fax : (230) 286 4654/ (230) 286 9479\n             Vat Reg No : VAT20362266\n             Bus Reg No : C06064211\n                    VAT INVOICE\n\n\nINV No : " + generateInvoiceNumber() + "\nDelivery No : " + generateDeliveryNumber() + "\nPrepared by : " + "Joe" + "\nDate : " + date + "\tTime : " + currentTime + "\nCustomer : " + finalCustomerName + "\n" + finalShippingAddress + "\n\nVat No : " + customerVatNo + "\nBRN : " + brn + "\n\nProducts\tQty\tDISC\tPrice\tTotal\n------------------------------------------------\n\n";
                        body += ProList.get(i).getId() + "\t" + ProList.get(i).getQty() + "\t" + calDiscount + "\t" + ProList.get(i).getPrice() + "\t" + ProList.get(i).getTotal() + "\t" + ProList.get(i).getName() + "\n\n";
                        footer = "------------------------------------------------\nTotal Excl Vat Rs:\t" + Math.round(total*100)/100.0 + "\nTotal Discount Rs:\t" + Math.round(discount*100)/100.0 + "\nTotal Vat Amt Rs:\t" + Math.round(vatAmount*100)/100.0 + "\nTotal Incl. Vat Rs:\t" + Math.round(totalIncVat*100)/100.0 + "\n\nPay Method : " + finalSalesType + "\n\n------------------------------------------------\nTotal Qty : " + ProList.size() + "\n\n\nCustomer Signature:___________________________\n\nSalesman Signature:___________________________\n\n------------------------------------------------\nGoods Once Sold Are Not Returnable\n------------------------------------------------\nIn case of recovery through an attorney a\ncommision of 10% and interest of 2% above bank\nlending rate will be charged on the overdue\namount. For return cheque/s, a fee of Rs100 willbe charged.\n------------------------------------------------\n\n           --- Thank You/Merci ---\n\n\n\n";


                    }
                    db.createInvoice(invoiceProductList);
                    //#Varun - print receipt
                    db.createReceipt(invoiceSaleList);
                }


                //#Varun - Clear listview upon create invoice
                ProList.clear();
                totalList.get(0).setNum("0.00");
                totalList.get(1).setNum("0.00");
                totalList.get(2).setNum("0.00");
                totalList.get(3).setNum("0.00");
                LVLines.invalidateViews();
                LVTotals.invalidateViews();


                try {
                    FindBluetoothDevice();
                    openBluetoothPrinter();
                    printData(header+body+footer);
                    Thread.sleep(5000);
                    disconnectBt();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }


                //Redirect to main menu
                Intent mainIntent = new Intent( CreateInvoiceLinesActivity.this,
                        MenuActivity.class );
                startActivity(mainIntent);



                //print.checkBluetooth();
                //print.feintBluetoothDeviceDiscovery();
                //print.printData();

                Intent intent = new Intent(CreateInvoiceLinesActivity.this, ReceiptPrintActivity.class);
                startActivity(intent);
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

    public void delete(int position){
        //remove item from itemlist based on index
        double total = 0.00;
        double discount = 0.00;
        double totalVatAmt = 0.00;
        String vatRate = "";
        for(int i = 0; i < ProList.size(); i++){
            total = total + Double.parseDouble(ProList.get(i).getTotal());
            discount = discount + ((Double.parseDouble(ProList.get(i).getDiscount())/100) * (Double.parseDouble(ProList.get(i).getPrice())));
            vatRate = ProList.get(i).getVatRate();

            if(vatRate.contains("0.0")) {
                Log.e("yyfrfrfrrffry", vatRate+"");
                //totalList.get(2).setNum("0.00");
            }else {
                totalVatAmt = totalVatAmt + (Double.parseDouble(vatRate) / 100) * Double.parseDouble(ProList.get(i).getTotal());
            }
        }

        double newTotal = total - Double.parseDouble(ProList.get(position).getTotal());
        double newDiscount = discount - ((Double.parseDouble(ProList.get(position).getDiscount())/100) * (Double.parseDouble(ProList.get(position).getPrice())));
        double newTotalVatAmt = totalVatAmt - (Double.parseDouble(vatRate) / 100) * Double.parseDouble(ProList.get(position).getTotal());
        ProList.remove(position);

        //Update Total Exclu VAT on remove
        totalList.get(0).setNum(df2.format(newTotal)+"");
        totalList.get(1).setNum(df2.format(newDiscount)+"");
        if(vatRate.contains("0.0")) {
            totalList.get(2).setNum("0");
            totalList.get(3).setNum(df2.format(newTotal)+"");
        }else {
            totalList.get(2).setNum(Math.round(newTotalVatAmt *100) / 100.0+"");
            totalList.get(3).setNum(df2.format(total+Math.round(newTotalVatAmt *100) / 100.0)+"");
        }

        //Update listview based on item position
        LVLines.invalidateViews();
        LVTotals.invalidateViews();
    }


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




}


