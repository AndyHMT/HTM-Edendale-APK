package com.harelmallac.edendale.model;

import java.util.Date;

public class StockTakeModel {

    private Date dateTime;
    private double shelfStock;
    private String storeStock;
    private IdentityModel customer;
    private IdentityModel product;
    private String addressId;

    public StockTakeModel(){}

    public StockTakeModel(Date dateTime, double shelfStock, String storeStock, IdentityModel customer, IdentityModel product, String addressId) {
        this.dateTime = dateTime;
        this.shelfStock = shelfStock;
        this.storeStock = storeStock;
        this.customer = customer;
        this.product = product;
        this.addressId = addressId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getShelfStock() {
        return shelfStock;
    }

    public void setShelfStock(double shelfStock) {
        this.shelfStock = shelfStock;
    }

    public String getStoreStock() {
        return storeStock;
    }

    public void setStoreStock(String storeStock) {
        this.storeStock = storeStock;
    }

    public IdentityModel getCustomer() {
        return customer;
    }

    public void setCustomer(IdentityModel customer) {
        this.customer = customer;
    }

    public IdentityModel getProduct() {
        return product;
    }

    public void setProduct(IdentityModel product) {
        this.product = product;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
