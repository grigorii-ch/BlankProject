package chuprynin.com.epam.rd;

import java.util.Arrays;

public class Storage<T> {
    private Object[] storage;
    private Cache<T> cache;
    private int capacity = 10;

    /**
     * Дефолтный конструктор класса
     */
    public Storage() {
        this.storage = new Object[capacity];
        cache = new Cache<T>(capacity);
    }

    /**
     * Конструктор класса
     *
     * @param storage
     */
    public Storage(Object[] storage) {
        this.capacity = storage.length;

        this.storage = storage;
        cache = new Cache<T>(capacity);
    }

    /**
     * Добавление елемента в массив, если достигнут предел длины - массив увеличивается в 1,5 раза
     *
     * @param element
     */
    public void add(T element) {
        boolean isFullStorage = true;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = element;
                isFullStorage = false;
                break;
            }
        }

        if (isFullStorage) {
            capacity = (int) (capacity * 1.5);
            Object[] newStorage = new Object[capacity];

            for (int i = 0; i < storage.length; i++) {
                newStorage[i] = storage[i];
            }

            newStorage[storage.length] = element;
            storage = newStorage;
        }
    }

    /**
     * Удаление последнего элемента в кэше и массиве
     */
    public void delete() {
        T tmpElement = null;
        int tmpIdex = -1;

        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i].equals(getLast())) {
                tmpElement = (T) storage[i];
                tmpIdex = i;
                break;
            }
        }

        if (tmpElement != null) {
            if (cache.isPresent(tmpElement)) {
                cache.delete(tmpElement);
                storage[tmpIdex] = null;
            }
        }
    }

    /**
     * Метод удаляет все из Кэша
     */
    public void clear() {
        cache.clear();
    }

    /**
     * метод получения последнего элемента из массива
     *
     * @return
     */
    public T getLast() {
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i] != null) {
                return (T) storage[i];
            }
        }
        return null;
    }

    /**
     * метод получения элемента из массива по индексу
     * Если объекта не оказалось в кэше, то берем объект из нашего массива, добавляем его в кэш и возвращаем.
     *
     * @param index
     * @return
     */
    public T get(int index) {
        T tmpElement = null;

        try {
            tmpElement = cache.get(index);
            return tmpElement;
        } catch (Exception e) {
            System.out.println("В кэше отсутсвует елемент с индеком " + index + " ищем в хранилище");
        }

        if (capacity < index) {
            System.out.println("В хранилище отсутсвует елемент с индеком " + index);
            return null;

        } else {
            tmpElement = (T) storage[index];
            cache.add((T) tmpElement, index);
            return tmpElement;
        }
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storage=" + Arrays.toString(storage) +
                ", cache=" + cache +
                '}';
    }
}
