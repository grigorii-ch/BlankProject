package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Cache<String> cacheString = new Cache<>(5);

        try {
            cacheString.add("Test1", 1);
        } catch (CacheElementNotExists e) {
            log.info("Ошибка при заполнении обьекта Cache: {}", e.printFullStackTrace());
        }

        try {
            cacheString.add(null, 2);
        } catch (CacheElementNotExists e) {
            log.info("Ошибка при заполнении обьекта Cache: {}", e.printFullStackTrace());
        }

        try {
            cacheString.add("Test3", 3);
        } catch (CacheElementNotExists e) {
            log.info("Ошибка при заполнении обьекта Cache: {}", e.printFullStackTrace());
        }

        try {
            cacheString.add("Test4", 4);
        } catch (CacheElementNotExists e) {
            log.info("Ошибка при заполнении обьекта Cache: {}", e.printFullStackTrace());
        }

        try {
            cacheString.add("Test5", 5);
        } catch (CacheElementNotExists e) {
            log.info("Ошибка при заполнении обьекта Cache: {}", e.printFullStackTrace());
        }

        cacheString.delete("Test1");
        cacheString.clear();

        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", "Test5"});

        String[] storageElements = new String[]{"TRATATA0","TRATATA1","TRATATA2","TRATATA3","TRATATA4",
                "TRATATA5","TRATATA6","TRATATA7",null,"TRATATA9","TRATATA10"};

        for (String elem : storageElements) {
            try {
                storage.add(elem);
            } catch (StorageElementNotExists e) {
                log.trace("Ошибка: при добавлении элемента {}%n{}", elem,  e.printFullStackTrace());

                continue;
            }
        }

        storage.delete();

        storage.clear();
    }
}
