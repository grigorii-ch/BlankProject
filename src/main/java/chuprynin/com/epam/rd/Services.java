package chuprynin.com.epam.rd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Services{
    public void ArrayService() {
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

        System.out.println("Выводим список людей \n");
        for (Human human : humanList) {
            System.out.println(human.toString());
        }

        System.out.println("\nВыводим дубли и даляем \n");
        for (int i = 0; i < humanList.size(); i++) {
            if (humanList.subList(i + 1, humanList.size()).contains(humanList.get(i))) {
                System.out.println(humanList.get(i));
                humanList.remove(i);
            }
        }

        System.out.println("\nCписок людей без дублей \n");
        for (Human human : humanList) {
            System.out.println(human.toString());
        }

        Comparator<Human> comparatorFio = Comparator.comparing(Human::getFio);
        System.out.println("\nCписок людей с сортиповкой по ФИО \n");
        Collections.sort(humanList, comparatorFio);
        for (Human human : humanList) {
            System.out.println(human.toString());
        }

        Comparator<Human> comparatorAge = Comparator.comparing(Human::getAge);
        System.out.println("\nCписок людей с сортиповкой по Возрасту \n");
        Collections.sort(humanList, comparatorAge);
        for (Human human : humanList) {
            System.out.println(human.toString());
        }

    }
}
