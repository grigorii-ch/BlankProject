package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.task1.TaskOne;
import com.chuprynin.epam.rd.task2.TaskTwo;
import com.chuprynin.epam.rd.task3.FileSystemProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        log.info("Старт программы");
        TaskOne taskOne = new TaskOne();
        log.info("Запуск таски 1 - обычный");
        List<String> taskOneResult = taskOne.run();
        log.debug(taskOneResult.toString());
        log.info("Запуск таски 1 - Стримы");
        List<String> taskOneResultStream = taskOne.runStream();
        log.debug(taskOneResultStream.toString());
        TaskTwo taskTwo = new TaskTwo();
        log.info("Запуск таски 2 - обычный");
        log.debug("Виды колбасы {}", taskTwo.run());
        log.info("Запуск таски 2 - Стримы");
        log.debug("Виды колбасы Stream {}", taskTwo.runStream());
        log.info("Запуск таски 3 - построение дерева");
        FileSystemProvider fileSystemProvider = new FileSystemProvider();
        fileSystemProvider.run("src/main",0);
        log.debug(fileSystemProvider.getTreePatch());
        log.info("Завершение программы");
    }
}


