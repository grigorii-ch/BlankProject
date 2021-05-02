package com.chuprynin.epam.rd.helpers;

import com.chuprynin.epam.rd.task2.pojo.Sausage;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
/**
 * Класс помощник, для хранения общих методов Task1, Task2
 */
public class TasksHepler {

    /**
     * Метод для считывания данных из файла
     *
     * @param fileName - имя файла
     * @return - результат
     * @throws IOException - ошибки доступа
     */
    public List<String> readFromFile(String fileName) throws IOException {
        log.info("Считывание из файла {}", fileName);
        return Files.lines(Paths.get(fileName))
                .collect(Collectors.toList());
    }

    /**
     * Метод для подсчёта суммы из строки типа UUID, не числовые данные в строке будут удалены
     *
     * @param uuidData - строка типа UUID
     * @return - результат сложения
     */
    public Integer getSumfromUUID(String uuidData) {
        int summ = 0;
        String[] clearData = uuidData.replaceAll("[A-z-0]", "").split("");
        for (String s : clearData) {
            summ += Integer.parseInt(s);
        }
        return summ;
    }

    /**
     * Метод для парсинга строки с описанием и создания на ее основе экземпляра класса Sausage
     *
     * @param parseData - строка для парсинга
     * @return экземпляр класса Sausage
     * @throws ArrayIndexOutOfBoundsException
     */
    public Sausage convertToPojo(String parseData) throws ArrayIndexOutOfBoundsException {
        String decodeData = new String(Base64.getDecoder().decode(parseData));
        String[] parsedData = decodeData.replaceAll(" ", "").split(",");
        String type = parsedData[0].replaceAll("[type=']", "");
        String weight = parsedData[1].replaceAll("[weight=]", "");
        String cost = parsedData[2].replaceAll("[cost=]", "");
        return new Sausage(type, Integer.parseInt(weight), Long.parseLong(cost));
    }

    /**
     * Метод для создания экземпляра List через рефлексию
     *
     * @return Объект List
     */
    public List getNewList() {
        log.info("Создана новая коллекция");
        return Stream.empty()
                .collect(Collectors.toList());
    }
}