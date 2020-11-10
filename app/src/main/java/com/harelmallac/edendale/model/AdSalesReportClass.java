package com.harelmallac.edendale.model;

public class AdSalesReportClass {
    private String SRepQty;
    private String SRepProduct;

    public AdSalesReportClass(String SRepQty, String SRepProduct) {
        this.SRepQty = SRepQty;
        this.SRepProduct = SRepProduct;
    }

    public String getSRepQty() {
        return SRepQty;
    }

    public void setSRepQty(String SRepQty) {
        this.SRepQty = SRepQty;
    }

    public String getSRepProduct() {
        return SRepProduct;
    }

    public void setSRepProduct(String SRepProduct) {
        this.SRepProduct = SRepProduct;
    }
}
