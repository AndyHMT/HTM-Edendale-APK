package com.harelmallac.edendale.model;

public class ChequeReportClass {

    private String name;
    private String invoice;
    private String receipt;
    private String amount;

    public ChequeReportClass(String name, String invoice, String receipt, String amount) {
        this.name = name;
        this.invoice = invoice;
        this.receipt = receipt;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
