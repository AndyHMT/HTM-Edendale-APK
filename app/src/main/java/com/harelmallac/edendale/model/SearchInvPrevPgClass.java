package com.harelmallac.edendale.model;

public class SearchInvPrevPgClass {
    private String PrevPgName;
    private String PrevPgQty;
    private String PrevPgPrice;
    private String PrevPgTotal;

    public SearchInvPrevPgClass(String prevPgName, String prevPgQty, String prevPgPrice, String prevPgTotal) {
        PrevPgName = prevPgName;
        PrevPgQty = prevPgQty;
        PrevPgPrice = prevPgPrice;
        PrevPgTotal = prevPgTotal;
    }


    public String getPrevPgName() {
        return PrevPgName;
    }

    public void setPrevPgName(String prevPgName) {
        PrevPgName = prevPgName;
    }

    public String getPrevPgQty() {
        return PrevPgQty;
    }

    public void setPrevPgQty(String prevPgQty) {
        PrevPgQty = prevPgQty;
    }

    public String getPrevPgPrice() {
        return PrevPgPrice;
    }

    public void setPrevPgPrice(String prevPgPrice) {
        PrevPgPrice = prevPgPrice;
    }

    public String getPrevPgTotal() {
        return PrevPgTotal;
    }

    public void setPrevPgTotal(String prevPgTotal) {
        PrevPgTotal = prevPgTotal;
    }
}
