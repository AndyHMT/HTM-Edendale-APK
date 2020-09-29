package com.harelmallac.edendale.database;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.harelmallac.edendale.model.CustomerModel;
import com.harelmallac.edendale.model.ProductModel;
import com.harelmallac.edendale.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequest {

    final String URL = "http://192.168.85.141:8088/";
    public SQLiteDatabase db;

    public void getUser(final Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = URL + "users";
        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<UserModel> userList = new ArrayList<UserModel>();
            DataBaseHelper dB = new DataBaseHelper(context);

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String id = obj.getString("userId");
                        String password = obj.getString("password");
                        String salesRepId = obj.getString("salesRepId");
                        String salesSiteId = obj.getString("salesSiteId");
                        String role = obj.getString("role");
                        String username = obj.getString("username");
                        String bank = obj.getString("bank");
                        Boolean active = obj.getBoolean("active");
                        String mainsite = obj.getString("mainsite");

                        UserModel user = new UserModel(id, password, salesRepId, salesSiteId, role, username, bank, active, mainsite);
                        dB.addUser(user);
                        userList.add(user);
                        //Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                        Log.e("Id", id + "");

                    }


                }
                // Try and catch are included to handle any errors due to JSON
                catch (JSONException e) {
                    Log.e("error", e.toString());
                    // If an error occurs, this prints the error to the log
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
                //Toast.makeText(MainActivity.this, "onErroe", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(objectRequest);
    }

    public void getProducts(final Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);

        String url = URL + "products/site?siteId=EDLL";

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<ProductModel> productList = new ArrayList<ProductModel>();
            DataBaseHelper dbH = new DataBaseHelper(context);
            SQLiteDatabase db = dbH.getWritableDatabase();

            @Override
            public void onResponse(JSONArray response) {


                try {
                    String sqlCreateTableInvoice = "CREATE TABLE IF NOT EXISTS tbl_invoice(invoiceId INTEGER PRIMARY KEY, date DATE, status TEXT, invoiceNumber VARCHAR, deliveryNumber VARCHAR, orderNumber VARCHAR, salesSite VARCHAR, type VARCHAR, customerId VARCHAR, customerName VARCHAR, customerBrn VARCHAR, customerVatNo VARCHAR, customerVatCode VARCHAR, salesTypeId VARCHAR, addressId VARCHAR, addressName VARCHAR, userId VARCHAR, receiptNumber VARCHAR, mainSite VARCHAR, originalSalesRep VARCHAR, invoiceTotal VARCHAR, statusPost VARCHAR, cancelledOn Date)";
                    db.execSQL(sqlCreateTableInvoice);
                    Log.e("Created Table Invoice","Table Invoice Created");
                    String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
                    db.execSQL(sqlCreateTableProduct);
                    Log.e("Created Table Product","Table Product Created");

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String sageIdentifier = obj.getString("sageIdentifier");
                        String productName = obj.getString("productName");
                        String productType = obj.getString("productType");
                        Double quantity = 0.0;
                        String vatRate = obj.getString("vatRate");
                        String unit = "";
                        String subCat1 = "";
                        String subCat2 = "";
                        String subCat3 = "";
                        String subCat4 = "";
                        String subCat5 = "";



                        if (vatRate.equals("OGA")) {
                            productName = productName + "*";
                        }

                        String sqlDetelteFromProduct = "DELETE FROM tbl_product WHERE sageIdentifier = '"+sageIdentifier+"'";
                        db.execSQL(sqlDetelteFromProduct);
                        Log.e("DELETE FROM Table Product","DELETE FROM Product Created");

                        ProductModel product = new ProductModel(sageIdentifier, productName, productType, quantity, vatRate, unit, subCat1, subCat2, subCat3, subCat4, subCat5);
                        dbH.addProduct(product);
                        productList.add(product);



                        //Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
                    }


                } catch (JSONException e) {
                    Log.e("error", e.toString());
                    // If an error occurs, this prints the error to the log
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
                //Toast.makeText(MainActivity.this, "onErroe", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(objectRequest);
    }

    public void getAllCustomers(Context context, String repnum)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        //repnum = "SR00010";
        String url = URL + "customers/complete?salesRepId="+repnum+"&company=EDL";
        Log.e("URL",url);
        DataBaseHelper dbH = new DataBaseHelper(context);
        final SQLiteDatabase db1 = dbH.getWritableDatabase();

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<CustomerModel> customerList = new ArrayList<CustomerModel>();

            @Override
            public void onResponse(JSONArray response) {

                String sqlCreateTableCustomer = "CREATE TABLE IF NOT EXISTS tbl_customer(sageIdentifier VARCHAR(1000) PRIMARY KEY, customerName TEXT, brn VARCHAR, vatNo VARCHAR, salesRepId VARCHAR, customerType VARCHAR, vatCode VARCHAR, creditLimit VARCHAR, amountOwed DOUBLE)";
                db1.execSQL(sqlCreateTableCustomer);
                Log.e("Created Table Customer","Table Customer Created");
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String sageIdentifier = obj.getString("sageIdentifier");
                        String customerName = obj.getString("customerName");
                        String brn = obj.getString("brn");
                        String vatCode = obj.getString("vatCode");
                        String vatNo = obj.getString("vatNo");
                        String customerType = obj.getString("customerType");
                       // Double amountOwed = Double.parseDouble(obj.getString("amountOwed"));
                        String salesRepId = obj.getString("salesRepId");
                        String creditLimit = obj.getString("creditLimit");
                        Log.e("Customer Name",customerName);

                        String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
                        db.execSQL(sqlCreateTableProduct);
                        Log.e("Created Table Product","Table Product Created");
                        String sqlDetelteFromProduct = "DELETE FROM tbl_product WHERE sageIdentifier = '"+sageIdentifier+"'";
                        db.execSQL(sqlDetelteFromProduct);
                        Log.e("DELETE FROM Table Product","DELETE FROM Product Created");
//
//                        ProductModel product = new ProductModel(sageIdentifier, productName, productType, quantity, vatRate, unit, subCat1, subCat2, subCat3, subCat4, subCat5);
//                        dbH.addProduct(product);
//                        productList.add(product);



                        //Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
                    }


                } catch (JSONException e) {
                    Log.e("error", e.toString());
                    // If an error occurs, this prints the error to the log
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error response", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                String creds = String.format("%s:%s", "edend@leapp", "edend@l3_123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);
                params.put("Authorization", auth);
                return params;
            }
        };
        queue.add(objectRequest);
    }


}





//        String sqlCreateTableInvoice = "CREATE TABLE IF NOT EXISTS tbl_invoice(invoiceId INTEGER PRIMARY KEY, date DATE, status TEXT, invoiceNumber VARCHAR, deliveryNumber VARCHAR, orderNumber VARCHAR, salesSite VARCHAR, type VARCHAR, customerId VARCHAR, customerName VARCHAR, customerBrn VARCHAR, customerVatNo VARCHAR, customerVatCode VARCHAR, salesTypeId VARCHAR, addressId VARCHAR, addressName VARCHAR, userId VARCHAR, receiptNumber VARCHAR, mainSite VARCHAR, originalSalesRep VARCHAR, invoiceTotal VARCHAR, statusPost VARCHAR, cancelledOn Date)";
//        db.execSQL(sqlCreateTableInvoice);
//
//        String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
//        db.execSQL(sqlCreateTableProduct);
