package com.harelmallac.edendale.model;

public class SearchInvoiceCancelClass {
    private String sInCanName;
    private int sInCanvprint;
    private int sInCanView;

    public SearchInvoiceCancelClass(String sInCanName, int sInCanvprint, int sInCanView) {
        this.sInCanName = sInCanName;
        this.sInCanvprint = sInCanvprint;
        this.sInCanView = sInCanView;
    }

    public String getsInCanName() {
        return sInCanName;
    }

    public void setsInCanName(String sInCanName) {
        this.sInCanName = sInCanName;
    }

    public int getsInCanvprint() {
        return sInCanvprint;
    }

    public void setsInCanvprint(int sInCanvprint) {
        this.sInCanvprint = sInCanvprint;
    }

    public int getsInCanView() {
        return sInCanView;
    }

    public void setsInCanView(int sInCanView) {
        this.sInCanView = sInCanView;
    }
}
