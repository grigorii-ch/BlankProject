package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.exceptions.CacheElementNotExists;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс для работы с параметризованным массивом
 *
 * @param <T> Тип которым будет параметризирован класс.
 */
@Slf4j
@Getter
public class Cache<T> {
    /**
     * массив элементов типа CacheElement<T>[] с именем cache
     */
    private final CacheElement<T>[] cache;
    /**
     * размером массива cache
     */
    private final int capacity;

    /**
     * Конструктор, создает массив CacheElement<T>[] cache
     * с длиной capacity
     *
     * @param capacity - длина массива
     */
    @SuppressWarnings("unchecked")
    public Cache(int capacity) {
        this.capacity = capacity;
        cache = (CacheElement<T>[]) new CacheElement<?>[this.capacity];
        log.info("Создан объект {}, с длиной массива {}", this.getClass().getName(), capacity);
    }

    /**
     * Добавление element в массив cache
     * если массив полный - будет добавлен в конец со сдвигом в лево остальных
     *
     * @param element добавляемый элемент типа Т
     * @param index   индекс добавляемого элемента
     * @throws CacheElementNotExists - если element = null
     */
    public void add(T element, int index) throws CacheElementNotExists {
        if (Objects.isNull(element)) {
            log.warn("Добавляемый элемент  не должен быть null");
            throw new CacheElementNotExists("Добавляемый элемент /объект не должен быть null ");
        }

        CacheElement<T> cacheElement = new CacheElement<>(element, index);

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
     * @param element удаляемый элемент типа Т
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
     * @param element искомый элемент типа Т
     * @return trye - когда элемент был найден в массиве, в противном случае вернется false
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
     * @param index индекс искомого элемента типа int
     * @return trye - когда элемент был найден в массиве, в противном случае вернется false
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
     * @param index индекс запрашиваемого элемент типа int
     * @return элемента типа T - из массива cache, null - если элемент не был найден или произошла ошибка
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        try {
            for (int i = 0; i < capacity; i++) {
                if (index == cache[i].getIndex()) {
                    CacheElement<T> foundedElement = cache[i];
                    moveSubRangeToLeft(i);
                    cache[capacity - 1] = foundedElement;
                    return (T) foundedElement.getElement();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка в методе {} при добавлении элемента в Cache : {}", "get", e.getMessage());
            return null;
        }
        return null;
    }

    /**
     * очистка массива cache
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        log.info("Массив очищен.");
    }

    /**
     * Двигаем массив от переданной позиции в лево от позици position
     *
     * @param position - номеро индекса типа int
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

    @Override
    public String toString() {
        return "Cache{" +
                "cache=" + Arrays.toString(cache) +
                ", capacity=" + capacity +
                '}';
    }
}