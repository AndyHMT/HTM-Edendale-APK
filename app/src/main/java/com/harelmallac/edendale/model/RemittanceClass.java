package com.harelmallac.edendale.model;

public class RemittanceClass {
    private String rmtNum;
    private String rmtQty;

    public RemittanceClass(String rmtNum, String rmtQty) {
        this.rmtNum = rmtNum;
        this.rmtQty = rmtQty;
    }

    public String getRmtNum() {
        return rmtNum;
    }

    public void setRmtNum(String rmtNum) {
        this.rmtNum = rmtNum;
    }

    public String getRmtQty() {
        return rmtQty;
    }

    public void setRmtQty(String rmtQty) {
        this.rmtQty = rmtQty;
    }
}
