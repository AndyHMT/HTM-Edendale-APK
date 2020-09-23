package com.harelmallac.edendale.model;

import java.util.Date;

public class ComplaintsModel {

    private String customerName;
    private String title;
    private String description;
    private Date date;
    private String userId;

    public ComplaintsModel(){}

    public ComplaintsModel(String customerName, String title, String description, Date date, String userId) {
        this.customerName = customerName;
        this.title = title;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
