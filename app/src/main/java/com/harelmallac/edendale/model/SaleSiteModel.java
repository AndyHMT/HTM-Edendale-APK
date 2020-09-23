package com.harelmallac.edendale.model;

public class SaleSiteModel {

    private String sageIdentifier;
    private String salesSiteName;

    private SaleSiteModel(){}

    public SaleSiteModel(String sageIdentifier, String salesSiteName) {
        this.sageIdentifier = sageIdentifier;
        this.salesSiteName = salesSiteName;
    }


    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getSalesSiteName() {
        return salesSiteName;
    }

    public void setSalesSiteName(String salesSiteName) {
        this.salesSiteName = salesSiteName;
    }
}
