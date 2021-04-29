package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.task1.TaskOne;
import com.chuprynin.epam.rd.task2.TaskTwo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Старт программы");
        Optional<ArrayList<String>> optional;
        TaskOne taskOne = new TaskOne();
        optional = (Optional<ArrayList<String>>) taskOne.run();
        if (optional.isPresent()) {
            ArrayList<String> taskOneResult = optional.get();
            log.debug(taskOneResult.toString());
        }
        optional = (Optional<ArrayList<String>>) taskOne.runStream();
        if (optional.isPresent()) {
            ArrayList<String> taskOneResultStream = optional.get();
            log.debug(taskOneResultStream.toString());
        }
        log.info("Запуск такси 2 - обычный");
        TaskTwo taskTwo = new TaskTwo();
        Optional optional2 = taskTwo.run();

        if (optional2.isPresent()) {
            log.debug("Виды колбасы {}", optional2.get());
        }
        log.info("Запуск такси 2 - Стримы");
        optional2 = taskTwo.runStream();

        if (optional2.isPresent()) {
            log.debug("Виды колбасы {}", optional2.get());
        }
        log.info("Завершение программы");
    }
}


