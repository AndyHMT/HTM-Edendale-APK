package com.harelmallac.edendale.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.harelmallac.edendale.model.IdentityModel;
import com.harelmallac.edendale.model.InvoiceProductModel;
import com.harelmallac.edendale.model.SaleInvoiceModel;
import com.harelmallac.edendale.model.SalesmanComplaintsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PushToApi {

    final String URL = "http://192.168.0.116:8088/";
    public SQLiteDatabase db;

    public void postInvoicesHeader(Context context) throws JSONException
    {
        JSONArray array = new JSONArray();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        DataBaseHelper db = new DataBaseHelper(context);
        String invoiceHeaderUrl = "invoices/send";
        String link = URL+invoiceHeaderUrl;
        List<SaleInvoiceModel>invoiceHeader = new ArrayList<>();
        Cursor res = db.getInvoiceHeader();
        if(res.getCount()<0)
        {
            Log.e("VARUN", res.getCount()+"");
            Toast.makeText(context, "Not able to retrieve invoices from local database", Toast.LENGTH_SHORT).show();
        }
        while(res.moveToNext())
        {
            SaleInvoiceModel invoiceModel = new SaleInvoiceModel("",res.getString(1),res.getString(4),res.getString(3),res.getString(2),res.getString(14),res.getString(8),res.getString(6),res.getString(13),res.getString(7),res.getString(16), res.getString(6),"","",res.getString(18),res.getString(19),res.getString(1),res.getString(20));
            invoiceHeader.add(invoiceModel);
        }
        for (SaleInvoiceModel saleInvoiceModel:invoiceHeader)
        {
            String customerId =saleInvoiceModel.getCustomer();
            Cursor res2 = db.getCustomerById(customerId);
            JSONObject customer = new JSONObject();
            while(res2.moveToNext())
            {
                customer.put("sageIdentifier",res2.getString(0));
                customer.put("customerName",res2.getString(1));
                customer.put("brn",res2.getString(2));
                customer.put("vatNo",res2.getString(3));
                customer.put("salesRepId",res2.getString(4));
                customer.put("customerType",res2.getString(5));
                customer.put("vatCode",res2.getString(6));
                customer.put("salesRepId2","");
            }


            String addressId = saleInvoiceModel.getAddress();
            Cursor res3 = db.getAddressInvoice(addressId);
            JSONObject address = new JSONObject();
            while(res3.moveToNext())
            {
                address.put("sageIdentifier",res3.getString(0));
                address.put("addressId",res3.getString(1));
                address.put("country","MU");
                address.put("name",res3.getString(2));
                address.put("addressLine1",res3.getString(3));
                address.put("addressLine2",res3.getString(4));
                address.put("city",res3.getString(5));
                address.put("customer",customer);
                address.put("default",false);
            }
            String saleSiteId = saleInvoiceModel.getSalesSite();
            JSONObject saleSite = new JSONObject();
            saleSite.put("sageIdentifier",saleSiteId);

            String salesTypeId = saleInvoiceModel.getSalesType();
            JSONObject salesType = new JSONObject();
            salesType.put("sageIdentifier",salesTypeId);
            salesType.put("salesTypeName",salesTypeId);

            String typeId = saleInvoiceModel.getType();
            JSONObject type = new JSONObject();
            type.put("sageIdentifier",typeId);
            type.put("typeName",typeId);
            JSONObject object = new JSONObject();
            try{
                object.put("sageIdentifier","");
                object.put("date",saleInvoiceModel.getDate());
                object.put("creationTime",saleInvoiceModel.getCreationTime());
                object.put("deliveryNumber",saleInvoiceModel.getDeliveryNumber());
                object.put("invoiceNumber",saleInvoiceModel.getInvoiceNumber());
                object.put("status",saleInvoiceModel.getStatus());
                object.put("vanId",saleInvoiceModel.getVanId());
                object.put("receiptNo",saleInvoiceModel.getReceiptNo());
                object.put("invoiceTotal",saleInvoiceModel.getInvoiceTotal());
                object.put("mainSite",saleInvoiceModel.getMainSite());
                object.put("address",address);
                object.put("customer",customer);
                object.put("saleSite",saleSite);
                object.put("salesType",salesType);
                object.put("type",type);
                object.put("userId",saleInvoiceModel.getUserId());

            }catch (Exception ex){
                ex.printStackTrace();
            }
            array.put(object);
            Log.e("ARRAY FSDV", array+"");
            db.updateInvoice(saleInvoiceModel.getInvoiceNumber());
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, link, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response",response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error getting response", error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }












//    public void postInvoicesHeader(Context context) throws JSONException
//    {
//        JSONArray array = new JSONArray();
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        DataBaseHelper db = new DataBaseHelper(context);
//        String invoiceHeaderUrl = "invoices/send";
//        String link = URL+invoiceHeaderUrl;
//
//        List<SaleInvoiceModel>invoiceHeader = new ArrayList<>();
//
//        Cursor res = db.getInvoiceHeader();
//
//        if(res.getCount()<0)
//        {
//            Log.e("VARUN", res.getCount()+"");
//
//            Toast.makeText(context, "Not able to retrieve invoices from local database", Toast.LENGTH_SHORT).show();
//        }
//
//        while(res.moveToNext())
//        {
//            SaleInvoiceModel invoiceModel = new SaleInvoiceModel("",res.getString(1),res.getString(4),res.getString(3),res.getString(2),res.getString(14),res.getString(8),res.getString(6),res.getString(13),res.getString(7),res.getString(16), res.getString(6),"","",res.getString(18),res.getString(19),res.getString(1),res.getString(20));
//            invoiceHeader.add(invoiceModel);
//        }
//
//        for (SaleInvoiceModel saleInvoiceModel:invoiceHeader)
//        {
//            JSONObject object = new JSONObject();
//
//            try{
//
//                object.put("date",saleInvoiceModel.getDate());
//                object.put("status",saleInvoiceModel.getStatus());
//                object.put("invoiceNumber",saleInvoiceModel.getInvoiceNumber());
//                object.put("deliveryNumber",saleInvoiceModel.getDeliveryNumber());
//                object.put("saleSiteId",saleInvoiceModel.getSalesSite());
//                object.put("typeId",saleInvoiceModel.getType());
//                object.put("customerId",saleInvoiceModel.getCustomer());
//                object.put("salesTypeId",saleInvoiceModel.getSalesType());
//                object.put("addressId",saleInvoiceModel.getAddress());
//                object.put("sageIdentifier","");
//                object.put("userId",saleInvoiceModel.getUserId());
//                object.put("vanId",saleInvoiceModel.getVanId());
//                object.put("receiptNo",saleInvoiceModel.getReceiptNo());
//                object.put("creationTime",saleInvoiceModel.getCreationTime());
//                object.put("invoiceTotal",saleInvoiceModel.getInvoiceTotal());
//                object.put("mainSite",saleInvoiceModel.getMainSite());
//
//
//            }catch (Exception ex){
//
//                ex.printStackTrace();
//            }
//
//            array.put(object);
//            Log.e("ARRAY FSDV", array+"");
//            db.updateInvoice(saleInvoiceModel.getInvoiceNumber());
//        }
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, link, array,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.e("response",response.toString());
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error getting response", error.toString());
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<String, String>();
//                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
//                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
//                params.put("Authorization", auth);
//                return params;
//            }
//        };
//        requestQueue.add(jsonArrayRequest);
//    }





    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postSalesmanComplaints(Context context) throws JSONException, ParseException {

        JSONArray array = new JSONArray();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        DataBaseHelper db = new DataBaseHelper(context);
        String url = "complaints/salesman/send";
        String link = URL+url;
        List<SalesmanComplaintsModel>salesmanComplaint = new ArrayList<>();

        Cursor res = db.getComplaints();

        if(res.getCount() < 0){
            Toast.makeText(context,"Not able to retrieve product data",Toast.LENGTH_SHORT).show();
        }

        while (res.moveToNext()){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(Calendar.getInstance().getTime());

            String productId = res.getString(10);
            IdentityModel product = new IdentityModel(productId);
            SalesmanComplaintsModel complaint =new SalesmanComplaintsModel(res.getString(11),res.getString(2),res.getString(1),Boolean.parseBoolean(res.getString(5)),Boolean.parseBoolean(res.getString(16)),Boolean.parseBoolean(res.getString(6)),res.getString(4),Boolean.parseBoolean(res.getString(19)),res.getString(22),Boolean.parseBoolean(res.getString(7)),Boolean.parseBoolean(res.getString(8)),res.getString(20),Boolean.parseBoolean(res.getString(17)),res.getString(3),product,res.getString(11),Boolean.parseBoolean(res.getString(14)),res.getString(12),res.getString(11),Boolean.parseBoolean(res.getString(18)),Boolean.parseBoolean(res.getString(15)),date);
            salesmanComplaint.add(complaint);
        }

        // forEach Function
        for (SalesmanComplaintsModel complaintModel:salesmanComplaint)
        {
            IdentityModel productId = complaintModel.getProduct();
            String sageIdentifier = productId.getSageIdentifier();
            JSONObject product = new JSONObject();
            product.put("sageIdentifier",sageIdentifier);
            JSONObject object = new JSONObject();

            String date =complaintModel.getPurchaseDate();
            String newDate =  date.replace("/","-");
            Log.e("newDate", newDate);

            try
            {
                String newTimestamp =  complaintModel.getTimestamp().replace(" ","T");
                object.put("complaintDescription",complaintModel.getComplantDescription());
                object.put("customerAddress",complaintModel.getCustomerAddress());
                object.put("customerName",complaintModel.getCustomerName());
                object.put("dairy",complaintModel.isDairy());
                object.put("deposit",complaintModel.isDeposit());
                object.put("dry",complaintModel.isDry());
                object.put("email",complaintModel.getEmail());
                object.put("expiry",complaintModel.isExpiry());
                object.put("firstCorrectiveAction",complaintModel.getFirstCorrectiveAction());
                object.put("frozen",complaintModel.isFrozen());
                object.put("liquid",complaintModel.isLiquid());
                object.put("others",Boolean.parseBoolean(complaintModel.getOthers()));
                object.put("packaging",complaintModel.isPackaging());
                object.put("phoneNo",complaintModel.getPhoneNo());
                object.put("productId",product);
                object.put("productDescription",complaintModel.getProductDescription());
                object.put("productQuality",complaintModel.isProductQuality());
                object.put("purchaseDate",newDate);
                object.put("purchasePlace",complaintModel.getPurchasePlace());
                object.put("solubility",complaintModel.isSolubility());
                object.put("taste",complaintModel.isTaste());
                object.put("timestamp",newTimestamp);



            }catch (JSONException e) {
                e.printStackTrace();
            }

            array.put(object);
            Log.e("Data",array.toString());
        }

//        int i =0;
//        while(i<salesmanComplaint.size())
//        {
//            JSONObject object = new JSONObject();
//            object.put()
//            i++;
//        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, link, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response",response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error getting response", error.toString());
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public void postInvoiceProducts(Context context) throws JSONException {
        JSONArray array = new JSONArray();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String invoiceHeaderUrl = "invoice/products/send";
        String link = URL+invoiceHeaderUrl;
        DataBaseHelper db = new DataBaseHelper(context);
        Cursor res = db.getInvoiceProduct();
        if(res.getCount() < 0){
            Toast.makeText(context, "Not able to retrieve invoices from local database", Toast.LENGTH_SHORT).show();
        }
        while(res.moveToNext()) {

            try{
                JSONObject object = new JSONObject();
                JSONObject product = new JSONObject();
                product.put("sageIdentifier",res.getString(6));
                object.put("discountAmount",res.getString(1));
                object.put("discountPercentage",res.getString(2));
                object.put("grossPrice",res.getString(3));
                object.put("invoiceNumber",res.getString(12));
                object.put("product",product);
                object.put("selectedQty",res.getString(7));
                object.put("vatAmount",res.getString(8));
                array.put(object);
                Log.e("Array", array+"");
            }
            catch (Exception e) {
                e.printStackTrace();

            }

            //InvoiceProductModel invoiceProductModel = new InvoiceProductModel(Double.parseDouble(res.getString(1)), Double.parseDouble(res.getString(2)), Double.parseDouble(res.getString(3)), Double.parseDouble(res.getString(5)), n);
        }


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, link, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response",response.toString());
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error getting response", error.toString());
            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }




    public void postCancelledInvoice()
    {

    }

    public void postReceipt()
    {

    }

}
