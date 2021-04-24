package com.chuprynin.epam.rd.pojo;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;

@Entity
public class Student {
    @Value(value = "100", path = "age")
    private int age;
    @Value(value = "Ivan", path = "name")
    private String name;

    public int getAge() {
        return age;
    }

    @Value(value = "100", path = "age")
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Value(value = "Ivan", path = "name")
    public void setName(String name) {
        this.name = name;
    }
}
