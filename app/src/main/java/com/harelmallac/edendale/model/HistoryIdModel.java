package com.harelmallac.edendale.model;

public class HistoryIdModel {

    private String productId;
    private String customerId;

    public HistoryIdModel(){}

    public HistoryIdModel(String productId, String customerId) {
        this.productId = productId;
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
