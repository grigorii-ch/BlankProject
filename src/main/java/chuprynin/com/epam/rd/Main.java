package chuprynin.com.epam.rd;

public class Main {
    public static void main(String[] args) {
        System.out.println("Пример проверки класса Cache");
        Cache<String> cacheString = new Cache<>(5);
        cacheString.add("Test1", 1);
        cacheString.add("Test2", 2);
        cacheString.add("Test3", 3);
        cacheString.add("Test4", 4);
        cacheString.add("Test5", 5);
        System.out.println("Добавили 5 элементов");
        System.out.println(cacheString.toString());

        System.out.println("Получаем 3 элемент по index");
        System.out.println(cacheString.get(3));

        System.out.println("Проверить Test1");
        System.out.println(cacheString.isPresent("Test1"));
        System.out.println("Проверить Test100");
        System.out.println(cacheString.isPresent("Test100"));

        System.out.println("Удалить Test1");
        cacheString.delete("Test1");
        System.out.println(cacheString.toString());

        System.out.println("Очистить");
        cacheString.clear();
        System.out.println(cacheString.toString());


        System.out.println("Пример проверки класса Storage");
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", "Test5"});
        System.out.println("Создание через конструктор с 5 элементами");
        System.out.println(storage.toString());

        storage.add("TRATATA0");
        storage.add("TRATATA1");
        storage.add("TRATATA2");
        storage.add("TRATATA3");
        storage.add("TRATATA4");
        storage.add("TRATATA5");
        storage.add("TRATATA6");
        storage.add("TRATATA7");
        storage.add("TRATATA8");
        storage.add("TRATATA9");
        storage.add("TRATATA10");
        System.out.println("Добавление 10 новых элементов");
        System.out.println(storage.toString());

        System.out.println("Получить последний элемент");
        System.out.println(storage.getLast());

        System.out.println("Добавление 3 элементов");
        System.out.println(storage.get(3));
        System.out.println(storage.toString());

        System.out.println("Очистить");
        storage.clear();
        System.out.println(storage.toString());
    }
}
