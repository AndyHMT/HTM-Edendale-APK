package com.harelmallac.edendale.model;

public class ItemClass {
    String id;
    String name;
    String total;
    String discount;
    String quantity;

    public ItemClass(String id, String name, String total, String discount, String quantity) {
        this.id = id;
        this.name = name;
        this.total = total;
        this.discount = discount;
        this.quantity = quantity;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
