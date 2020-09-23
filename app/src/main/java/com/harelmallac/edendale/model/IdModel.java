package com.harelmallac.edendale.model;

public class IdModel {

    private String productId;
    private String salesSiteId;

    public IdModel(){}

    public IdModel(String productId, String salesSiteId) {
        this.productId = productId;
        this.salesSiteId = salesSiteId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSalesSiteId() {
        return salesSiteId;
    }

    public void setSalesSiteId(String salesSiteId) {
        this.salesSiteId = salesSiteId;
    }
}
