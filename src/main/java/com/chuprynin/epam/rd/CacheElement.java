package com.chuprynin.epam.rd;

import lombok.Data;

import java.util.Objects;

/**
 * Параметризированный класс, модель - хранения элементов в классе Кэш
 *
 * @param <T>
 */
@Data
public class CacheElement<T> {
    private T element;
    private int index;

    /**
     * Дефолтный конструктор
     *
     * @param element
     * @param index
     */
    public CacheElement(T element, int index) {
        this.element = element;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheElement<?> that = (CacheElement<?>) o;
        return index == that.index && Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, index);
    }
}
