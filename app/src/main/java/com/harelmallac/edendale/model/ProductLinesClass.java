package com.harelmallac.edendale.model;

public class ProductLinesClass {
    String id;
    String name;
    String qty;
    String price;
    String total;
    String discount;

    public ProductLinesClass(String id, String name, String qty, String price, String total, String discount) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
