package com.harelmallac.edendale.model;

public class UserModel {

    private String userId;
    private String username;
    private String password;
    private String repNum;
    private String salesSiteId;
    private String role;
    private String mainsite;
    private String bank;
    private boolean active;

    public UserModel(String userId, String password, String repNum, String salesSiteId, String role, String username, String bank, boolean active, String mainsite  ) {
        this.userId = userId;
        this.password = password;
        this.repNum = repNum;
        this.salesSiteId = salesSiteId;
        this.role = role;
        this.mainsite = mainsite;
        this.bank = bank;
        this.active = active;
        this.username = username;
    }

    public UserModel()
    {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
