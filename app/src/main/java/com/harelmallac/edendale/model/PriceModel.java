package com.harelmallac.edendale.model;

public class PriceModel {

    private String customerId;
    private String productId;
    private float price;
    private int priority;

    public PriceModel(){}

    public PriceModel(String customerId, String productId, float price, int priority) {
        this.customerId = customerId;
        this.productId = productId;
        this.price = price;
        this.priority = priority;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
