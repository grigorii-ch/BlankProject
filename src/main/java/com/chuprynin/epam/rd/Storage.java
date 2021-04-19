package com.chuprynin.epam.rd;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class Storage<T> {
    private Object[] storage;
    private Cache<T> cache;
    private int capacity;

    public Cache<T> getCache() {
        return cache;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Дефолтный конструктор класса
     * capacity = 10 значение по умолчанию.
     */
    public Storage() {
        this.capacity = 10;
        this.storage = new Object[capacity];
        cache = new Cache<T>(capacity);
        log.debug("Создан обьект {}, c элементоми  Object[{}], Cache<T>({}) ", this.getClass().getName(), capacity, capacity);
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
        log.debug("Создан обьект {}, c элементоми  Object[{}], Cache<T>({}) ", this.getClass().getName(), capacity, capacity);
    }

    /**
     * Добавление елемента в массив, если достигнут предел длины - массив увеличивается в 1,5 раза
     *
     * @param element
     */
    public void add(T element) throws StorageElementNotExists {
        if (Objects.isNull(element)) {
            log.warn("Добавляемый елемент не должен быть null");
            throw new StorageElementNotExists("Добавляемый елемент не должен быть null ");
        }

        for (int i = 0; i < storage.length; i++) {
            if (Objects.isNull(storage[i])) {
                storage[i] = element;
                log.debug("Элемент {} добавлен в свободную ячейку", element);
                return;
            }
        }
        increaseLengthStorage(element);
    }

    /**
     * Медол увеличение длинны массива в 1,5 раза
     *
     * @param element
     */
    private void increaseLengthStorage(T element) {
        try {
            capacity = (int) (capacity * 1.5);
            Object[] newStorage = new Object[capacity];
            for (int i = 0; i < storage.length; i++) {
                newStorage[i] = storage[i];
            }
            newStorage[storage.length] = element;
            storage = newStorage;
            log.debug("Массив увеличен до {}, Элемент {} добавн в первую свободную ячейку", capacity, element);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка в методе {}} : {}, элемент {} не был добавлен", "increaseLengthStorage", e.getMessage(), element);
        }
    }

    /**
     * Удаление последнего элемента в кэше и массиве
     */
    public void delete() {
        T tmpElement = this.getLast();

        if (Objects.isNull(tmpElement)) {
            return;
        }

        if (cache.isPresent(tmpElement)) {
            cache.delete(tmpElement);
            log.debug("Элемент {} удален из cache", tmpElement);
        }

        try {
            for (int i = storage.length - 1; i >= 0; i--) {
                if (storage[i] != null && storage[i].equals(tmpElement)) {
                    storage[i] = null;
                    log.debug("Элемент {} удален из storage", tmpElement);
                    return;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка в методе {} : {}", "delete", e.getMessage());
        }
    }

    /**
     * Метод удаляет все из Кэша
     */
    public void clear() {
        cache.clear();
        for (int i = 0; i < storage.length; i++) {
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
     * получение элемента по индексу
     * Если нет в кэше, берем из нашего массива,
     * добавляем в кэш и возвращаем.
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

        try {
            cache.add((T) storage[index], index);
            return (T) storage[index];
        } catch (CacheElementNotExists e) {
            log.warn("Ошибка при получании элемента из массива по индексу {}", index);
            return null;
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
