package com.chuprynin.epam.rd;

import lombok.Getter;
import lombok.Setter;

/**
 * Параметризированный класс, модель - хранения элементов в классе Кэш
 *
 * @param <T> - Тип которым будет параметризирован класс.
 *
 */
@Getter
@Setter
public class CacheElement<T> {
    /**
     * элемент типа Т
     */
    private T element;
    /**
     * индекс с которым был запрос элемента из массива
     */
    private int index;

    /**
     * Дефолтный конструктор
     *
     * @param element элемент типа Т
     * @param index индекс элемента с которым был запрос элемента из массива
     */
    public CacheElement(T element, int index) {
        this.element = element;
        this.index = index;
    }

    @Override
    public String toString() {
        return "CacheElement{" +
                "element=" + element +
                ", index=" + index +
                '}';
    }
}