package com.chuprynin.epam.rd.pojo;

import com.chuprynin.epam.rd.annotation.Value;

/**
 * Pojo класс с аннотакиями @Value на полях класса
 */
public class Building {
    @Value
    private int number;
    @Value
    private String street;

    /**
     * Возвращает значение поля number
     *
     * @return int значение поля number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Установка значения для поля number
     *
     * @param number - int значение
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Возвращает значение поля street
     *
     * @return String значение поля street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Установка значения для поля street
     *
     * @param street - String значение
     */
    public void setStreet(String street) {
        this.street = street;
    }
}
