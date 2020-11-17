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
import com.harelmallac.edendale.model.AddressModel;
import com.harelmallac.edendale.model.CustomerModel;
import com.harelmallac.edendale.model.PriceModel;
import com.harelmallac.edendale.model.ProductModel;
import com.harelmallac.edendale.model.UserModel;
import com.harelmallac.edendale.model.VatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRequest {

    final String URL = "http://192.168.0.116:8088/";
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
                        boolean active = obj.getBoolean("active");
                        String mainsite = obj.getString("mainsite");

                        UserModel user = new UserModel(id, password, salesRepId, salesSiteId, role, username, bank, active, mainsite);
                        dB.addUser(user);
                        userList.add(user);
                        //Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                      //  Log.e("Id", id + "");

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
                   // Log.e("Created Table Invoice","Table Invoice Created");

                    String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
                    db.execSQL(sqlCreateTableProduct);
                    //Log.e("Created Table Product","Table Product Created");

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String sageIdentifier = obj.getString("sageIdentifier");
                        String productName = obj.getString("productName");
                        String productType = obj.getString("productType");
                        double quantity = 0.0;
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
                        //Log.e("DELETE FROM Table Product","DELETE FROM Product Created");

                        ProductModel product = new ProductModel(sageIdentifier, productName, productType, quantity, vatRate, unit, subCat1, subCat2, subCat3, subCat4, subCat5);
                        dbH.addProduct(product);
                        Log.e("Product added",product.getProductName());
                        //productList.add(product);



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

    public void getAllCustomers(final Context context, String repnum) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //repnum = "SR00010";
        String url = URL + "customers/complete?salesRepId="+repnum+"&company=EDL";
        Log.e("URL",url);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<CustomerModel> customerList = new ArrayList<CustomerModel>();
            DataBaseHelper dbH = new DataBaseHelper(context);
            SQLiteDatabase db1 = dbH.getWritableDatabase();

            @Override
            public void onResponse(JSONArray response) {

                String sqlCreateTableCustomer = "CREATE TABLE IF NOT EXISTS tbl_customer(sageIdentifier VARCHAR(1000) PRIMARY KEY, customerName TEXT, brn VARCHAR, vatNo VARCHAR, salesRepId VARCHAR, customerType VARCHAR, vatCode VARCHAR, creditLimit VARCHAR, amountOwned VARCHAR)";
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
                        String amountOwned = "";
                        String salesRepId = obj.getString("salesRepId");
                        String creditLimit = obj.getString("creditLimit");
                        Log.e("Customer Name",customerName);

                        String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
                        db1.execSQL(sqlCreateTableProduct);
                        //Log.e("Created Table Product","Table Product Created");
                        String sqlDetelteFromProduct = "DELETE FROM tbl_product WHERE sageIdentifier = '"+sageIdentifier+"'";
                        db1.execSQL(sqlDetelteFromProduct);
                        //Log.e("DELETE FROM Table Product","DELETE FROM Product Created");


                        CustomerModel customer = new CustomerModel(sageIdentifier, customerName, brn, vatNo, salesRepId, customerType, vatCode, creditLimit, amountOwned);
                        dbH.addCustomer(customer);
                        customerList.add(customer);
                        //Log.e("Customers Table","Customer "+customer.getCustomerName()+" added");

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

    public void getAllAdresses(final Context context, String repnum) {
        String url = URL+"addresses/salesRep?salesRepId="+repnum;
        Log.e("URL",url);
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
            List<AddressModel> addressList = new ArrayList<AddressModel>();
            DataBaseHelper dbH = new DataBaseHelper(context);
            SQLiteDatabase db2 = dbH.getWritableDatabase();

            @Override
            public void onResponse(JSONArray response) {

                String sqlCreateTableAdress = "CREATE TABLE IF NOT EXISTS tbl_address(sageIdentifier VARCHAR(1000) PRIMARY KEY, addressNumId VARCHAR, name TEXT, addressLine1 VARCHAR, addressLine2 VARCHAR, city VARCHAR, customerId VARCHAR)";
                db2.execSQL(sqlCreateTableAdress);
                //Log.e("Created Table Adress","Table Adress Created");
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String sageIdentifier = obj.getString("sageIdentifier");
                        String addressNumId = "";
                        String name = obj.getString("name");
                        String addressLine1 = obj.getString("addressLine1");
                        String addressLine2 = obj.getString("addressLine2");
                        String city = obj.getString("city");
                        JSONObject customer  = obj.getJSONObject("customer");
                        String customerId = customer.getString("sageIdentifier");

                        Log.e("Address Line",addressLine1);

                        String sqlCreateTableProduct = "DELETE FROM tbl_address WHERE sageIdentifier ='"+sageIdentifier+"'";
                        db2.execSQL(sqlCreateTableProduct);
                       // Log.e("DELETE FROM table Address","Alter address table");

                        AddressModel address = new AddressModel(sageIdentifier, addressNumId, name, addressLine1, addressLine2,city, customerId);
                        dbH.addAddress(address);
                        addressList.add(address);
                        //Log.e("Address Table","Address "+address.getName()+" added");



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

    public void getAllPrices(final Context context, String repnum, String siteId) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //repnum = "SR00010";
        String url = URL + "prices/salesRepSite?salesRepId="+repnum+"&salesSiteId="+siteId;
        Log.e("URL",url);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<PriceModel> priceList = new ArrayList<PriceModel>();
            DataBaseHelper dbH = new DataBaseHelper(context);
            SQLiteDatabase db3 = dbH.getWritableDatabase();

            @Override
            public void onResponse(JSONArray response) {

                String sqlCreateTableCustomer = "CREATE TABLE IF NOT EXISTS tbl_price(price_id VARCHAR(1000) PRIMARY KEY, customerId VARCHAR, productId VARCHAR, price VARCHAR, priority INTEGER)";
                db3.execSQL(sqlCreateTableCustomer);
               // Log.e("Created Table Customer","Table Customer Created");
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String price_id = null;
                        String customerId = obj.getString("customerId");
                        String productId = obj.getString("productId");
                        float price = Float.parseFloat(obj.getString("price"));
                        int priority = Integer.parseInt(obj.getString("priority")) ;
                        //Log.e("Price",price+"");

                        String sqlDeletePrice = "DELETE FROM tbl_price WHERE customerId ='"+customerId+"' and productId ='"+productId+"'";
                        db3.execSQL(sqlDeletePrice);
                      //  Log.e("Created Table Price","Table Price Created");
//
//
                        PriceModel priceModel = new PriceModel(customerId, productId, price, priority);
                        dbH.addPrice(priceModel);
                        priceList.add(priceModel);
                     //   Log.e("Customers Table","pRICE "+priceModel.getPrice()+" added");


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

    public void getVat(final Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //repnum = "SR00010";
        String url = URL + "vat/today";
        Log.e("URL",url);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<VatModel> vatList = new ArrayList<VatModel>();
            DataBaseHelper dbH = new DataBaseHelper(context);
            SQLiteDatabase db4 = dbH.getWritableDatabase();

            @Override
            public void onResponse(JSONArray response) {

                String sqlCreateTableVat= "CREATE TABLE IF NOT EXISTS tbl_vat(vat_id VARCHAR(1000) PRIMARY KEY, customerVatCode VARCHAR(100), productVatRate VARCHAR(100), vatRate VARCHAR)";
                db4.execSQL(sqlCreateTableVat);
                Log.e("Created Table Vat","Table Vat Created");
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        String vat_id = null;
                        String customerVatCode = obj.getString("customerVatCode");
                        String productVatRate = obj.getString("productVatRate");
                        String vatRate = obj.getString("vatRate");
                        Log.e("Vat Rate",productVatRate+"");

                        String sqlDeleteVat = "DELETE FROM tbl_vat WHERE customerVatCode = '"+customerVatCode+"'and productVatRate = '"+productVatRate+"'";
                        db4.execSQL(sqlDeleteVat);
                        Log.e("Deleted from table Vat","Deleted");
//
                        VatModel vatModel = new VatModel(customerVatCode, productVatRate, vatRate);
                        dbH.addVat(vatModel);
                        vatList.add(vatModel);
                        Log.e("Vat Table","Vat "+vatModel.getCustomerVatCode()+" added");


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

    public void syncTransfer(final Context context, final String saleSiteId) {
        RequestQueue queue = Volley.newRequestQueue(context);
        //repnum = "SR00010";
        String url = URL + "stock/site?salesSiteId="+saleSiteId;
        Log.e("URL",url);

        JsonArrayRequest objectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            List<ProductModel> productList = new ArrayList<ProductModel>();
            DataBaseHelper dbH = new DataBaseHelper(context);
            SQLiteDatabase db5 = dbH.getWritableDatabase();

            @Override
            public void onResponse(JSONArray response) {

                String sqlCreateTableVat= "CREATE TABLE IF NOT EXISTS tbl_invoiceSelectedProd(selectedProductId VARCHAR(1000) PRIMARY KEY, quantityReceived REAL, productId VARCHAR, userId VARCHAR, location VARCHAR, date Date)";
                db5.execSQL(sqlCreateTableVat);
                Log.e("Created Table Vat","Table Vat Created");
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        JSONObject id  = obj.getJSONObject("id");
                        String productId = id.getString("productId");
                        String salesSiteId = id.getString("salesSiteId");
                        String quantity = obj.getString("quantity");
                        Log.e("PRODUCT ID ",productId);
                        Log.e("id",salesSiteId);

                        dbH.addSelectedProductInvoice(productId,salesSiteId,quantity);
                        Log.e("Table SelectedProductId","Created");



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




