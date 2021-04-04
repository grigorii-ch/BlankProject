package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {

//        System.out.println("Пример проверки класса Cache");
        Cache<String> cacheString = new Cache<>(5);
        try {
            cacheString.add("Test1", 1);
        } catch (CheckedNoSuchElementException e) {
            log.info(String.format("Ошибка при заполнении обьекта Cache: %n%s", e.printFullStackTrace()));
        }

        try {
            cacheString.add(null, 2);
        } catch (CheckedNoSuchElementException e) {
            log.info(String.format("Ошибка при заполнении обьекта Cache: %n%s", e.printFullStackTrace()));
        }

        try {
            cacheString.add("Test3", 3);
        } catch (CheckedNoSuchElementException e) {
            log.info(String.format("Ошибка при заполнении обьекта Cache: %n%s", e.printFullStackTrace()));
        }

        try {
            cacheString.add("Test4", 4);
        } catch (CheckedNoSuchElementException e) {
            log.info(String.format("Ошибка при заполнении обьекта Cache: %n%s", e.printFullStackTrace()));
        }

        try {
            cacheString.add("Test5", 5);
        } catch (CheckedNoSuchElementException e) {
            log.info(String.format("Ошибка при заполнении обьекта Cache: %n%s", e.printFullStackTrace()));
        }

//        System.out.println("Добавили 5 элементов");
//        System.out.println(cacheString.toString());

//        System.out.println("Получаем 3 элемент по index");
//        System.out.println(cacheString.get(3));

//        System.out.println("Проверить Test1");
//        System.out.println(cacheString.isPresent("Test1"));
//        System.out.println("Проверить Test100");
//        System.out.println(cacheString.isPresent("Test100"));

//        System.out.println("Удалить Test1");
        cacheString.delete("Test1");
//        System.out.println(cacheString.toString());

//        System.out.println("Очистить");
        cacheString.clear();
//        System.out.println(cacheString.toString());


//        System.out.println("Пример проверки класса Storage");
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", "Test5"});
//        System.out.println("Создание через конструктор с 5 элементами");
        System.out.println(storage.toString());

        String[] storageElements = new String[]{"TRATATA0","TRATATA1","TRATATA2","TRATATA3","TRATATA4",
                "TRATATA5","TRATATA6","TRATATA7",null,"TRATATA9","TRATATA10"};

        for (String elem : storageElements) {
            try {
                storage.add(elem);
            } catch (UncheckedNullPointerException e) {
                log.trace(String.format("Ошибка: при добавлении элемента %s%n%s", elem,  e.printFullStackTrace()));

                continue;
            }

        }


//        System.out.println("Добавление 10 новых элементов");
//        System.out.println(storage.toString());
//
//        System.out.println("Получить последний элемент");
//        System.out.println(storage.getLast());
//
//        System.out.println("Получание  3 элемента");
//        System.out.println(storage.get(3));
//        System.out.println(storage.toString());

        storage.delete();

//        System.out.println("Очистить");
        storage.clear();
//        System.out.println(storage.toString());
    }
}
