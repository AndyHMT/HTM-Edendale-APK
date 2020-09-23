package com.harelmallac.edendale.model;

import java.util.Date;

public class CancelOrderModel {

    private String cancelComments;
    private String cancelledBy;
    private Date cancelledOn;
    private String salesNumber;

    public CancelOrderModel(){}

    public String getCancelComments() {
        return cancelComments;
    }

    public void setCancelComments(String cancelComments) {
        this.cancelComments = cancelComments;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public Date getCancelledOn() {
        return cancelledOn;
    }

    public void setCancelledOn(Date cancelledOn) {
        this.cancelledOn = cancelledOn;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }
}
