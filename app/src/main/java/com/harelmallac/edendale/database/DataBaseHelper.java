package com.harelmallac.edendale.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.harelmallac.edendale.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.sql.Types.NULL;


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
        String sqlCreateTableInvoice = "CREATE TABLE IF NOT EXISTS tbl_invoice(invoiceId INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, status TEXT, invoiceNumber VARCHAR, deliveryNumber VARCHAR, orderNumber VARCHAR, salesSite VARCHAR, type VARCHAR, customerId VARCHAR, customerName VARCHAR, customerBrn VARCHAR, customerVatNo VARCHAR, customerVatCode VARCHAR, salesTypeId VARCHAR, addressId VARCHAR, addressName VARCHAR, userId VARCHAR, receiptNumber VARCHAR, mainSite VARCHAR, originalSalesRep VARCHAR, invoiceTotal VARCHAR, statusPost VARCHAR, cancelledOn Date)";
        db.execSQL(sqlCreateTableInvoice);
        Log.i("Created Table Invoice","Table User Dropped");
        String sqlCreateTableProduct = "CREATE TABLE IF NOT EXISTS tbl_product(sageIdentifier VARCHAR(1000) PRIMARY KEY, productName TEXT, productType TEXT, quantity REAL, vatRate VARCHAR, unit VARCHAR, subCat1 VARCHAR, subCat2 VARCHAR, subCat3 VARCHAR, subCat4 VARCHAR, subCat5 VARCHAR)";
        db.execSQL(sqlCreateTableProduct);
        String createTableStatement="CREATE TABLE "+ USER_TABLE+"(userId INTEGER PRIMARY KEY, password VARCHAR, salesRepId VARCHAR(500), salesSiteId VARCHAR, role VARCHAR, username VARCHAR, bank VARCHAR, active NUMERIC, mainsite VARCHAR )";
        db.execSQL(createTableStatement);
        Log.i("Create Table User","Table User Created");
        String sqlCreateTableCustomer = "CREATE TABLE IF NOT EXISTS tbl_customer(sageIdentifier VARCHAR(1000) PRIMARY KEY, customerName TEXT, brn VARCHAR, vatNo VARCHAR, salesRepId VARCHAR, customerType VARCHAR, vatCode VARCHAR, creditLimit VARCHAR, amountOwned VARCHAR)";
        db.execSQL(sqlCreateTableCustomer);
        String createStatementComplaint = "CREATE TABLE IF NOT EXISTS tbl_complaint(complaintId VARCHAR(1000) PRIMARY KEY, customerName VARCHAR, address VARCHAR, phoneNum VARCHAR, email VARCHAR, dairy NUMERIC, dry NUMERIC, frozen NUMERIC, liquid NUMERIC, productName VARCHAR, productId VARCHAR, productDescription VARCHAR, dateOfPurchase DATE, placeOfPurchase VARCHAR, prodQuality NUMERIC, taste NUMERIC, deposit NUMERIC, packaging NUMERIC, solubility NUMERIC, expiry NUMERIC, others VARCHAR, problemDescription VARCHAR, correctiveAction VARCHAR)";
        db.execSQL(createStatementComplaint);
        String sqlCreateTableSales = "CREATE TABLE IF NOT EXISTS tbl_saleReceipt(invoiceId INTEGER PRIMARY KEY AUTOINCREMENT, customerId VARCHAR, date DATE, invoiceNumber VARCHAR, receiptNumber VARCHAR, saleType VARCHAR, amount DOUBLE, salesRepId VARCHAR, salesSiteId VARCHAR, chequeNum VARCHAR, bank VARCHAR,status VARCHAR)";
        db.execSQL(sqlCreateTableSales);

