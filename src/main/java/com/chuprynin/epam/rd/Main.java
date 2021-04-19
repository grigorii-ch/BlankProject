package com.chuprynin.epam.rd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("Пример проверки класса Cache");
        Cache<String> cacheString = new Cache<>(5);
        try {
            cacheString.add("Test1", 1);
            cacheString.add("Test2", 2);
            cacheString.add("Test3", 3);
            cacheString.add("Test4", 4);
            cacheString.add("Test5", 5);
        } catch (CacheElementNotExists e) {
            log.warn("Ошибка {}", e.getMessage(), e);
        }

        log.debug("Добавили 5 элементов: {}", cacheString.toString());

        log.debug("Получаем 3 элемент по index: {}", cacheString.get(3));

        log.debug("Проверить Test1 : {}", cacheString.isPresent("Test1"));

        log.debug("Проверить Test100: {}", cacheString.isPresent("Test100"));

        cacheString.delete("Test1");
        log.debug("Удалить Test1 : {}", cacheString.toString());

        cacheString.clear();
        log.debug("Очистить : {}", cacheString.toString());


        log.debug("Пример проверки класса Storage");
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", "Test5"});
        log.debug("Создание через конструктор с 5 элементами: {}", storage.toString());


        storage.add("TRATATA0");
        storage.add("TRATATA1");
        storage.add("TRATATA2");
        storage.add("TRATATA3");
        storage.add("TRATATA4");
        storage.add("TRATATA5");

        log.debug("Добавление 5 новых элементов : {}", storage.toString());

        log.debug("Получить последний элемент: {}", storage.getLast());

        log.debug("Получание  3 элемента: {}", storage.get(3));

        storage.delete();
        log.debug("Удалить последний элемент {}", storage.toString());

        storage.clear();
        log.debug("Очистить массив: {}", storage.toString());
    }
}
