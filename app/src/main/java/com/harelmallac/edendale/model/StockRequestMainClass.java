package com.harelmallac.edendale.model;

public class StockRequestMainClass {
    private String stkprodName;
    private String stkprodQty;

    public StockRequestMainClass(String stkprodName, String stkprodQty) {
        this.stkprodName = stkprodName;
        this.stkprodQty = stkprodQty;
    }

    public String getStkprodName() {
        return stkprodName;
    }

    public void setStkprodName(String stkprodName) {
        this.stkprodName = stkprodName;
    }

    public String getStkprodQty() {
        return stkprodQty;
    }

    public void setStkprodQty(String stkprodQty) {
        this.stkprodQty = stkprodQty;
    }
}
