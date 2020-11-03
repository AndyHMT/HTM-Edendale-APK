package com.harelmallac.edendale.model;

import java.util.Date;

public class SalesmanComplaintsModel {

    private String complantDescription;
    private String customerAddress;
    private String customerName;
    private boolean dairy;
    private boolean deposit;
    private boolean dry;
    private String email;
    private boolean expiry;
    private String firstCorrectiveAction;
    private boolean frozen;
    private boolean liquid;
    private String others;
    private boolean packaging;
    private String phoneNo;
    private IdentityModel product;
    private String productDescription;
    private boolean productQuality;
    private String purchaseDate;
    private String purchasePlace;
    private boolean solubility;
    private boolean taste;
    private String timestamp;

    public SalesmanComplaintsModel(){}

    public SalesmanComplaintsModel(String complantDescription, String customerAddress, String customerName, boolean dairy, boolean deposit, boolean dry, String email, boolean expiry, String firstCorrectiveAction, boolean frozen, boolean liquid, String others, boolean packaging, String phoneNo, IdentityModel product, String productDescription, boolean productQuality, String purchaseDate, String purchasePlace, boolean solubility, boolean taste, String timestamp) {
        this.complantDescription = complantDescription;
        this.customerAddress = customerAddress;
        this.customerName = customerName;
        this.dairy = dairy;
        this.deposit = deposit;
        this.dry = dry;
        this.email = email;
        this.expiry = expiry;
        this.firstCorrectiveAction = firstCorrectiveAction;
        this.frozen = frozen;
        this.liquid = liquid;
        this.others = others;
        this.packaging = packaging;
        this.phoneNo = phoneNo;
        this.product = product;
        this.productDescription = productDescription;
        this.productQuality = productQuality;
        this.purchaseDate = purchaseDate;
        this.purchasePlace = purchasePlace;
        this.solubility = solubility;
        this.taste = taste;
        this.timestamp = timestamp;
    }

    public String getComplantDescription() {
        return complantDescription;
    }

    public void setComplantDescription(String complantDescription) {
        this.complantDescription = complantDescription;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public boolean isDairy() {
        return dairy;
    }

    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    public boolean isDeposit() {
        return deposit;
    }

    public void setDeposit(boolean deposit) {
        this.deposit = deposit;
    }

    public boolean isDry() {
        return dry;
    }

    public void setDry(boolean dry) {
        this.dry = dry;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExpiry() {
        return expiry;
    }

    public void setExpiry(boolean expiry) {
        this.expiry = expiry;
    }

    public String getFirstCorrectiveAction() {
        return firstCorrectiveAction;
    }

    public void setFirstCorrectiveAction(String firstCorrectiveAction) {
        this.firstCorrectiveAction = firstCorrectiveAction;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isLiquid() {
        return liquid;
    }

    public void setLiquid(boolean liquid) {
        this.liquid = liquid;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public boolean isPackaging() {
        return packaging;
    }

    public void setPackaging(boolean packaging) {
        this.packaging = packaging;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public IdentityModel getProduct() {
        return product;
    }

    public void setProduct(IdentityModel product) {
        this.product = product;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public boolean isProductQuality() {
        return productQuality;
    }

    public void setProductQuality(boolean productQuality) {
        this.productQuality = productQuality;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public boolean isSolubility() {
        return solubility;
    }

    public void setSolubility(boolean solubility) {
        this.solubility = solubility;
    }

    public boolean isTaste() {
        return taste;
    }

    public void setTaste(boolean taste) {
        this.taste = taste;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
