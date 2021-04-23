package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.pojo.Building;
import com.chuprynin.epam.rd.pojo.Human;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {
        logger.info("Начало программы");
        Process process = new Process();
        try {
            Human human = new Human();
            human = (Human) process.run(human, human.getClass());
            logger.debug(human.toString());

            Building building = new Building();
            building = (Building) process.run(building, building.getClass());
            logger.debug(building.toString());
        } catch (Exception e) {
            logger.warn("Ошибка: {}", e.getMessage(), e);
        }
        logger.info("Завершение программы");
    }
}
