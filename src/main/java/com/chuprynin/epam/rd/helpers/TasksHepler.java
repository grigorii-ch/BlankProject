package com.chuprynin.epam.rd.helpers;

import com.chuprynin.epam.rd.task2.pojo.Sausage;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
/**
 * Класс помощник, для хранения общих методов Task1, Task2
 */
public class TasksHepler {

    public final static String ERR_NEW_COLLECTION = "Ошибка создания коллекции";

    /**
     * Метод для считывания данных из файла
     * @param fileName - имя файла
     * @return - результат
     * @throws IOException - ошибки доступа
     */
    public List<String> readFromFile(String fileName) throws IOException {
        log.info("Считывание из файла {}", fileName);
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }

    /**
     * Метод для подсчёта суммы из строки типа UUID, не числовые данные в строке будут удалены
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
     * @param parseData - строка для парсинга
     * @return экземпляр класса Sausage
     * @throws ArrayIndexOutOfBoundsException
     */
    public Sausage convertToPojo(String parseData) throws ArrayIndexOutOfBoundsException {
        String[] parsedData = parseData.split(":");
        try {
            return new Sausage(parsedData[0], Integer.parseInt(parsedData[1]), Long.parseLong(parsedData[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Метод для создания экземпляра ArrayList через рефлексию
     * @return Объект Optional с ArrayList внутри
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public Optional<?> getNewArrayList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        log.info("Создана новая коллекция");
        return Optional.of(ArrayList.class.getDeclaredConstructor().newInstance());
    }
}