package com.harelmallac.edendale.model;

import java.util.Date;

public class ReceiptModel {

    private float amount;
    private String bank;
    private String chequeBank;
    private String chequeNo;
    private String customerId;
    private Date date;
    private String invoiceNo;
    private String paymentType;
    private String receiptNo;
    private String salesRepId;
    private String salesSiteId;

    public ReceiptModel(){}

    public ReceiptModel(float amount, String bank, String chequeBank, String chequeNo, String customerId, Date date, String invoiceNo, String paymentType, String receiptNo, String salesRepId, String salesSiteId) {
        this.amount = amount;
        this.bank = bank;
        this.chequeBank = chequeBank;
        this.chequeNo = chequeNo;
        this.customerId = customerId;
        this.date = date;
        this.invoiceNo = invoiceNo;
        this.paymentType = paymentType;
        this.receiptNo = receiptNo;
        this.salesRepId = salesRepId;
        this.salesSiteId = salesSiteId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getChequeBank() {
        return chequeBank;
    }

    public void setChequeBank(String chequeBank) {
        this.chequeBank = chequeBank;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getSalesSiteId() {
        return salesSiteId;
    }

    public void setSalesSiteId(String salesSiteId) {
        this.salesSiteId = salesSiteId;
    }
}

