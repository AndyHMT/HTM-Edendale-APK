package com.harelmallac.edendale.model;

public class VatModel {

    private String customerVatCode;
    private String productVatRate;
    private String vatRate;

    public VatModel(){}

    public VatModel(String customerVatCode, String productVatRate, String vatRate) {
        this.customerVatCode = customerVatCode;
        this.productVatRate = productVatRate;
        this.vatRate = vatRate;
    }

    public String getCustomerVatCode() {
        return customerVatCode;
    }

    public void setCustomerVatCode(String customerVatCode) {
        this.customerVatCode = customerVatCode;
    }

    public String getProductVatRate() {
        return productVatRate;
    }

    public void setProductVatRate(String productVatRate) {
        this.productVatRate = productVatRate;
    }

    public String getVatRate() {
        return vatRate;
    }

    public void setVatRate(String vatRate) {
        this.vatRate = vatRate;
    }
}
