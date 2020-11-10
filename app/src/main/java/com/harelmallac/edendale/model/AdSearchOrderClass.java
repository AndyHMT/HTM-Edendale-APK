package com.harelmallac.edendale.model;

public class AdSearchOrderClass {

    private String SordCustName;
    private String SordAddress;

    public AdSearchOrderClass(String sordCustName, String sordAddress) {
        SordCustName = sordCustName;
        SordAddress = sordAddress;
    }

    public String getSordCustName() {
        return SordCustName;
    }

    public void setSordCustName(String sordCustName) {
        SordCustName = sordCustName;
    }

    public String getSordAddress() {
        return SordAddress;
    }

    public void setSordAddress(String sordAddress) {
        SordAddress = sordAddress;
    }

}
