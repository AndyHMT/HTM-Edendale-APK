package com.harelmallac.edendale.model;

public class StockRequestProductClass {
    private String StockProductName;
    private String StockProductQty;

    public StockRequestProductClass(String stockProductName, String stockProductQty) {
        StockProductName = stockProductName;
        StockProductQty = stockProductQty;
    }

    public String getStockProductName() {
        return StockProductName;
    }

    public void setStockProductName(String stockProductName) {
        StockProductName = stockProductName;
    }

    public String getStockProductQty() {
        return StockProductQty;
    }

    public void setStockProductQty(String stockProductQty) {
        StockProductQty = stockProductQty;
    }
}
