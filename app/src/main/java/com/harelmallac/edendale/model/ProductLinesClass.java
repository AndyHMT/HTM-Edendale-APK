package com.harelmallac.edendale.model;

public class ProductLinesClass {
    String Name;
    String Qty;
    String Price;
    String Total;

    public ProductLinesClass(String name, String qty, String price, String total) {
        Name = name;
        Qty = qty;
        Price = price;
        Total = total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
