package com.harelmallac.edendale.model;

public class CurrentStockReportClass {

    private String CSRQty;
    private String CSRProduct;

    public CurrentStockReportClass(String CSRQty, String CSRProduct) {
        this.CSRQty = CSRQty;
        this.CSRProduct = CSRProduct;
    }

    public String getCSRQty() {
        return CSRQty;
    }

    public void setCSRQty(String CSRQty) {
        this.CSRQty = CSRQty;
    }

    public String getCSRProduct() {
        return CSRProduct;
    }

    public void setCSRProduct(String CSRProduct) {
        this.CSRProduct = CSRProduct;
    }
}
