package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Storage<T> {
    private Object[] storage;
    private Cache<T> cache;
    private int capacity;

    public Object[] getStorage() {
        return storage;
    }

    public void setStorage(Object[] storage) {
        this.storage = storage;
    }

    public Cache<T> getCache() {
        return cache;
    }

    public void setCache(Cache<T> cache) {
        this.cache = cache;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Дефолтный конструктор класса
     */
    public Storage() {
        this.capacity = 10; // Значение по умолчанию
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
        if (element == null) {
            log.warn("Добавляемый елемент не должен быть null");
            throw new StorageElementNotExists("Добавляемый елемент не должен быть null ");
        }

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
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

        if (tmpElement == null) {
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
            log.warn("Ошибка в методе {}} : {}", "delete", e.getMessage());
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
