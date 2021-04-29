package com.chuprynin.epam.rd.task2.pojo;

/**
 * POJO класс описывающий характеристики колбасы
 */
public class Sausage {
    private String type;
    private int weight;
    private long cost;

    /**
     *
     * @param type - тип
     * @param weight - вес
     * @param cost - цена
     */
    public Sausage(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Sausage{" +
                "type='" + type + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
