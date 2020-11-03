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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "tbl_user";
    public static final String PRODUCT_TABLE = "tbl_product";
    public static final String CUSTOMER_TABLE = "tbl_customer";
    public static final String ADDRESS_TABLE = "tbl_address";
    public static final String PRICE_TABLE = "tbl_price";
    public static final String VAT_TABLE = "tbl_vat";
    public static final String COMPLAINT_TABLE = "tbl_complaint";
    public static final String SELECTPRODUCTINVOICE = "tbl_invoiceSelectedProd";
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

    public boolean addSelectedProductInvoice(String productId, String salesSiteId, String quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        cv.put("selectedProductId", (byte[]) null);
        cv.put("quantityReceived",quantity);
        cv.put("productId",productId);
        cv.put("location",salesSiteId);
        cv.put("date",formatter.format(date));
        Log.e("Insert",formatter.format(date));

        String query = "";


        long insert = db.insert(SELECTPRODUCTINVOICE, null, cv);

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

    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler)
    {
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

    public Cursor getComplaints()
    {
        db = this.getWritableDatabase();
        String selectStatement ="SELECT * FROM "+COMPLAINT_TABLE;

        Cursor res = db.rawQuery(selectStatement,null);
        return res;
    }


    public Cursor getProduct()
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM  " + PRODUCT_TABLE;

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;

    }

    public String getProductName(String productId)
    {
        db = this.getWritableDatabase();
        String selectStatement ="SELECT * FROM  " + PRODUCT_TABLE + " WHERE sageIdentifier = '" + productId +"'";
        Cursor res = db.rawQuery(selectStatement, null);
        res.moveToFirst();
        String pName= res.getString(res.getColumnIndex(res.getColumnName(1)));
        return pName;
    }

    //#Varun - retrieve product id from tbl_product
    public String getProductId(String productName)
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM  " + PRODUCT_TABLE + " WHERE productName = '" + productName +"'";
        Cursor res = db.rawQuery(selectTableStatement, null);
        res.moveToFirst();
        String pid = res.getString(res.getColumnIndex(res.getColumnName(0)));
        return pid;
    }

    public String addComplaint(SalesmanComplaintsModel complaints)
    {
        db = this.getWritableDatabase();
        String createStatement = "CREATE TABLE IF NOT EXISTS tbl_complaint(complaintId VARCHAR(1000) PRIMARY KEY, customerName VARCHAR, address VARCHAR, phoneNum VARCHAR, email VARCHAR, dairy NUMERIC, dry NUMERIC, frozen NUMERIC, liquid NUMERIC, productName VARCHAR, productId VARCHAR, productDescription VARCHAR, dateOfPurchase DATE, placeOfPurchase VARCHAR, prodQuality NUMERIC, taste NUMERIC, deposit NUMERIC, packaging NUMERIC, solubility NUMERIC, expiry NUMERIC, others VARCHAR, problemDescription VARCHAR, correctiveAction VARCHAR)";
        db.execSQL(createStatement);
        IdentityModel product = complaints.getProduct();
        String id = product.getSageIdentifier();
        String name = getProductName(id);

        String insertStatement = "INSERT INTO tbl_complaint(customerName,address,phoneNum,email,dairy,dry,frozen,liquid,productName," +
                "productId,productDescription,dateOfPurchase,placeOfPurchase,prodQuality,taste,deposit,packaging,solubility,expiry," +
                "others,problemDescription,correctiveAction) VALUES ('"+ complaints.getCustomerName()+
                "', '"+complaints.getCustomerAddress()+"', '"+complaints.getPhoneNo()+"', '"+complaints.getEmail()+
                "', '"+complaints.isDairy()+"', '"+complaints.isDry()+"', '"+complaints.isFrozen()+"', '"+complaints.isLiquid()+
                "', '"+name+"', '"+id+"', '"+complaints.getProductDescription()+"', '"+complaints.getPurchaseDate()+
                "', '"+complaints.getPurchasePlace()+"', '"+complaints.isProductQuality()+"', '"+complaints.isTaste()+
                "', '"+complaints.isDeposit()+"', '"+complaints.isPackaging()+"', '"+complaints.isSolubility()+
                "', '"+complaints.isExpiry()+"', '"+complaints.getOthers()+"', '"+complaints.getComplantDescription()+
                "', '"+complaints.getFirstCorrectiveAction()+"')";


        db.execSQL(insertStatement);
        Log.e("List of added",complaints.getCustomerName()+"', '"+complaints.getCustomerAddress()+"', '"+complaints.getPhoneNo()+"', '"+complaints.getEmail()+"', '"+complaints.isDairy()+"', '"+complaints.isDry()+"', '"+complaints.isFrozen()+"', '"+complaints.isLiquid()+"', '"+name+"', '"+id+"', '"+complaints.getProductDescription()+"', '"+complaints.getPurchaseDate()+"', '"+complaints.getPurchasePlace()+"', '"+complaints.isProductQuality()+"', '"+complaints.isTaste()+"', '"+complaints.isDeposit()+"', '"+complaints.isPackaging()+"', '"+complaints.isSolubility()+"', '"+complaints.isExpiry()+"', '"+complaints.getOthers()+"', '"+complaints.getComplantDescription()+"', '"+complaints.getFirstCorrectiveAction());
       return "Complaints added succesfully";
    }

    //#Varun - retrieve product price
    public double getProductPrice(String productId)
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT price FROM  " + PRICE_TABLE + " WHERE productId = '" + productId +"'";
        Cursor res = db.rawQuery(selectTableStatement, null);
        res.moveToFirst();

        double price = res.getDouble(res.getColumnIndex(res.getColumnName(0)));
        return price;
    }




    //#Varun - retrieve product qty
    public int getProductQty(String productId)
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT quantityReceived FROM  " + SELECTPRODUCTINVOICE + " WHERE productId = '" + productId +"'";
        Cursor res = db.rawQuery(selectTableStatement, null);
        res.moveToFirst();

        int qty = res.getInt(res.getColumnIndex(res.getColumnName(0)));
        return qty;
    }

    public Cursor getCustomer()
    {

        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM  " + CUSTOMER_TABLE;

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;

    }

    public Cursor getInvoice()
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_invoiceSelectedProd as selectedProd INNER JOIN tbl_product as product ON selectedProd.productId = product.sageIdentifier order by product.productName";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;

    }

    public Cursor getCustomerCreateInvoiceInfo(String customerName)
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM  " + CUSTOMER_TABLE +" Where customerName = '" + customerName +"'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        Log.e("Response",res.toString());
        return res;

    }

    public Cursor getAddress(String customerName)
    {
        db = this.getWritableDatabase();
        //String customerID = "(SELECT sageIdentifier FROM" + CUSTOMER_TABLE + "WHERE customerName =" + customerName +")";
        //Cursor cusID = db.rawQuery(customerID,null);
        String selectTableStatement="SELECT * FROM tbl_address WHERE customerId = (SELECT sageIdentifier FROM " + CUSTOMER_TABLE + " WHERE customerName ='" + customerName +"')";
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
            Log.e("FISBQ","false");
            return false;
        }
        else
        {
            Log.e("sdfb","true");
            return true;
        }
    }




    ///

    //ALL UPDATE METHOD
    //#Varun - Update selected qty upon create invoice
    public boolean updateSelectedProductQty(String productId, String quantity)
    {
        ContentValues cv = new ContentValues();
        cv.put("quantityReceived", quantity);
        long update = db.update(SELECTPRODUCTINVOICE, cv, "productId = ?", new String[]{productId});

        if(update == - 1) {
            Log.e("Qty not updated -- check updateSelecteddProductQty method ", false+"");
            return false;
        }else {
            return true;
        }
    }



}
