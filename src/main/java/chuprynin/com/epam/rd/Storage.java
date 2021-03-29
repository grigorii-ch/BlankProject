package chuprynin.com.epam.rd;

import java.util.Arrays;

public class Storage<T> {
    private Object[] storage;
    private Cache<T> cache;
    private int capacity;

    /**
     * Дефолтный конструктор класса
     */
    public Storage() {
        this.capacity = 10; // Значение по умолчанию
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
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = element;
                return;
            }
        }
        increaseLengthStorage(element);
    }

    /**
     * Медол увеличение длинны массива в 1,5 раза
     * @param element
     */
    private void increaseLengthStorage(T element) {
        capacity = (int) (capacity * 1.5);
        Object[] newStorage = new Object[capacity];
        for (int i = 0; i < storage.length; i++) {
            newStorage[i] = storage[i];
        }
        newStorage[storage.length] = element;
        storage = newStorage;
    }

    /**
     * Удаление последнего элемента в кэше и массиве
     */
    public void delete() {
        T tmpElement = this.getLast();
        if (tmpElement != null) {
            if (cache.isPresent(tmpElement)) {
                cache.delete(tmpElement);
            }
            for (int i = storage.length - 1; i >= 0; i--) {
                if (storage[i] != null && storage[i].equals(tmpElement)) {
                    storage[i] = null;
                    return;
                }
            }
        }
    }

    /**
     * Метод удаляет все из Кэша
     */
    public void clear() {
        cache.clear();
        for(int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    /**
     * метод получения последнего элемента из массива
     *
     * @return T, null
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
     * @return T, null
     */
    public T get(int index) {
        if (cache.isPresent(index)) {
            return cache.get(index);
        }
        if (capacity < index) {
            return null;
        }
        cache.add((T) storage[index], index);
        return (T) storage[index];
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storage=" + Arrays.toString(storage) +
                ", cache=" + cache +
                '}';
    }
}
