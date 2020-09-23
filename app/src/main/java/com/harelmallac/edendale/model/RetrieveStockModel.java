package com.harelmallac.edendale.model;

public class RetrieveStockModel {

    private IdModel id;
    private float quantity;

    public RetrieveStockModel(){}

    public RetrieveStockModel(IdModel id, float quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public IdModel getId() {
        return id;
    }

    public void setId(IdModel id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
