package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.exceptions.CacheElementNotExists;
import com.chuprynin.epam.rd.exceptions.StorageElementNotExists;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс для работы с параметрезированным массивом и параметрезированным обьектом Cache
 *
 * @param <T> Тип которым будет параметризирован класс.
 */
@Slf4j
@Getter
public class Storage<T> {

    /**
     * массив типа Т storagestorage
     */
    private Object[] storage;
    /**
     * обьект типа Т cache
     */
    private final Cache<T> cache;
    /**
     * размером массива storage
     */
    private int capacity;

    /**
     * Дефолтный конструктор класса
     * создает массив storage, обьект cache
     * capacity = 10 значение по умолчанию.
     */
    public Storage() {
        this.capacity = 10;
        this.storage = new Object[capacity];
        cache = new Cache<T>(capacity);
        log.info("Создан объект {}, c элементами  Object[{}], Cache<T>({}) ", this.getClass().getName(), capacity, capacity);
    }

    /**
     * Дефолтный конструктор класса
     * создает массив storage, обьект cache
     * capacity = длине переданного в параметре массива
     *
     * @param storage массив обьектов
     */
    public Storage(Object[] storage) {
        this.capacity = storage.length;
        this.storage = storage;
        cache = new Cache<T>(capacity);
        log.info("Создан объект {}, c элементами  Object[{}], Cache<T>({}) ", this.getClass().getName(), capacity, capacity);
    }

    /**
     * Добавление елемента в массив, если достигнут предел длины - массив увеличивается в 1,5 раза
     *
     * @param element добавляемый элемент типа Т
     */
    public void add(T element) throws StorageElementNotExists {
        if (Objects.isNull(element)) {
            log.warn("Добавляемый элемент не должен быть null");
            throw new StorageElementNotExists("Добавляемый элемент не должен быть null ");
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
     * Удаление последнего элемента в кэше и массиве
     */
    public void delete() {
        T tmpElement = this.getLast();

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
     * Метод удаляет все из массива storage и чистм cache
     */
    public void clear() {
        cache.clear();
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        log.debug("Кэш очищен.");
    }

    /**
     * получение последнего элемента из массива
     *
     * @return последний элемент массива типа T, если элемент не найден возвращаем null
     */
    @SuppressWarnings("unchecked")
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
     * @param index - индекс элемента который мы хотим получить.
     * @return найденый элемент массива типа T, если элемент не найден возвращаем null
     */
    @SuppressWarnings("unchecked")
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
            log.warn("Ошибка при получении элемента из массива по индексу {}", index);
            return null;
        }
    }

    /**
     * Медод увеличение длинны массива в 1,5 раза
     *
     * @param element добавляемый елемент типа T
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
            log.info("Массив увеличен до {}, Элемент {} добавн в первую свободную ячейку", capacity, element);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка в методе {}} : {}, элемент {} не был добавлен", "increaseLengthStorage", e.getMessage(), element);
        }
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storage=" + Arrays.toString(storage) +
                ", cache=" + cache +
                ", capacity=" + capacity +
                '}';
    }
}
