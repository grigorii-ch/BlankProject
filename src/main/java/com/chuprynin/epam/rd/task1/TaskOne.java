package com.chuprynin.epam.rd.task1;

import com.chuprynin.epam.rd.helpers.TasksHepler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
     * @return Optional объект с листом значений внутри.
     */
    public Optional<?> run() {
        log.info("Запуск такси 1 - обычный");
        Optional<?> result = Optional.empty();
        try {
            List<String> stringListUUID = generateCollection();
            writeToFile((ArrayList<String>) stringListUUID);
            ArrayList<Integer> dataForCalculateEndDay = (ArrayList<Integer>) readAdnSumNumbers();
            result = Optional.of(endDayDate(dataForCalculateEndDay));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Запуск варианта реализации со стримами
     *
     * @return Optional объект с листом значений внутри.
     */
    public Optional<?> runStream() {
        log.info("Запуск такси 1 - Стримы");
        Optional<?> result = Optional.empty();
        try {
            Optional<?> optional = generateCollectionStream();
            if (!optional.isPresent()) {
                throw new RuntimeException(TasksHepler.ERR_NEW_COLLECTION);
            }
            List<String> stringListUUID = (List<String>) optional.get();
            writeToFileStream((ArrayList<String>) stringListUUID);
            ArrayList<Integer> dataForCalculateEndDay = (ArrayList<Integer>) readAdnSumNumbersStream();
            result = Optional.of(endDayDateStream(dataForCalculateEndDay));
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод создания коллекции с размером 10000
     * заполнение - случайная генерация UUID
     *
     * @return List<String> со значениями UUID
     */
    private List<String> generateCollection() {
        List<String> listUUIDs = null;
        try {
            Optional<?> optional = hepler.getNewArrayList();
            if (optional.isPresent()) {
                listUUIDs = (List<String>) optional.get();
            } else {
                throw new RuntimeException(TasksHepler.ERR_NEW_COLLECTION);
            }
            for (int i = 0; i < TaskOne.LIST_UUID_SIZE; i++) {
                listUUIDs.add(UUID.randomUUID().toString());
            }
        } catch (Exception e) {
            log.warn("Ошибка: {}", e.getMessage(), e);
        }
        return listUUIDs;
    }

    /**
     * Метод создания коллекции с размером 10000
     * заполнение - случайная генерация UUID
     * реализация через стримы
     *
     * @return Optional объект с List<String> со значениями UUID внутри.
     */
    private Optional<List<String>> generateCollectionStream() {
        log.debug("Заполнение коллекции");
        return Optional.of(Stream.generate(UUID::randomUUID)
                .map(UUID::toString)
                .limit(TaskOne.LIST_UUID_SIZE)
                .collect(Collectors.toList()));
    }

    /**
     * Метод для записи в файл
     *
     * @param strings - ArrayList<String> с данными для записи
     */
    private void writeToFile(ArrayList<String> strings) {
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
     * @param strings - ArrayList<String> с данными для записи
     */
    private void writeToFileStream(ArrayList<String> strings) throws IOException {
        log.info("Запись в файл {}", TaskOne.FILE_NAME_STREAM);
        Files.write(Path.of(TaskOne.FILE_NAME_STREAM), strings);
    }

    /**
     * Считывание данных из файлы, получение цифровых значений из UUID и суммировании
     *
     * @return List результатов суммирования
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private List<Integer> readAdnSumNumbers() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Optional<?> optional = hepler.getNewArrayList();
        if (optional.isEmpty()) {
            throw new RuntimeException(TasksHepler.ERR_NEW_COLLECTION);
        }
        ArrayList<Integer> arrayList = (ArrayList<Integer>) optional.get();
        try {
            List<String> strings = hepler.readFromFile(TaskOne.FILE_NAME);

            for (String s : strings) {
                int elementSum = hepler.getSumfromUUID(s);
                if (elementSum > 100) {
                    arrayList.add(elementSum);
                    log.trace("Результат суммирования {}", elementSum);
                }
            }
        } catch (IOException e) {
            log.warn("Ошибка: {}", e.getMessage(), e);
        }
        return arrayList;
    }

    /**
     * Считывание данных из файлы, получение цифровых значений из UUID и суммировании
     * с помощью стримов
     *
     * @return List результатов суммирования
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
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
     * @param dataDayMonth ArrayList<Integer> данные для вычислений
     * @return ArrayList<String> с результатами вычислений
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private ArrayList<String> endDayDate(ArrayList<Integer> dataDayMonth) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Optional<?> optional = hepler.getNewArrayList();
        if (optional.isEmpty()) {
            throw new RuntimeException(TasksHepler.ERR_NEW_COLLECTION);
        }
        log.debug("Вычисление даты конца света");
        ArrayList<String> endDates = (ArrayList<String>) optional.get();
        for (int i : dataDayMonth) {
            String data = String.valueOf(i);
            LocalDateTime localDate = LocalDateTime.now()
                    .plusMonths(Long.parseLong(data.substring(0, 1)))
                    .plusDays(Long.parseLong(data.substring(1, 2)));
            endDates.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDate));
            log.trace("Дата после вычислений {}", localDate);
        }
        return endDates;
    }

    /**
     * Вычисление дат конца света
     *
     * @param dataDayMonth ArrayList<Integer> данные для вычислений
     * @return ArrayList<String> с результатами вычислений
     */
    private List<String> endDayDateStream(ArrayList<Integer> dataDayMonth) {
        log.debug("Вычистание даты конца света");
        return dataDayMonth.stream()
                .map(e -> {
                    String str = String.valueOf(e);
                    LocalDateTime localDate = LocalDateTime.now()
                            .plusMonths(Long.parseLong(str.substring(0, 1)))
                            .plusDays(Long.parseLong(str.substring(1, 2)));
                    log.trace("Дата после вычислений {}", localDate);
                    return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDate);
                })
                .collect(Collectors.toList());
    }
}