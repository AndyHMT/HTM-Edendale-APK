package com.harelmallac.edendale.model;

public class SalesTypeModel {

    private String sageIdentifier;
    private String salesTypeName;

    public SalesTypeModel(){}

    public SalesTypeModel(String sageIdentifier, String salesTypeName) {
        this.sageIdentifier = sageIdentifier;
        this.salesTypeName = salesTypeName;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getSalesTypeName() {
        return salesTypeName;
    }

    public void setSalesTypeName(String salesTypeName) {
        this.salesTypeName = salesTypeName;
    }
}
