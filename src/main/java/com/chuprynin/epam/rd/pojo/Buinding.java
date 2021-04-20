package com.chuprynin.epam.rd.pojo;

import com.chuprynin.epam.rd.annotation.Value;

public class Buinding {
    @Value
    private int number;
    @Value
    private String street;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
