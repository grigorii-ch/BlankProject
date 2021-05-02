package com.chuprynin.epam.rd.pojo;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;

/**
 * Pojo класс с аннотациями: Value на полях и методах класса, Entity на классе
 */
@Entity
public class Human {
    @Value(value = "99")
    private int age;
    @Value(value = "Grisha")
    private String name;

    /**
     * Возвращает значение поля age
     *
     * @return int значение поля age
     */
    public int getAge() {
        return age;
    }

    /**
     * Установка значения для поля age
     *
     * @param age - int значение
     */
    @Value(value = "99")
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Возвращает значение поля name
     *
     * @return String значение поля name
     */
    public String getName() {
        return name;
    }

    /**
     * Установка значения для поля name
     *
     * @param name - String значение
     */
    @Value(value = "Grisha")
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
