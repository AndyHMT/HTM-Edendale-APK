package com.harelmallac.edendale.model;

public class ChequeReportClass {

    private String CRQty;
    private String CRProduct;

    public ChequeReportClass(String CRQty, String CRProduct) {
        this.CRQty = CRQty;
        this.CRProduct = CRProduct;
    }

    public String getCRQty() {
        return CRQty;
    }

    public void setCRQty(String CRQty) {
        this.CRQty = CRQty;
    }

    public String getCRProduct() {
        return CRProduct;
    }

    public void setCRProduct(String CRProduct) {
        this.CRProduct = CRProduct;
    }
}
