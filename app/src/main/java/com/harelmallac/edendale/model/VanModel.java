package com.harelmallac.edendale.model;

public class VanModel {

    private String name;
    private String f_1;
    private String f_2;

    public VanModel(){}

    public VanModel(String name, String f_1, String f_2) {
        this.name = name;
        this.f_1 = f_1;
        this.f_2 = f_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getF_1() {
        return f_1;
    }

    public void setF_1(String f_1) {
        this.f_1 = f_1;
    }

    public String getF_2() {
        return f_2;
    }

    public void setF_2(String f_2) {
        this.f_2 = f_2;
    }
}
