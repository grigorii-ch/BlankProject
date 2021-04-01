package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.human.Human;
import chuprynin.com.epam.rd.human.HumanService;
import chuprynin.com.epam.rd.map.MapService;
import chuprynin.com.epam.rd.user.User;
import chuprynin.com.epam.rd.user.UserService;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HumanService humanService = new HumanService();
        ArrayList<Human> humanList = humanService.getFillHumanList();
        System.out.println("Поиск дублей");
        humanService.findDubles(humanList);

        System.out.println("Удаление дублей");
        humanList = humanService.removeDubles(humanList);
        System.out.println(humanList);

        System.out.println("Сортировка по ФИО");
        humanService.sortByFio(humanList);

        System.out.println("Сортировка по возрасту");
        humanService.sortByAge(humanList);

        System.out.println("Сортировка адресу");
        humanService.sortByAddress(humanList);

        System.out.println();

        UserService userService = new UserService();
        ArrayList<User> userList = userService.getFillUserList();
        userService.printHelloUsers(userList);

        System.out.println();

        MapService mapService = new MapService();
        System.out.println("Дефолтная сортировка");
        HashMap map = mapService.getFillMap();
        System.out.println(map);

        System.out.println("Сортировка по ключу");
        mapService.sortByKey(map);

        System.out.println("Сортировка по значению");
        mapService.sortByValue(map);
    }
}
