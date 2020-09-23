package com.harelmallac.edendale.model;

public class typeModel {

    private String sageIdentifier;
    private String typeName;

    public typeModel(){}

    public typeModel(String sageIdentifier, String typeName) {
        this.sageIdentifier = sageIdentifier;
        this.typeName = typeName;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
