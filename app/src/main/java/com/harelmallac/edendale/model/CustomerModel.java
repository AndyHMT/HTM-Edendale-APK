package com.harelmallac.edendale.model;

public class CustomerModel {

    private String sageIdentifier;
    private String customerName;
    private String brn;
    private String vatNo;
    private String salesRepId;
    private String customerType;
    private String vatCode;
    private String creditLimit;
    private String amountOwed;

    public CustomerModel(){}

    public CustomerModel(String sageIdentifier, String customerName, String brn, String vatNo, String salesRepId, String customerType, String vatCode, String creditLimit, String amountOwed) {
        this.sageIdentifier = sageIdentifier;
        this.customerName = customerName;
        this.brn = brn;
        this.vatNo = vatNo;
        this.salesRepId = salesRepId;
        this.customerType = customerType;
        this.vatCode = vatCode;
        this.creditLimit = creditLimit;
        this.amountOwed = amountOwed;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBrn() {
        return brn;
    }

    public void setBrn(String brn) {
        this.brn = brn;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(String amountOwed) {
        this.amountOwed = amountOwed;
    }
}
