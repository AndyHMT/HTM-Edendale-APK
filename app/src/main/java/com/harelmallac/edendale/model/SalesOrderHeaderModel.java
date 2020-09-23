package com.harelmallac.edendale.model;

import java.util.Date;

public class SalesOrderHeaderModel {

    private IdentityModel address;
    private IdentityModel customer;
    private Date date;
    private String origin;
    private String receiptNo;
    private String sageIdentifier;
    private String saleNumber;
    private IdentityModel salesSite;
    private IdentityModel salesType;
    private String status;
    private IdentityModel type;
    private String userId;
    private String mainSite;
    private String poNum;
    private String salesRepName;

    public SalesOrderHeaderModel(){}

    public SalesOrderHeaderModel(IdentityModel address, IdentityModel customer, Date date, String origin, String receiptNo, String sageIdentifier, String saleNumber, IdentityModel salesSite, IdentityModel salesType, String status, IdentityModel type, String userId, String mainSite, String poNum, String salesRepName) {
        this.address = address;
        this.customer = customer;
        this.date = date;
        this.origin = origin;
        this.receiptNo = receiptNo;
        this.sageIdentifier = sageIdentifier;
        this.saleNumber = saleNumber;
        this.salesSite = salesSite;
        this.salesType = salesType;
        this.status = status;
        this.type = type;
        this.userId = userId;
        this.mainSite = mainSite;
        this.poNum = poNum;
        this.salesRepName = salesRepName;
    }

    public IdentityModel getAddress() {
        return address;
    }

    public void setAddress(IdentityModel address) {
        this.address = address;
    }

    public IdentityModel getCustomer() {
        return customer;
    }

    public void setCustomer(IdentityModel customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public IdentityModel getSalesSite() {
        return salesSite;
    }

    public void setSalesSite(IdentityModel salesSite) {
        this.salesSite = salesSite;
    }

    public IdentityModel getSalesType() {
        return salesType;
    }

    public void setSalesType(IdentityModel salesType) {
        this.salesType = salesType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IdentityModel getType() {
        return type;
    }

    public void setType(IdentityModel type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMainSite() {
        return mainSite;
    }

    public void setMainSite(String mainSite) {
        this.mainSite = mainSite;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }
}
