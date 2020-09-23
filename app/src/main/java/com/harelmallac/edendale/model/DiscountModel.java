package com.harelmallac.edendale.model;

public class DiscountModel {

    private String customerId;
    private String discount1;
    private String discount2;
    private String discount3;
    private String productId;

    public DiscountModel(){}

    public DiscountModel(String customerId, String discount1, String discount2, String discount3, String productId) {
        this.customerId = customerId;
        this.discount1 = discount1;
        this.discount2 = discount2;
        this.discount3 = discount3;
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDiscount1() {
        return discount1;
    }

    public void setDiscount1(String discount1) {
        this.discount1 = discount1;
    }

    public String getDiscount2() {
        return discount2;
    }

    public void setDiscount2(String discount2) {
        this.discount2 = discount2;
    }

    public String getDiscount3() {
        return discount3;
    }

    public void setDiscount3(String discount3) {
        this.discount3 = discount3;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
