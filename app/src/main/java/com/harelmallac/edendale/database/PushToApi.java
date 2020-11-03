package com.harelmallac.edendale.database;

import android.database.sqlite.SQLiteDatabase;

public class PushToApi {

    final String URL = "http://192.168.85.177:8088/";
    public SQLiteDatabase db;

    public void postInvoicesHeader()
    {
        String invoiceHeaderUrl = "invoices/send";
    }

    public void postSalesmanComplaints()
    {
        String url = "complaints/send";


    }

    public void postCancelledInvoice()
    {

    }

    public void postReceipt()
    {

    }

}
