package com.harelmallac.edendale.model;

import android.widget.ImageView;

public class SearchInvoiceClass {
    private String sInvName;
    private int sInvprint;
    private int sInvView;
    private int sInvCancel;

    public SearchInvoiceClass(String sInvName, int sInvprint, int sInvView, int sInvCancel) {
        this.sInvName = sInvName;
        this.sInvprint = sInvprint;
        this.sInvView = sInvView;
        this.sInvCancel = sInvCancel;
    }

    public String getsInvName() {
        return sInvName;
    }

    public void setsInvName(String sInvName) {
        this.sInvName = sInvName;
    }

    public int getsInvprint() {
        return sInvprint;
    }

    public void setsInvprint(int sInvprint) {
        this.sInvprint = sInvprint;
    }

    public int getsInvView() {
        return sInvView;
    }

    public void setsInvView(int sInvView) {
        this.sInvView = sInvView;
    }

    public int getsInvCancel() {
        return sInvCancel;
    }

    public void setsInvCancel(int sInvCancel) {
        this.sInvCancel = sInvCancel;
    }
}
