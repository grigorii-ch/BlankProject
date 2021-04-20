package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.exceptions.CacheElementNotExists;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Старт программы");
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
        log.debug("Добавили 5 элементов: {}", cacheString);

        log.debug("Получаем 3 элемент по index: {}", cacheString.get(3));

        log.debug("Проверить Test1 : {}", cacheString.isPresent("Test1"));

        log.debug("Проверить Test100: {}", cacheString.isPresent("Test100"));

        cacheString.delete("Test1");
        log.debug("Удалить Test1 : {}", cacheString);

        cacheString.clear();
        log.debug("Очистить : {}", cacheString.toString());

        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3"});
        log.debug("Создание класса Storage через конструктор с 3 элементами: {}", storage);

        storage.add("TRATATA1");
        storage.add("TRATATA2");
        storage.add("TRATATA3");

        log.debug("Добавление 3 новых элементов : {}", storage);

        log.debug("Получить последний элемент: {}", storage.getLast());

        log.debug("Получание  3 элемента: {}", storage.get(3));

        storage.delete();
        log.debug("Удалить последний элемент {}", storage);

        storage.clear();
        log.debug("Очистить массив: {}", storage);

        log.info("Завершение программы");
    }
}
