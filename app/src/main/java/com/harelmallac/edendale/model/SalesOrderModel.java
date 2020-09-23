package com.harelmallac.edendale.model;

public class SalesOrderModel {

    private float discountAmount;
    private float discountPercentage;
    private float grossPrice;
    private IdentityModel product;
    private String saleNumber;
    private String selectedQty;
    private float vatAmount;

    public SalesOrderModel(){}

    public SalesOrderModel(float discountAmount, float discountPercentage, float grossPrice, IdentityModel product, String saleNumber, String selectedQty, float vatAmount) {
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.grossPrice = grossPrice;
        this.product = product;
        this.saleNumber = saleNumber;
        this.selectedQty = selectedQty;
        this.vatAmount = vatAmount;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(float grossPrice) {
        this.grossPrice = grossPrice;
    }

    public IdentityModel getProduct() {
        return product;
    }

    public void setProduct(IdentityModel product) {
        this.product = product;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(String selectedQty) {
        this.selectedQty = selectedQty;
    }

    public float getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(float vatAmount) {
        this.vatAmount = vatAmount;
    }
}
