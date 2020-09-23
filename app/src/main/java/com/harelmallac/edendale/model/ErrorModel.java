package com.harelmallac.edendale.model;

import java.util.Date;

public class ErrorModel {

    private String error;
    private Date timestamp;
    private String description;
    private String salesRepId;
    private String variables;
    private String invokedFunction;

    public ErrorModel(){}

    public ErrorModel(String error, Date timestamp, String description, String salesRepId, String variables, String invokedFunction) {
        this.error = error;
        this.timestamp = timestamp;
        this.description = description;
        this.salesRepId = salesRepId;
        this.variables = variables;
        this.invokedFunction = invokedFunction;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public String getInvokedFunction() {
        return invokedFunction;
    }

    public void setInvokedFunction(String invokedFunction) {
        this.invokedFunction = invokedFunction;
    }
}
