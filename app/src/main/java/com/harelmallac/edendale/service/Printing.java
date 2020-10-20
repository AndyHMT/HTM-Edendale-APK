package com.harelmallac.edendale.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.harelmallac.edendale.SplashActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.UUID;

public class Printing implements Runnable{
    private Context context;
    private Activity activity;
    protected static final String TAG = "TAG";
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    public Printing(@Nullable Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    BluetoothAdapter mBluetoothAdapter;
    private UUID applicationUUID = UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");
    private ProgressDialog mBluetoothConnectProgressDialog;
    private BluetoothSocket mBluetoothSocket;
    BluetoothDevice mBluetoothDevice;

    public void checkBluetooth() {
        Log.e("Enter", "checkbluetooth");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
                Toast.makeText(context, "Device support Bluetooth.", Toast.LENGTH_SHORT).show();
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(
                        BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBtIntent,
                        REQUEST_ENABLE_BT);
                    Toast.makeText(context, "Device supports Bluetooth but it is off.", Toast.LENGTH_SHORT).show();
                } else {
                    ListPairedDevices();

                    printData();
                    Toast.makeText(context, "Device supports Bluetooth and it is on.", Toast.LENGTH_SHORT).show();
                }
        }else {
                mBluetoothAdapter.disable();
                Toast.makeText(context, "Device does not supports Bluetooth.", Toast.LENGTH_SHORT).show();
        }
    }

    public void feintBluetoothDeviceDiscovery() {
        mBluetoothAdapter.startDiscovery();
        mBluetoothAdapter.cancelDiscovery();
    }

    public void printData(){

        Thread t = new Thread() {
            public void run() {
                try {
                   // mBluetoothAdapter
                    OutputStream os = mBluetoothSocket.getOutputStream();
                    String header = "\n\n\n       EDENDALE DISTRIBUTORS LTD\n           Anse Courtois, Les Pailles\n            Republic of Mauritius\n            Phone : (230) 286 4920\n      Fax : (230) 286 4654/ (230) 286 9479\n             Vat Reg No : VAT20362266\n             Bus Reg No : C06064211\n                    VAT INVOICE\n\n\nINV No : " + "1545232" + "\nDelivery No : " + "454545" + "\nPrepared by : " + "joe" + "\nDate : " + "12/12/12" + "\tTime : " + "18:25" + "\nCustomer : " + "Terry John" + "\n" + "VGT" + "\n\nVat No : " + "V343443" + "\nBRN : " + "B4545454" + "\n\nProducts\tQty\tDISC\tPrice\tTotal\n------------------------------------------------\n\n";
                    String copy = "";
                   
                    copy = "-Customer's Copy\n\n\n\n\n";

                    os.write(header.getBytes());
                    os.write(copy.getBytes());
                    
                    //This is printer specific code you can comment ==== > Start

                    // Setting height
                    int gs = 29;
                    os.write(intToByteArray(gs));
                    int h = 104;
                    os.write(intToByteArray(h));
                    int n = 162;
                    os.write(intToByteArray(n));

                    // Setting Width
                    int gs_width = 29;
                    os.write(intToByteArray(gs_width));
                    int w = 119;
                    os.write(intToByteArray(w));
                    int n_width = 2;
                    os.write(intToByteArray(n_width));
                    Log.e("ooooos", os+"");
                } catch (Exception e) {
                    Log.e("PrintActivity", "Exe ", e);
                }
            }
        };
        t.start();
    }

    protected void onDestroy() {
        try {
            if (mBluetoothSocket != null)
                mBluetoothSocket.close();
        } catch (Exception e) {
            Log.e("Tag", "Exe ", e);
        }
    }


    private void ListPairedDevices() {
        Set<BluetoothDevice> mPairedDevices = mBluetoothAdapter
                .getBondedDevices();
        Log.e("Tag", mPairedDevices.size()+"");

        if (mPairedDevices.size() > 0) {
            for (BluetoothDevice mDevice : mPairedDevices) {
                Log.e(TAG, "PairedDevices: " + mDevice.getName() + "  "
                        + mDevice.getAddress());
                Log.e("Tag", mDevice.getAddress());

            }
        }else {
            mBluetoothDevice = mBluetoothAdapter.getRemoteDevice("88:6B:0F:50:99:7F");
            Thread mBlutoothConnectThread = new Thread(this);
            mBlutoothConnectThread.start();
        }
    }

    public void run() {
        Log.e("CouldNotConnectToSocket", "runnnnnnnnnnnnnnnnnssswswswnn");
        try {
            Log.e("CouldNotConnectToSocket", "runnnnnnnnnnnnnnnnnnn");
            mBluetoothSocket = mBluetoothDevice
                    .createRfcommSocketToServiceRecord(applicationUUID);
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothSocket.connect();
            mHandler.sendEmptyMessage(0);
        } catch (IOException eConnectException) {
            Log.e(TAG, "CouldNotConnectToSocket", eConnectException);
            closeSocket(mBluetoothSocket);
            return;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mBluetoothConnectProgressDialog.dismiss();
            Toast.makeText( context, "Device connected. ", Toast.LENGTH_LONG).show();
            //btn.setEnabled(true);
            Toast.makeText( context, "Device disconnected. ", Toast.LENGTH_LONG).show();
        }
    };

    private void closeSocket(BluetoothSocket nOpenSocket) {
        try {
            nOpenSocket.close();
            Log.d(TAG, "SocketClosed");
        } catch (IOException ex) {
            Log.d(TAG, "CouldNotCloseSocket");
        }
    }

    public static byte intToByteArray(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();

        for (int k = 0; k < b.length; k++) {
            System.out.println("Selva  [" + k + "] = " + "0x"
                    + UnicodeFormatter.byteToHex(b[k]));
        }

        return b[3];
    }

    public byte[] sel(int val) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putInt(val);
        buffer.flip();
        return buffer.array();
    }


}
