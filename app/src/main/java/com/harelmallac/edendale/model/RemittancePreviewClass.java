package com.harelmallac.edendale.model;

import com.android.volley.toolbox.StringRequest;

public class RemittancePreviewClass {

    private String RprevNum;
    private String RprevQty;
    private String RprevAmt;

    public RemittancePreviewClass(String rprevNum, String rprevQty, String rprevAmt) {
        RprevNum = rprevNum;
        RprevQty = rprevQty;
        RprevAmt = rprevAmt;
    }

    public String getRprevNum() {
        return RprevNum;
    }

    public void setRprevNum(String rprevNum) {
        RprevNum = rprevNum;
    }

    public String getRprevQty() {
        return RprevQty;
    }

    public void setRprevQty(String rprevQty) {
        RprevQty = rprevQty;
    }

    public String getRprevAmt() {
        return RprevAmt;
    }

    public void setRprevAmt(String rprevAmt) {
        RprevAmt = rprevAmt;
    }
}
