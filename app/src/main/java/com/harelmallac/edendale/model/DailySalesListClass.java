package com.harelmallac.edendale.model;

public class DailySalesListClass {
    private String DSQty;
    private String DSProduct;

    public DailySalesListClass(String DSQty, String DSProduct) {
        this.DSQty = DSQty;
        this.DSProduct = DSProduct;
    }

    public String getDSQty() {
        return DSQty;
    }

    public void setDSQty(String DSQty) {
        this.DSQty = DSQty;
    }

    public String getDSProduct() {
        return DSProduct;
    }

    public void setDSProduct(String DSProduct) {
        this.DSProduct = DSProduct;
    }
}
