package com.harelmallac.edendale.model;

public class AdminSearchRejectedOrderClass {

    private String AdSchRejCustomer;
    private String AdSchRejDate;
    private int AdSchRejView;

    public AdminSearchRejectedOrderClass(String adSchRejCustomer, String adSchRejDate, int adSchRejView) {
        AdSchRejCustomer = adSchRejCustomer;
        AdSchRejDate = adSchRejDate;
        AdSchRejView = adSchRejView;
    }

    public String getAdSchRejCustomer() {
        return AdSchRejCustomer;
    }

    public void setAdSchRejCustomer(String adSchRejCustomer) {
        AdSchRejCustomer = adSchRejCustomer;
    }

    public String getAdSchRejDate() {
        return AdSchRejDate;
    }

    public void setAdSchRejDate(String adSchRejDate) {
        AdSchRejDate = adSchRejDate;
    }

    public int getAdSchRejView() {
        return AdSchRejView;
    }

    public void setAdSchRejView(int adSchRejView) {
        AdSchRejView = adSchRejView;
    }
}
