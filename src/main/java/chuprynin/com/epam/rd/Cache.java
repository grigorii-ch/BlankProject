package chuprynin.com.epam.rd;

import java.util.Arrays;

/**
 * Класс для работы с параментрированным массивом
 *
 * @param <T>
 */
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
    }

    /**
     * Добавление елемента в не занятый индекс массива
     * еслм массмв полный - будет добавлен в конец со сдвигом в лево остальных
     *
     * @param element
     */
    public void add(T element, int index) {
        CacheElement cacheElement = new CacheElement(element, index);
        for (int i = 0; i < capacity; i++) {
            if (cache[i] == null) {
                cache[i] = cacheElement;
                return;
            }
        }
        moveSubRangeToLeft(0);
        cache[capacity - 1] = cacheElement;
    }

    /**
     * Удаление элемента, если такой присутствует в массиве
     * остальные елементы двигаются в лево
     *
     * @param element
     */
    public void delete(T element) {
        if (isPresent(element)) {
            for (int i = 0; i < capacity; i++) {
                if (element.equals(cache[i].getElement())) {
                    moveSubRangeToLeft(i);
                    cache[capacity - 1] = null;
                    return;
                }
            }
        }
    }

    /**
     * Двигаем массив от переданой позиции в лево
     *
     * @param position
     */
    private void moveSubRangeToLeft(int position) {
        for (int i = position; i < capacity - 1; i++) {
            cache[i] = cache[i + 1];
        }
    }

    /**
     * Проверка наличия эллемента в массиве
     *
     * @param element
     * @return boolean
     */
    public boolean isPresent(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && element.equals(cache[i].getElement())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка наличия эллемента в массиве
     *
     * @param index
     * @return boolean
     */
    public boolean isPresent(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && index == cache[i].getIndex()) {
                return true;
            }
        }
        return false;
    }

    /**
     * получение эллемента из массива
     * При нахождении элемента в кэше он перемещается в конец массива,
     * с учетом сдвига остальных элементов влево.
     *
     * @param index
     * @return T, null
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        for (int i = 0; i < capacity; i++) {
            if (index == cache[i].getIndex()) {
                CacheElement foundedElement;
                foundedElement = cache[i];
                moveSubRangeToLeft(i);
                cache[capacity - 1] = foundedElement;
                return (T) foundedElement.getElement();
            }
        }
        return null;
    }

    /**
     * очистка массива
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
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
