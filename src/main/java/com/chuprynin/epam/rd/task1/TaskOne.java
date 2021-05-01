package com.chuprynin.epam.rd.task1;

import com.chuprynin.epam.rd.helpers.TasksHepler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
/**
 * Класс для получения массива дат конца света, на основе данных UUID
 */
public class TaskOne {
    private static final int LIST_UUID_SIZE = 10000;
    private static final String FILE_NAME = "task1_1.txt";
    private static final String FILE_NAME_STREAM = "task1_2.txt";

    TasksHepler hepler = new TasksHepler();

    /**
     * Запуск варианта реализации без стримов
     *
     * @return List<String>
     */
    public List<String> run() throws IOException {
        log.info("Запуск таски 1 - обычный");
        writeToFile(generateCollection());
        List<Integer> dataForCalculateEndDay = readAdnSumNumbers();
        return endDayDate(dataForCalculateEndDay);
    }

    /**
     * Запуск варианта реализации со стримами
     *
     * @return List<String> значений.
     */
    public List<String> runStream() throws IOException {
        log.info("Запуск таски 1 - Стримы");
        List<String> stringListUUID = generateCollectionStream();
        writeToFileStream(stringListUUID);
        List<Integer> dataForCalculateEndDay = readAdnSumNumbersStream();
        return endDayDateStream(dataForCalculateEndDay);
    }

    /**
     * Метод создания коллекции с размером 10000
     * заполнение - случайная генерация UUID
     *
     * @return List<String> со значениями UUID
     */
    private List<String> generateCollection() {
        List<String> listUUIDs = hepler.getNewList();
        for (int i = 0; i < TaskOne.LIST_UUID_SIZE; i++) {
            listUUIDs.add(UUID.randomUUID().toString());
        }
        return listUUIDs;
    }

    /**
     * Метод создания коллекции с размером 10000
     * заполнение - случайная генерация UUID
     * реализация через стримы
     *
     * @return List<String> со значениями UUID внутри.
     */
    private List<String> generateCollectionStream() {
        log.debug("Заполнение коллекции");
        return Stream.generate(UUID::randomUUID)
                .map(UUID::toString)
                .limit(TaskOne.LIST_UUID_SIZE)
                .collect(Collectors.toList());
    }

    /**
     * Метод для записи в файл
     *
     * @param strings - List<String> с данными для записи
     */
    private void writeToFile(List<String> strings) {
        log.info("Запись в файл {}", TaskOne.FILE_NAME);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TaskOne.FILE_NAME))) {
            for (String s : strings) {
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            log.warn("Ошибка: {}", e.getMessage(), e);
        }
    }

    /**
     * Метод для записи в файл, с помощью стримов
     *
     * @param strings - List<String> с данными для записи
     */
    private void writeToFileStream(List<String> strings) throws IOException {
        log.info("Запись в файл {}", TaskOne.FILE_NAME_STREAM);
        Files.write(Path.of(TaskOne.FILE_NAME_STREAM), strings);
    }

    /**
     * Считывание данных из файлы, получение цифровых значений из UUID и суммировании
     *
     * @return List результатов суммирования
     */
    private List<Integer> readAdnSumNumbers() throws IOException {
        List<Integer> newList = hepler.getNewList();
        List<String> strings = hepler.readFromFile(TaskOne.FILE_NAME);
        for (String s : strings) {
            int elementSum = hepler.getSumfromUUID(s);
            if (elementSum > 100) {
                newList.add(elementSum);
                log.trace("Результат суммирования {}", elementSum);
            }
        }
        return newList;
    }

    /**
     * Считывание данных из файлы, получение цифровых значений из UUID и суммировании
     * с помощью стримов
     *
     * @return List результатов суммирования
     */
    private List<Integer> readAdnSumNumbersStream() throws IOException {
        List<String> strings = hepler.readFromFile(TaskOne.FILE_NAME_STREAM);
        return strings.stream()
                .map(e -> {
                    String[] s = e.replaceAll("[A-z-0]", "").split("");
                    int cnt = 0;
                    for (String str : s) {
                        cnt += Integer.parseInt(str);
                        log.trace("Результат суммирования {}", cnt);
                    }
                    return cnt;
                })
                .filter(e -> e > 100)
                .collect(Collectors.toList());
    }

    /**
     * Вычисление дат конца света
     *
     * @param dataDayMonth List<Integer> данные для вычислений
     * @return List<String> с результатами вычислений
     */
    private List<String> endDayDate(List<Integer> dataDayMonth) {
        log.debug("Вычисление даты конца света");
        List<String> endDates = hepler.getNewList();
        for (int i : dataDayMonth) {
            LocalDateTime localDate = getDateTime(String.valueOf(i));
            endDates.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDate));
            log.trace("Дата после вычислений {}", localDate);
        }
        return endDates;
    }

    /**
     * Вычисление дат конца света
     *
     * @param dataDayMonth List<Integer> данные для вычислений
     * @return List<String> с результатами вычислений
     */
    private List<String> endDayDateStream(List<Integer> dataDayMonth) {
        log.debug("Вычистание даты конца света");
        return dataDayMonth.stream()
                .map(e -> {
                    LocalDateTime localDate = getDateTime(String.valueOf(e));
                    log.trace("Дата после вычислений {}", localDate);
                    return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDate);
                })
                .collect(Collectors.toList());
    }

    /**
     * Метод для парсинга даных и преврашения в формат даты
     *
     * @param s Строка с данными
     * @return дата, с корректировками входных данных
     */
    private LocalDateTime getDateTime(String s) {
        String str = s;
        return LocalDateTime.now()
                .plusMonths(Long.parseLong(str.substring(0, 1)))
                .plusDays(Long.parseLong(str.substring(1, 2)));
    }
}