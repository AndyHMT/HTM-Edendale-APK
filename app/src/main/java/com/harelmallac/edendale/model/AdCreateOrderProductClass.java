package com.harelmallac.edendale.model;

public class AdCreateOrderProductClass {

    private String CordProdct;
    private String CordQtyStore;
    private String CordproQty;

    public AdCreateOrderProductClass(String cordProdct, String cordQtyStore, String cordproQty) {
        CordProdct = cordProdct;
        CordQtyStore = cordQtyStore;
        CordproQty = cordproQty;
    }

    public String getCordProdct() {
        return CordProdct;
    }

    public void setCordProdct(String cordProdct) {
        CordProdct = cordProdct;
    }

    public String getCordQtyStore() {
        return CordQtyStore;
    }

    public void setCordQtyStore(String cordQtyStore) {
        CordQtyStore = cordQtyStore;
    }

    public String getCordproQty() {
        return CordproQty;
    }

    public void setCordproQty(String cordproQty) {
        CordproQty = cordproQty;
    }
}
