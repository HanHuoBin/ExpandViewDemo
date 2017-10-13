package com.expand.demo.model;

/**
 * Created by hanbin on 2017/10/13.
 */

public class CityModel {
    private String name;
    private int id;
    private String icon;
    private String number;

    public CityModel(String name, int id, String icon, String number) {
        this.name = name;
        this.id = id;
        this.icon = icon;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
