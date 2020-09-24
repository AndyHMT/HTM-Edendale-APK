package com.harelmallac.edendale.model;

public class ProductModel {

    private String sageIdentifier;
    private String productName;
    private String productType;
    private double quantity;
    private String vatRate;
    private String unit;
    private String subCat1;
    private String subCat2;
    private String subCat3;
    private String subCat4;
    private String subCat5;
    private float percentageProd;

    public ProductModel(){}

    public ProductModel(String sageIdentifier, String productName, String productType, double quantity, String vatRate, String unit, String subCat1, String subCat2, String subCat3, String subCat4, String subCat5) {
        this.sageIdentifier = sageIdentifier;
        this.productName = productName;
        this.productType = productType;
        this.quantity = quantity;
        this.vatRate = vatRate;
        this.unit = unit;
        this.subCat1 = subCat1;
        this.subCat2 = subCat2;
        this.subCat3 = subCat3;
        this.subCat4 = subCat4;
        this.subCat5 = subCat5;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getVatRate() {
        return vatRate;
    }

    public void setVatRate(String vatRate) {
        this.vatRate = vatRate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSubCat1() {
        return subCat1;
    }

    public void setSubCat1(String subCat1) {
        this.subCat1 = subCat1;
    }

    public String getSubCat2() {
        return subCat2;
    }

    public void setSubCat2(String subCat2) {
        this.subCat2 = subCat2;
    }

    public String getSubCat3() {
        return subCat3;
    }

    public void setSubCat3(String subCat3) {
        this.subCat3 = subCat3;
    }

    public String getSubCat4() {
        return subCat4;
    }

    public void setSubCat4(String subCat4) {
        this.subCat4 = subCat4;
    }

    public String getSubCat5() {
        return subCat5;
    }

    public void setSubCat5(String subCat5) {
        this.subCat5 = subCat5;
    }

    public float getPercentageProd() {
        return percentageProd;
    }

    public void setPercentageProd(float percentageProd) {
        this.percentageProd = percentageProd;
    }
}

