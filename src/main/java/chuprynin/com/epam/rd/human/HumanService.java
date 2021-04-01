package chuprynin.com.epam.rd.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Класс для работы сущностью Human
 */
public class HumanService {
    /**
     * Возвращает ArrayList с 10 элементами класс Human
     *
     * @return ArrayList<Human>
     */
    public ArrayList<Human> getFillHumanList() {
        ArrayList<Human> humanList = new ArrayList<>();

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

        return humanList;
    }

    /**
     * Найти дубли в коллекции и вывести их в консоль.
     *
     * @param humanList
     */
    public void findDubles(ArrayList<Human> humanList) {
        for (int i = 0; i < humanList.size(); i++) {
            if (i != humanList.size() - 1 &&
                    humanList.subList(i + 1, humanList.size()).contains(humanList.get(i))) {
                System.out.println(humanList.get(i));
            }
        }
    }

    /**
     * Удалить дубли из коллекции
     *
     * @param humanList
     */
    public ArrayList<Human> removeDubles(ArrayList<Human> humanList) {
        for (int i = 0; i < humanList.size(); i++) {
            if (i != humanList.size() - 1 &&
                    humanList.subList(i + 1, humanList.size()).contains(humanList.get(i))) {
                humanList.remove(i);
            }
        }
        return humanList;
    }

    /**
     * Отсортировать людей по ФИО
     *
     * @param humanList
     */
    public void sortByFio(ArrayList<Human> humanList) {
        Collections.sort(humanList, Comparator.comparing(obj -> obj.getFio()));
        print(humanList);
    }

    /**
     * Отсортировать людей по возрасту
     *
     * @param humanList
     */
    public void sortByAge(ArrayList<Human> humanList) {
        Collections.sort(humanList, Comparator.comparing(Human::getAge));
        print(humanList);
    }

    /**
     * Отсортировать людей по адресу (лексикографическая сортировка полного адреса)
     *
     * @param humanList
     */
    public void sortByAddress(ArrayList<Human> humanList) {
        Collections.sort(humanList, Comparator.comparing(obj -> obj.getAddress().toString()));
        print(humanList);
    }

    /**
     * Печатаем ArrayList
     *
     * @param humanList
     */
    private void print(ArrayList<Human> humanList) {
        for (Human human : humanList) {
            System.out.println(human.toString());
        }
    }
}
