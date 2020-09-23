package com.harelmallac.edendale.model;

public class IdentityModel {

    private String sageIdentifier;

    public IdentityModel(){}

    public IdentityModel(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }
}
