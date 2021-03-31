package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.human.HumanService;
import chuprynin.com.epam.rd.map.MapService;
import chuprynin.com.epam.rd.user.UserService;

public class Main {
    public static void main(String[] args) {
        HumanService humanService = new HumanService();
        humanService.service();

        UserService userService = new UserService();
        userService.service();

        MapService mapService = new MapService();
        mapService.service();
    }
}
