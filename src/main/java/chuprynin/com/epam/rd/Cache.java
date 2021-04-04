package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Класс для работы с параментрированным массивом
 *
 * @param <T>
 */
@Slf4j
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
        log.debug(String.format("Создан обьект %s, с длиной массива %d", this.getClass().getName(), capacity));
    }

    /**
     * Добавление елемента в не занятый индекс массива
     * еслм массмв полный - будет добавлен в конец со сдвигом в лево остальных
     *
     * @param element
     */
    public void add(T element, int index) throws CheckedNoSuchElementException {
        if (element == null) {
            log.warn("Добавляемый елемент не должен быть null");
            throw new CheckedNoSuchElementException("Добавляемый елемент/обьект не должен быть null ");
        }

        CacheElement cacheElement = new CacheElement(element, index);

        for (int i = 0; i < capacity; i++) {
            if (cache[i] == null) {
                cache[i] = cacheElement;
                log.debug(String.format("Обьект %s c индексом %d добавлен в %d позицию, ", element, index, i-1));
                return;
            }
        }
        moveSubRangeToLeft(0);
        cache[capacity - 1] = cacheElement;
        log.debug(String.format("Обьект %s c индексом %d добавлен в конец - %d позицию, ", element, index, capacity - 1));
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
                    log.debug(String.format("Элемент %s был удален", element));
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
        try {
            for (int i = position; i < capacity - 1; i++) {
                cache[i] = cache[i + 1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn(String.format("Ошибка в методе %s при добавлении элемента в Cache : %s", "moveSubRangeToLeft", e.getMessage()));
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
                log.debug("Данный элемент %s, был найден в массиве с длинной %d", element, capacity);
                return true;
            }
        }
        log.debug("Данный элемент %s, не был найден в массиве с длинной %d", element, capacity);
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
                log.debug("Элемент с индексом %d, был найден в массиве с длинной %d", index, capacity);
                return true;
            }
        }
        log.debug("Элемент с индексом %d, не был найден в массиве с длинной %d", index, capacity);
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
            try {
                if (index == cache[i].getIndex()) {
                    CacheElement foundedElement = cache[i];
                    moveSubRangeToLeft(i);
                    cache[capacity - 1] = foundedElement;
                    return (T) foundedElement.getElement();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                log.warn(String.format("Ошибка в методе %s при добавлении элемента в Cache : %s", "get", e.getMessage()));
                return null;
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
