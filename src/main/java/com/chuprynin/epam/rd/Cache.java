package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.exceptions.CacheElementNotExists;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс для работы с параметризованным массивом
 *
 * @param <T>
 */
@Slf4j
@Getter
public class Cache<T> {
    private CacheElement<T>[] cache;
    private int capacity;

    /**
     * Конструктор, создает массив с переданной длиной в параметре
     *
     * @param capacity
     */
    public Cache(int capacity) {
        this.capacity = capacity;
        cache = new CacheElement[this.capacity];
        log.info("Создан объект {}, с длиной массива {}", this.getClass().getName(), capacity);
    }

    /**
     * Добавление элемента в не занятый индекс массива
     * если массив полный - будет добавлен в конец со сдвигом в лево остальных
     *
     * @param element
     */
    public void add(T element, int index) throws CacheElementNotExists {
        if (Objects.isNull(element)) {
            log.warn("Добавляемый элемент  не должен быть null");
            throw new CacheElementNotExists("Добавляемый элемент /объект не должен быть null ");
        }

        CacheElement cacheElement = new CacheElement(element, index);

        for (int i = 0; i < capacity; i++) {
            if (cache[i] == null) {
                cache[i] = cacheElement;
                log.debug("Объект  {} c индексом {} добавлен в {} позицию,", element, index, i);
                return;
            }
        }

        moveSubRangeToLeft(0);

        cache[capacity - 1] = cacheElement;
        log.debug("Объект {} c индексом {} добавлен в конец - {} позицию, ", element, index, capacity);
    }

    /**
     * Удаление элемента, если такой присутствует в массиве
     * остальные элементы двигаются в лево
     *
     * @param element
     */
    public void delete(T element) {
        if (isPresent(element)) {
            for (int i = 0; i < capacity; i++) {
                if (element.equals(cache[i].getElement())) {
                    moveSubRangeToLeft(i);
                    cache[capacity - 1] = null;
                    log.debug("Элемент {} был удален", element);
                    return;
                }
            }
        }
    }

    /**
     * Проверка наличия элемента в массиве
     *
     * @param element
     * @return boolean
     */
    public boolean isPresent(T element) {
        if (Objects.isNull(element)) {
            log.info("Попытка удаления элемента с индексом NULL");
            return false;
        }

        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && element.equals(cache[i].getElement())) {
                log.debug("Данный элемент {}, был найден в массиве с длинной {} в позиции {}", element, capacity, i);
                return true;
            }
        }
        log.debug("Данный элемент {}, не был найден в массиве с длинной {}", element, capacity);
        return false;
    }

    /**
     * Проверка наличия элемента в массиве
     *
     * @param index
     * @return boolean
     */
    public boolean isPresent(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && index == cache[i].getIndex()) {
                log.debug("Элемент с индексом {}, был найден в массиве с длинной {} в позиции {}", index, capacity, i);
                return true;
            }
        }
        log.debug("Элемент с индексом {}, не был найден в массиве с длинной {}", index, capacity);
        return false;
    }

    /**
     * получение элемента из массива
     * При нахождении элемента в кэше он перемещается в конец массива,
     * с учетом сдвига остальных элементов влево.
     *
     * @param index
     * @return T, null
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        try {
            for (int i = 0; i < capacity; i++) {
                if (index == cache[i].getIndex()) {
                    CacheElement foundedElement = cache[i];
                    moveSubRangeToLeft(i);
                    cache[capacity - 1] = foundedElement;
                    return (T) foundedElement.getElement();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка в методе {} при добавлении элемента в Cache : {}", "get", e.getMessage());
            return (T) new Object();
        }
        return (T) new Object();
    }

    /**
     * очистка массива
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        log.info("Массив очищен.");
    }

    /**
     * Двигаем массив от переданной позиции в лево
     *
     * @param position
     */
    private void moveSubRangeToLeft(int position) {
        try {
            for (int i = position; i < capacity - 1; i++) {
                cache[i] = cache[i + 1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка в методе {} при добавлении элемента в Cache : {}", "moveSubRangeToLeft", e.getMessage());
        }
    }
}
