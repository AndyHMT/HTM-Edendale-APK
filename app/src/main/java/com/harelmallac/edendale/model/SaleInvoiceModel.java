package com.harelmallac.edendale.model;

import java.util.Date;

public class SaleInvoiceModel {

    private String sageIdentifier;
    private Date date;
    private String deliveryNumber;
    private String invoiceNumber;
    private String status;
    private IdentityModel address;
    private IdentityModel customer;
    private IdentityModel salesSite;
    private IdentityModel salesType;
    private IdentityModel type;
    private String userId;
    private String vanId;
    private String poNum;
    private String receiptNo;
    private String mainSite;
    private String orignalSalesRepId;
    private Date creationTime;
    private String invoiceTotal;

    public SaleInvoiceModel(){}

    public SaleInvoiceModel(String sageIdentifier, Date date, String deliveryNumber, String invoiceNumber, String status, IdentityModel address, IdentityModel customer, IdentityModel salesSite, IdentityModel salesType, IdentityModel type, String userId, String vanId, String poNum, String receiptNo, String mainSite, String orignalSalesRepId, Date creationTime, String invoiceTotal) {
        this.sageIdentifier = sageIdentifier;
        this.date = date;
        this.deliveryNumber = deliveryNumber;
        this.invoiceNumber = invoiceNumber;
        this.status = status;
        this.address = address;
        this.customer = customer;
        this.salesSite = salesSite;
        this.salesType = salesType;
        this.type = type;
        this.userId = userId;
        this.vanId = vanId;
        this.poNum = poNum;
        this.receiptNo = receiptNo;
        this.mainSite = mainSite;
        this.orignalSalesRepId = orignalSalesRepId;
        this.creationTime = creationTime;
        this.invoiceTotal = invoiceTotal;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getVanId() {
        return vanId;
    }

    public void setVanId(String vanId) {
        this.vanId = vanId;
    }

    public String getPoNum() {
        return poNum;
    }

    public void setPoNum(String poNum) {
        this.poNum = poNum;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getMainSite() {
        return mainSite;
    }

    public void setMainSite(String mainSite) {
        this.mainSite = mainSite;
    }

    public String getOrignalSalesRepId() {
        return orignalSalesRepId;
    }

    public void setOrignalSalesRepId(String orignalSalesRepId) {
        this.orignalSalesRepId = orignalSalesRepId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(String invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }
}
