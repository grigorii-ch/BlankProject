package com.chuprynin.epam.rd.task2.pojo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * POJO класс описывающий характеристики колбасы
 */
@Data
@RequiredArgsConstructor
public class Sausage {
    @NonNull
    private String type;
    @NonNull
    private int weight;
    @NonNull
    private long cost;
}
