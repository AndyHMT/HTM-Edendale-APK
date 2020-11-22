package com.harelmallac.edendale.model;

import java.util.Date;

public class StockRequestModel {

    private float requestedQty;
    private String requestId;
    private String userId;
    private IdentityModel product;
    private String location;
    private String company;
    private String date;

    private StockRequestModel(){}

    public StockRequestModel(float requestedQty, String requestId, String userId, IdentityModel product, String location, String company, String date) {
        this.requestedQty = requestedQty;
        this.requestId = requestId;
        this.userId = userId;
        this.product = product;
        this.location = location;
        this.company = company;
        this.date = date;
    }

    public float getRequestedQty() {
        return requestedQty;
    }

    public void setRequestedQty(float requestedQty) {
        this.requestedQty = requestedQty;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public IdentityModel getProduct() {
        return product;
    }

    public void setProduct(IdentityModel product) {
        this.product = product;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
