package com.harelmallac.edendale.model;

public class AdminSkTkMainPageClass {

    private String productName;
    private String Qtystore;

    public AdminSkTkMainPageClass(String productName, String qtystore) {
        this.productName = productName;
        Qtystore = qtystore;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQtystore() {
        return Qtystore;
    }

    public void setQtystore(String qtystore) {
        Qtystore = qtystore;
    }
}
