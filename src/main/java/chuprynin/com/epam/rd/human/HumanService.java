package chuprynin.com.epam.rd.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Класс для работы сущностью Human
 */
public class HumanService {
    private ArrayList<Human> humanList = new ArrayList<>();

    /**
     * Конструктор класса с заполнением дефолтных значений массива
     */
    public HumanService() {
        humanList.add(new Human("Бот4", 104,
                new Address("Сервер4", "root/home4", 4, 4)));
        humanList.add(new Human("Иванов Иван Иванович", 32,
                new Address("Москва", "Пушкина", 1, 1)));
        humanList.add(new Human("Бот1", 101,
                new Address("Сервер1", "root/home1", 1, 1)));
        humanList.add(new Human("Иванов Иван Иванович", 32,
                new Address("Москва", "Пушкина", 1, 1)));
        humanList.add(new Human("Петров Петр Петрович", 33,
                new Address("Питер", "Ленина", 2, 3)));
        humanList.add(new Human("Бот2", 102,
                new Address("Сервер2", "root/home2", 2, 2)));
        humanList.add(new Human("Петров Петр Петрович", 33,
                new Address("Питер", "Ленина", 2, 3)));
        humanList.add(new Human("Сидоров Сидр Сидорович", 34,
                new Address("Самара", "Матроспва", 4, 5)));
        humanList.add(new Human("Бот3", 103,
                new Address("Сервер3", "root/home3", 3, 3)));
        humanList.add(new Human("Сидоров Сидр Сидорович", 34,
                new Address("Самара", "Матроспва", 4, 5)));
    }

    /**
     * Метод для демонстрации работы c сущностью Human
     */
    public void service() {
        print("Выводим заполненный список");

        System.out.println("\nВыводим дубли и удаляем");
        for (int i = 0; i < humanList.size(); i++) {
            if (i != humanList.size()-1 &&
                    humanList.subList(i + 1, humanList.size()).contains(humanList.get(i))) {
                System.out.println(humanList.get(i));
                humanList.remove(i);
            }
        }

        print("\nCписок людей без дублей");

        Collections.sort(humanList, Comparator.comparing(obj -> obj.getFio()));
        print("\nCписок людей с сортиповкой по ФИО");

        Collections.sort(humanList, Comparator.comparing(Human::getAge));
        print("\nCписок людей с сортиповкой по Возрасту");

        Collections.sort(humanList, Comparator.comparing(obj -> obj.getAddress().toString()));
        print("\nCписок людей с сортиповкой по Адресу");
    }

    /**
     * Вывод сообщения и массива
     *
     * @param title
     */
    private void print(String title) {
        System.out.println(title);
        for (Human human : humanList) {
            System.out.println(human.toString());
        }
    }
}
