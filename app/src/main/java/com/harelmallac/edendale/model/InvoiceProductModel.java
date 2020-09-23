package com.harelmallac.edendale.model;

public class InvoiceProductModel {

    private double discountAmount;
    private double discountPercentage;
    private double grossPrice;
    private String invoiceNumber;
    private IdentityModel product;
    private double selectedQty;
    private double vatAmount;

    public InvoiceProductModel(){}

    public InvoiceProductModel(double discountAmount, double discountPercentage, double grossPrice, String invoiceNumber, IdentityModel product, double selectedQty, double vatAmount) {
        this.discountAmount = discountAmount;
        this.discountPercentage = discountPercentage;
        this.grossPrice = grossPrice;
        this.invoiceNumber = invoiceNumber;
        this.product = product;
        this.selectedQty = selectedQty;
        this.vatAmount = vatAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(double grossPrice) {
        this.grossPrice = grossPrice;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public IdentityModel getProduct() {
        return product;
    }

    public void setProduct(IdentityModel product) {
        this.product = product;
    }

    public double getSelectedQty() {
        return selectedQty;
    }

    public void setSelectedQty(double selectedQty) {
        this.selectedQty = selectedQty;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }
}

