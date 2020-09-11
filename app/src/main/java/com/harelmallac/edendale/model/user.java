package com.harelmallac.edendale.model;

public class user {

    private int userId;
    private String password;
    private String repNum;
    private String salesSiteId;
    private String role;
    private String mainsite;
    private String bank;
    private boolean active;

    public user(int userId, String password, String repNum, String salesSiteId, String role, String mainsite, String bank, boolean active) {
        this.userId = userId;
        this.password = password;
        this.repNum = repNum;
        this.salesSiteId = salesSiteId;
        this.role = role;
        this.mainsite = mainsite;
        this.bank = bank;
        this.active = active;
    }

    public user()
    {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepNum() {
        return repNum;
    }

    public void setRepNum(String repNum) {
        this.repNum = repNum;
    }

    public String getSalesSiteId() {
        return salesSiteId;
    }

    public void setSalesSiteId(String salesSiteId) {
        this.salesSiteId = salesSiteId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMainsite() {
        return mainsite;
    }

    public void setMainsite(String mainsite) {
        this.mainsite = mainsite;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
