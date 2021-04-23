package com.chuprynin.epam.rd.pojo;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;

@Entity()
public class Human {
    private int age;
    @Value(value = "Grisha", path = "name")
    private String name;

    public int getAge() {
        return age;
    }

    @Value(value = "25")
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