//        //Alexandre - Tbl_sale receipt creation
//        String sqlSaleReceipt = "CREATE TABLE IF NOT EXISTS tbl_saleReceipt(receiptId INTEGER PRIMARY KEY AUTOINCREMENT, customerId VARCHAR, date DATE, invoiceNumber VARCHAR, receiptNumber VARCHAR, saleType VARCHAR, amount REAL, salesRepId VARCHAR, salesSiteId VARCHAR, chequeNum VARCHAR, bank VARCHAR,status VARCHAR)";
//        db.execSQL(sqlSaleReceipt);
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





//        public void createTblInvoice()
//        {
//            SQLiteDatabase db1 = this.getReadableDatabase();
//            String createTblInvoice ="CREATE TABLE IF NOT EXISTS tbl_invoice(invoiceId INTEGER PRIMARY KEY, date DATE, status TEXT, invoiceNumber VARCHAR, deliveryNumber VARCHAR, orderNumber VARCHAR, salesSite VARCHAR, type VARCHAR, customerId VARCHAR, customerName VARCHAR, customerBrn VARCHAR, customerVatNo VARCHAR, customerVatCode VARCHAR, salesTypeId VARCHAR, addressId VARCHAR, addressName VARCHAR, userId VARCHAR, receiptNumber VARCHAR, mainSite VARCHAR, originalSalesRep VARCHAR, invoiceTotal VARCHAR, statusPost VARCHAR, cancelledOn Date)";
//            db1.execSQL(createTblInvoice);
//            Log.e("Table invoice","tbl_Invoice Created");
//        }
//
//        public void createTblSaleReceipt()
//        {
//
//            SQLiteDatabase db1 = this.getReadableDatabase();
//            String createTblSaleReceipt ="CREATE TABLE IF NOT EXISTS tbl_saleReceipt(receiptId VARCHAR(1000) PRIMARY KEY, customerId VARCHAR, date DATE, invoiceNumber VARCHAR, receiptNumber VARCHAR, saleType VARCHAR, amount REAL, salesRepId VARCHAR, salesSiteId VARCHAR, chequeNum VARCHAR, bank VARCHAR,status VARCHAR)";
//            db1.execSQL(createTblSaleReceipt);
//            Log.e("Table Sale Receipt","tbl_saleReceipt Created");
//        }








































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

    //#Varun - Create invoice lines
    public String createInvoice(ArrayList<InvoiceProductModel> invoice)
    {
        db = this.getWritableDatabase();
        String createInvoiceProduct = "CREATE TABLE IF NOT EXISTS tbl_invoiceProduct(invPrdId INTEGER PRIMARY KEY AUTOINCREMENT, discountAmount DOUBLE, discountPercentage DOUBLE, grossPrice DOUBLE, prodPrice DOUBLE, invoiceId VARCHAR, productId INT, selectedQty DOUBLE, vatAmount DOUBLE)";
        db.execSQL(createInvoiceProduct);

        for(int i = 0; i < invoice.size(); i++) {
            String insertInvoice = "INSERT INTO tbl_invoiceProduct( discountAmount, discountPercentage, grossPrice, prodPrice, invoiceId, productId, selectedQty, vatAmount) VALUES ('" +invoice.get(i).getDiscountAmount()+"', '"+invoice.get(i).getDiscountPercentage()+"', '"+invoice.get(i).getGrossPrice()+
                    "', '"+getProductPrice(invoice.get(i).getProduct().getSageIdentifier())+"', '"+invoice.get(i).getInvoiceNumber()+"', '"+invoice.get(i).getProduct().getSageIdentifier()+"', '"+invoice.get(i).getSelectedQty()+
                    "', '"+invoice.get(i).getVatAmount()+"')";
            db.execSQL(insertInvoice);
        }
        return "Complaints added succesfully";
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

    //#Varun - retrieve invoice product
    public Cursor getInvoiceProduct() {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_invoiceProduct as prod INNER JOIN tbl_invoice as inv ON prod.invoiceId = inv.invoiceNumber";
        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }


    //#Varun - retrieve invoice count
    public int getInvoiceCount() {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT DISTINCT invoiceNumber FROM tbl_invoice";
        Cursor res = db.rawQuery(selectTableStatement, null);
        return res.getCount();
    }

    //#Varun - retrieve sales receipt count
    public int getReceiptCount() {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT DISTINCT invoiceNumber FROM tbl_saleReceipt";
        Cursor res = db.rawQuery(selectTableStatement, null);
        return res.getCount();
    }

    public String getAddressId(String customerId) {
        String result = "";
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_address WHERE customerId ='" + customerId + "'";

        Cursor res = db.rawQuery(selectTableStatement, null);

        if(res.getCount() < 0){
            result = "address id Not found.";
        }
        else {
            while (res.moveToNext()){
                result = res.getString(0);
            }
        }
        return result;
    }





    //Alexandre -- Sales Receipt add to database on receipt print
    //generate Receipt Number




    //================================================================
//    public Cursor getSaleReceipt()
//    {
//        db = this.getWritableDatabase();
//        String selectStatement = "SELECT * FROM tbl_saleReceipt";
//
//        Cursor res = db.rawQuery(selectStatement,null);
//        return res;
//    }
    //================================================================


    public String ReceiptNumberGenerator(){
        String repNum;
        int reCount = 0;

        db = this.getWritableDatabase();
        String selectStatement = "SELECT * FROM tbl_saleReceipt";

        Cursor cursor = db.rawQuery(selectStatement,null);

        if(cursor.getCount()==0){
            reCount = 1;
        }
        else{
            while(cursor.moveToNext()){

                reCount +=1;
            }

            reCount +=1;
        }

        String date = new SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(new Date());

        repNum = "RE" + "SR00010" + date + reCount;


        return repNum;
    }


    public String addSaleReceipt(String cusName, String PayType, String amount, String chequeNum, String bank){

        Log.e("check"," enter function ");

        String sqlSaleReceipt = "CREATE TABLE IF NOT EXISTS tbl_saleReceipt(receiptId VARCHAR(1000) PRIMARY KEY, customerId VARCHAR, date DATE, invoiceNumber VARCHAR, receiptNumber VARCHAR, saleType VARCHAR, amount REAL, salesRepId VARCHAR, salesSiteId VARCHAR, chequeNum VARCHAR, bank VARCHAR,status VARCHAR)";
        db.execSQL(sqlSaleReceipt);
        String CustomerId="test";

        String repNum = ReceiptNumberGenerator();
        Log.e("reNum", repNum);
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Cursor customerCursor = getCustomerDetails(cusName);
        while(customerCursor.moveToNext()) {
            CustomerId = customerCursor.getString(0);
        }
        String ReceiptID = null;
        String InvoiceNumber = null;
        String SalesType = PayType;
        float Amount = Float.valueOf(amount);
        String SalesRepId = "SR00010";
        String SalesSiteId = "MV07";
        String ChequeNum = chequeNum;
        String Bank = bank;
        String status = "Open";

        String sqlDeleteSaleReceipt = "DELETE FROM tbl_saleReceipt WHERE receiptNumber = '" + repNum +"'";
        db.execSQL(sqlDeleteSaleReceipt);

        String sqlInsertSaleReceipt = "INSERT INTO tbl_saleReceipt (customerId, DATE, invoiceNumber, receiptNumber, saleType, amount, salesRepId, salesSiteId, chequeNum, bank, status)" +
                " VALUES ('" + CustomerId +"','" + date +"','" + InvoiceNumber +"','" + repNum +"','" + SalesType +"','" + amount +"','" + SalesRepId +"','" + SalesSiteId +"','" + ChequeNum +"','" + Bank +"','" + status +"')";
        db.execSQL(sqlInsertSaleReceipt);


        return "Print Receipt Added";
    }








    //#Varun - insert into tbl_receipt after print receipt
    public String createReceipt(ArrayList<SaleInvoiceModel> saleInvoice) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String customerId = "";
        String brn = "";
        String vatNo = "";
        String date = df.format(Calendar.getInstance().getTime());
        date = date.replace(" ","T");
        for(int i = 0; i < saleInvoice.size(); i++) {
            Cursor res = getCustomerDetails(saleInvoice.get(i).getCustomer());
            if(res.getCount() < 0){
                Log.e("Error","Customer details Not found.");
            }
            else {
                while (res.moveToNext()){
                    customerId = res.getString(0);
                    brn = res.getString(2);
                    vatNo = res.getString(3);
                }
            }

            String insertInvoice = "INSERT INTO tbl_invoice(date, status, invoiceNumber, deliveryNumber, orderNumber, salesSite, type, customerId, customerName, customerBrn, customerVatNo, customerVatCode, salesTypeId, addressId, addressName, userId, receiptNumber, mainSite, originalSalesRep, invoiceTotal, statusPost, cancelledOn) VALUES ('" + date + "', '" + "Open" + "', '" + saleInvoice.get(i).getInvoiceNumber() +
                    "', '" + saleInvoice.get(i).getDeliveryNumber() + "', '" + "" + "', '" + saleInvoice.get(i).getSalesSite() + "', '" + saleInvoice.get(i).getType() + "', '" + customerId + "', '" + saleInvoice.get(i).getCustomer() + "', '" + brn + "', '" + vatNo + "', '" + getCustomerVatCode(saleInvoice.get(i).getCustomer()) + "', '" + saleInvoice.get(i).getSalesType() + "', '" + getAddressId(customerId) + "', '" + saleInvoice.get(i).getAddress() + "', '" + saleInvoice.get(i).getUserId() + "', '" + saleInvoice.get(i).getReceiptNo() + "', '" + "EDLL" + "', '" + "" + "', '" + saleInvoice.get(i).getInvoiceTotal() + "', '" + "Not Posted" + "', '" + "" + "')";
            db.execSQL(insertInvoice);

            String insertSalesReceipt = "INSERT INTO tbl_saleReceipt(customerId, date, invoiceNumber, receiptNumber, saleType, amount, salesRepId, salesSiteId, chequeNum, bank, status) VALUES ('" + customerId + "', '" + date + "', '" + saleInvoice.get(i).getInvoiceNumber() +
                    "', '" + saleInvoice.get(i).getReceiptNo() + "', '" + saleInvoice.get(i).getType() + "', '" + saleInvoice.get(i).getInvoiceTotal() + "', '" + saleInvoice.get(i).getUserId() + "', '" + saleInvoice.get(i).getSalesSite() + "', '" + "" + "', '" + "" + "', '" + "Open" + "')";
            db.execSQL(insertSalesReceipt);
        }
        return "Invoice created successfully.";
    }

    //#Varun - get today's invoices
    public Cursor getAllCurrentDayInvoices() {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_invoice as inv INNER JOIN tbl_customer as customer ON customer.sageIdentifier = inv.customerId INNER JOIN tbl_address AS custAdd ON custAdd.sageIdentifier = inv.addressId WHERE inv.status = 'Open' order by inv.date";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getInvoiceHeader()
    {
        db = this.getWritableDatabase();
        String selectStatement = "SELECT * FROM tbl_invoice where statusPost = 'Not Posted'";

        Cursor res = db.rawQuery(selectStatement,null);
        return res;
    }

    public Cursor getInvoiceDetails(String invoiceNum)
    {
        db = this.getWritableDatabase();
        String selectStatement = "SELECT * FROM tbl_invoice where invoiceNumber ='" + invoiceNum + "'";

        Cursor res = db.rawQuery(selectStatement,null);
        return res;
    }

    //#Varun - get product vat
    public Cursor getProductVat(String customer, String prodName) {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_vat WHERE customerVatCode ='"+getCustomerVatCode(customer)+"' AND productVatRate='"+getProductVatRate(prodName)+"'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    //#Varun - get invoice details
    public Cursor getSearchInvoiceDetails(String invoiceNum) {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_invoiceProduct as invoice INNER JOIN tbl_product as product ON product.sageIdentifier = invoice.productId WHERE invoice.invoiceId ='"+invoiceNum+"'order by product.productName";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    //#Varun - get customer vatCode
    public String getCustomerVatCode(String customer) {
        String result = "";
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_customer WHERE customerName ='"+customer+"'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        if(res.getCount() < 0){
            result = "Customer VAT code Not found.";
        }
       else {
            while (res.moveToNext()){
                result = res.getString(6);
            }
        }
        return result;
    }

    //#Varun - get customer details
    public Cursor getCustomerDetails(String customer) {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_customer WHERE customerName ='"+customer+"'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    //#Varun - get product vatRate
    public String getProductVatRate(String product) {
        String result = "";
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_product WHERE productName ='"+product+"'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        if(res.getCount() < 0){
            result = "Product VAT Rate Not found.";
        }
        else {
            while (res.moveToNext()){
                result = res.getString(4);
            }
        }
        return result;
    }

    public Cursor getCustomer()
    {

        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM  " + CUSTOMER_TABLE;

        Cursor res = db.rawQuery(selectTableStatement,null);
        return res;

    }

    public Cursor getInvoice()
    {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT * FROM tbl_invoiceSelectedProd as selectedProd INNER JOIN tbl_product as product ON selectedProd.productId = product.sageIdentifier order by product.productName";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;

    }

    public Cursor getAllProducts() {
        db = this.getWritableDatabase();
        String selectTableStatement="SELECT productName FROM tbl_product order by 1";

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

    public Cursor getCustomerById(String customerId)
    {
        db = this.getWritableDatabase();
        String query = "SELECT * FROM tbl_customer WHERE sageIdentifier = '"+customerId+"'";
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    public Cursor getAddressInvoice(String addressId)
    {
        db = this.getWritableDatabase();
        String query = " SELECT * FROM tbl_address WHERE sageIdentifier = '"+addressId+"'";
        Cursor res = db.rawQuery(query,null);
        return res ;
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
            return false;
        }
        else
        {
            return true;
        }
    }

    //#Varun - Get selected invoice
    public Cursor getSelectedInvoice(String invoiceNumber) {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT * FROM tbl_invoiceProduct as invOrder INNER JOIN tbl_product as product ON product.sageIdentifier = invOrder.productId WHERE invOrder.invoiceId ='" + invoiceNumber + "'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    //#Varun - Reports section queries
    public Cursor getAllInvoicesCashCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT 'cash' AS type, sum(ip.prodPrice) AS amount, sum(ip.vatAmount) AS vat, sum(ip.discountAmount) AS disc FROM tbl_invoice i INNER JOIN tbl_invoiceProduct ip ON i.invoiceNumber = ip.invoiceId WHERE i.salesTypeId = 'Cash' AND i.status = 'Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getAllInvoicesCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT 'cash' AS type, count(*) AS total FROM tbl_invoice i WHERE salesTypeId = 'Cash' AND status = 'Open' UNION SELECT  'credit' AS type, count(*) AS total FROM tbl_invoice i WHERE salesTypeId = 'Credit' AND status = 'Open' UNION SELECT  'crn' AS type, count(*) AS total FROM tbl_invoice i WHERE status = 'Credit Note'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getAllInvoicesCreditCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT 'credit' AS type,  sum(ip.prodPrice) AS amount, sum(ip.vatAmount) AS vat, sum(ip.discountAmount) AS disc FROM tbl_invoice i INNER JOIN tbl_invoiceProduct ip ON i.invoiceNumber = ip.invoiceId WHERE i.salesTypeId = 'Credit' AND i.status = 'Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getAllInvoicesCrnCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(ip.prodPrice) AS amount, sum(ip.vatAmount) AS vat, sum(ip.discountAmount) AS disc FROM tbl_invoice i INNER JOIN tbl_invoiceProduct ip ON i.invoiceNumber = ip.invoiceId WHERE i.status = 'Credit Note'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getAllSalesReceiptCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(amount) AS totalAmount, count(*) AS totalCount FROM tbl_saleReceipt WHERE status = 'Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor cashInHand() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(amount) AS cashReceipt FROM tbl_saleReceipt WHERE saleType = 'Cash' AND status = 'Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getCheque() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(amount) AS chequeReceipt FROM tbl_saleReceipt WHERE saleType = 'Cheque' AND status ='Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getRemittance() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(amount) AS sumReceipt FROM tbl_saleReceipt WHERE status ='Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getProductiveCalls() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT count(*) AS totalCount FROM tbl_invoice WHERE status = 'Open'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getCancelledCash() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(ip.grossPrice*ip.selectedQty) AS amount, sum(ip.selectedQty) AS qty, sum(ip.vatAmount) AS vat, sum(ip.discountAmount) AS disc FROM tbl_invoice i INNER JOIN tbl_invoiceProduct ip ON i.invoiceNumber = ip.invoiceId WHERE i.status = 'Credit Note' AND i.salesTypeId = 'Cash'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getCancelledCredit() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(ip.grossPrice*ip.selectedQty) AS amount, sum(ip.selectedQty) AS qty, sum(ip.vatAmount) AS vat, sum(ip.discountAmount) AS disc FROM tbl_invoice i  INNER JOIN tbl_invoiceProduct ip ON  i.invoiceNumber = ip.invoiceId  WHERE i.salesTypeId = 'Credit' AND i.status = 'Credit Note'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;

    }

    public Cursor getCancelledCreditCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT count(*) AS totalCount FROM tbl_invoice WHERE status = 'Credit Note' AND salesTypeId = 'Credit'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getCancelledCashCount() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT count(*) AS totalCount FROM tbl_invoice WHERE status = 'Credit Note' AND salesTypeId = 'Cash'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getChequeReport() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT * FROM tbl_saleReceipt AS sR INNER JOIN tbl_customer AS c ON c.sageIdentifier = sR.customerId WHERE saleType = 'Cheque'";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getSalesReportProduct() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT sum(invProd.selectedQty) AS qty, prod.productName FROM tbl_invoice AS inv INNER JOIN tbl_invoiceProduct AS invProd ON inv.invoiceNumber = invProd.invoiceId INNER JOIN tbl_product AS prod ON prod.sageIdentifier = invProd.productId WHERE inv.status = 'Open' GROUP BY prod.productName";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    public Cursor getCurrentStockProduct() {
        db = this.getWritableDatabase();
        String selectTableStatement= "SELECT * FROM tbl_invoiceSelectedProd AS selectedProd INNER JOIN tbl_product AS product ON selectedProd.productId = product.sageIdentifier ORDER BY product.productName";

        Cursor res = db.rawQuery(selectTableStatement, null);
        return res;
    }

    //#Varun - Second Main Menu Queries
    public String saveComplaint(ComplaintsModel complaints)
    {
        db = this.getWritableDatabase();
        String createStatement = "CREATE TABLE IF NOT EXISTS tbl_complaintOrder(complaintId INTEGER PRIMARY KEY AUTOINCREMENT, date DATE, customerName VARCHAR, title VARCHAR, description VARCHAR, userId VARCHAR)";
        db.execSQL(createStatement);

        String insertStatement = "INSERT INTO tbl_complaintOrder(date, customerName, title, description, userId) VALUES ('"+complaints.getDate()+
                "', '"+complaints.getCustomerName()+"', '"+complaints.getTitle()+"', '"+complaints.getDescription()+
                "', '"+complaints.getUserId()+"')";

        db.execSQL(insertStatement);
        Log.e("List of complaint added",complaints.getCustomerName()+"");
        return "Complaints added succesfully";
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

    public String updateInvoice(String invoiceNumber)
    {
        db = this.getWritableDatabase();
        String query = "UPDATE tbl_invoice SET statusPost = 'Posted' WHERE invoiceNumber ='"+invoiceNumber+"'";
        db.execSQL(query);

        return "Invoice "+invoiceNumber+" updated Succesfully";
    }



}
