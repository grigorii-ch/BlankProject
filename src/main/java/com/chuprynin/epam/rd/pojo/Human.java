package com.chuprynin.epam.rd.pojo;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;

@Entity()
public class Human {
    @Value()
    private int age;
    @Value()
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
