package com.harelmallac.edendale.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.harelmallac.edendale.model.SalesmanComplaintsModel;

public class PushToApi {

    final String URL = "http://192.168.85.177:8088/";
    public SQLiteDatabase db;

    public void postInvoicesHeader()
    {
        String invoiceHeaderUrl = "invoices/send";
    }

    public void postSalesmanComplaints(Context context)
    {
        DataBaseHelper db = new DataBaseHelper(context);
        String url = "complaints/send";

        Cursor res = db.getComplaints();

        if(res.getCount() < 0){
            Toast.makeText(context,"Not able to retrieve product data",Toast.LENGTH_SHORT).show();
        }

        while (res.moveToNext()){
            //SalesmanComplaintsModel complaint =new SalesmanComplaintsModel(res.getString(11),res.getString(2),res.getString(1),Boolean.parseBoolean(res.getString(5)),Boolean.parseBoolean(res.getString(16)),Boolean.parseBoolean(res.getString(6)),res.getString(4),Boolean.parseBoolean(res.getString(19)),res.getString(22),Boolean.parseBoolean(res.getString(7)),Boolean.parseBoolean(res.getString(8)),res.getString(20),Boolean.parseBoolean(res.getString(17)),res.getString(3));
        }









    }

    public void postCancelledInvoice()
    {

    }

    public void postReceipt()
    {

    }

}
