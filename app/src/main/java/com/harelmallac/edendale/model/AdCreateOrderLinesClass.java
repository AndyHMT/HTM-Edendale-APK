package com.harelmallac.edendale.model;

public class AdCreateOrderLinesClass {
    private String COrdProdName;
    private String CordQty;
    private String CordPrice;
    private String CordTotal;

    public AdCreateOrderLinesClass(String COrdProdName, String cordQty, String cordPrice, String cordTotal) {
        this.COrdProdName = COrdProdName;
        CordQty = cordQty;
        CordPrice = cordPrice;
        CordTotal = cordTotal;
    }

    public String getCOrdProdName() {
        return COrdProdName;
    }

    public void setCOrdProdName(String COrdProdName) {
        this.COrdProdName = COrdProdName;
    }

    public String getCordQty() {
        return CordQty;
    }

    public void setCordQty(String cordQty) {
        CordQty = cordQty;
    }

    public String getCordPrice() {
        return CordPrice;
    }

    public void setCordPrice(String cordPrice) {
        CordPrice = cordPrice;
    }

    public String getCordTotal() {
        return CordTotal;
    }

    public void setCordTotal(String cordTotal) {
        CordTotal = cordTotal;
    }
}
