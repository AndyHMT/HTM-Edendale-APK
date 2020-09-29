package com.harelmallac.edendale.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.harelmallac.edendale.model.*;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "tbl_user";
    public static final String PRODUCT_TABLE = "tbl_product";
    public static final String CUSTOMER_TABLE = "tbl_customer";
    public static final String ADDRESS_TABLE = "tbl_address";
    public static final String PRICE_TABLE = "tbl_price";
    public static final String VAT_TABLE = "tbl_vat";
    public SQLiteDatabase db;


    //Function for the first time a database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //FOR USER PART
        String sqlCreateTableInvoice = "CREATE TABLE IF NOT EXISTS tbl_invoice(invoiceId INTEGER PRIMARY KEY, date DATE, status TEXT, invoiceNumber VARCHAR, deliveryNumber VARCHAR, orderNumber VARCHAR, salesSite VARCHAR, type VARCHAR, customerId VARCHAR, customerName VARCHAR, customerBrn VARCHAR, customerVatNo VARCHAR, customerVatCode VARCHAR, salesTypeId VARCHAR, addressId VARCHAR, addressName VARCHAR, userId VARCHAR, receiptNumber VARCHAR, mainSite VARCHAR, originalSalesRep VARCHAR, invoiceTotal VARCHAR, statusPost VARCHAR, cancelledOn Date)";
        db.execSQL(sqlCreateTableInvoice);
        Log.i("Created Table Invoice","Table User Dropped");
        String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
        db.execSQL(sqlCreateTableProduct);
        String createTableStatement="CREATE TABLE "+ USER_TABLE+"(userId INTEGER PRIMARY KEY, password VARCHAR, salesRepId VARCHAR(500), salesSiteId VARCHAR, role VARCHAR, username VARCHAR, bank VARCHAR, active NUMERIC, mainsite VARCHAR )";
        db.execSQL(createTableStatement);
        Log.i("Create Table User","Table User Created");
        String sqlCreateTableCustomer = "CREATE TABLE IF NOT EXISTS tbl_customer(sageIdentifier VARCHAR(1000) PRIMARY KEY, customerName TEXT, brn VARCHAR, vatNo VARCHAR, salesRepId VARCHAR, customerType VARCHAR, vatCode VARCHAR, creditLimit VARCHAR, amountOwned VARCHAR)";
        db.execSQL(sqlCreateTableCustomer);

    }


    //function when version number changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(UserModel userModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userId",userModel.getUserId());
        cv.put("password",userModel.getPassword());
        cv.put("salesRepId",userModel.getRepNum());
        cv.put("salesSiteId",userModel.getSalesSiteId());
        cv.put("role",userModel.getRole());
        cv.put("username",userModel.getUsername());
        cv.put("bank",userModel.getBank());
        cv.put("active",userModel.isActive());
        cv.put("mainsite",userModel.getMainsite());

        long insert = db.insert(USER_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean addProduct(ProductModel productModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("sageIdentifier",productModel.getSageIdentifier());
        cv.put("productName",productModel.getProductName());
        cv.put("productType",productModel.getProductType());
        cv.put("quantity",productModel.getQuantity());
        cv.put("vatRate",productModel.getVatRate());
        cv.put("unit",productModel.getUnit());
        cv.put("subCat1",productModel.getSubCat1());
        cv.put("subCat1",productModel.getSubCat1());
        cv.put("subCat2",productModel.getSubCat2());
        cv.put("subCat3",productModel.getSubCat3());
        cv.put("subCat4",productModel.getSubCat4());
        cv.put("subCat5",productModel.getSubCat5());

        long insert = db.insert(PRODUCT_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean addCustomer(CustomerModel customerModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("sageIdentifier",customerModel.getSageIdentifier());
        cv.put("customerName",customerModel.getCustomerName());
        cv.put("brn",customerModel.getBrn());
        cv.put("vatCode",customerModel.getVatCode());
        cv.put("vatNo",customerModel.getVatNo());
        cv.put("customerType",customerModel.getCustomerType());
        cv.put("amountOwned",customerModel.getAmountOwed());
        cv.put("salesRepId",customerModel.getSalesRepId());
        cv.put("creditLimit",customerModel.getCreditLimit());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }


    }

    public boolean addAddress(AddressModel addressModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("sageIdentifier",addressModel.getSageIdentifier());
        cv.put("addressNumId",addressModel.getAddressId());
        cv.put("name",addressModel.getName());
        cv.put("addressLine1",addressModel.getAddressLine1());
        cv.put("addressLine2",addressModel.getAddressLine2());
        cv.put("city",addressModel.getCity());
        cv.put("customerId",addressModel.getCustomerId());



        long insert = db.insert(ADDRESS_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }


    }


    public boolean addPrice(PriceModel priceModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("customerId",priceModel.getCustomerId());
        cv.put("productId",priceModel.getProductId());
        cv.put("price",priceModel.getPrice());
        cv.put("priority",priceModel.getPriority());


        long insert = db.insert(PRICE_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }


    }

    public boolean addVat(VatModel vatModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("customerVatCode",vatModel.getCustomerVatCode());
        cv.put("productVatRate",vatModel.getProductVatRate());
        cv.put("vatRate",vatModel.getVatRate());


        long insert = db.insert(VAT_TABLE, null, cv);

        if(insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }


    }



















    public DataBaseHelper(@Nullable Context context) {
        super(context,"edendale.db",null,1);
    }

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DataBaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    public List<UserModel> getAllElements() {

        ArrayList<UserModel> list = new ArrayList<UserModel>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        UserModel obj = new UserModel();
                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }
        //Log.e("",list.toString());
        return list;
    }

    public Cursor getData()
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM  " + USER_TABLE;

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;

    }

    public boolean checkCredentils(String username,String password)
    {
        db=this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM "+USER_TABLE+" WHERE username ='"+username+"' AND password='"+password+"'";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        String txtcount = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
        //Log.e("ghjk",txtcount);

        int count = Integer.parseInt(txtcount);

        if (count <1)
        {
            Log.e("sdfb","false");
            return false;
        }
        else
        {
            Log.e("sdfb","true");
            return true;
        }
    }





}
