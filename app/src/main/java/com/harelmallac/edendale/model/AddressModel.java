package com.harelmallac.edendale.model;

public class AddressModel {

    private String sageIdentifier;
    private String addressId;
    private String name;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String customerId;

    public AddressModel(){}

    public AddressModel(String sageIdentifier, String addressId, String name, String addressLine1, String addressLine2, String city, String customerId) {
        this.sageIdentifier = sageIdentifier;
        this.addressId = addressId;
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.customerId = customerId;
    }

    public String getSageIdentifier() {
        return sageIdentifier;
    }

    public void setSageIdentifier(String sageIdentifier) {
        this.sageIdentifier = sageIdentifier;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
