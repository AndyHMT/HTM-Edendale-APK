package com.harelmallac.edendale.model;

import java.util.Date;

public class OrderHistoryModel {

    private HistoryIdModel id;
    private Date date;
    private double qty;

    public OrderHistoryModel(){}

    public OrderHistoryModel(HistoryIdModel id, Date date, double qty) {
        this.id = id;
        this.date = date;
        this.qty = qty;
    }

    public HistoryIdModel getId() {
        return id;
    }

    public void setId(HistoryIdModel id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }
}
