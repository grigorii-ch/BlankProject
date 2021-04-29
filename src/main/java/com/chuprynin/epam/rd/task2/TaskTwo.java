package com.chuprynin.epam.rd.task2;

import com.chuprynin.epam.rd.helpers.TasksHepler;
import com.chuprynin.epam.rd.task2.pojo.Sausage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс для формирования экземпляров класса Sausage на основе данных из файла
 */
@Slf4j
public class TaskTwo {

    private final static String PATCH_FILE = "src/main/resources/sausages.txt";
    private final String SAUSAGE_ERR_MESSAGE = "Не удалось загрузить данные из файла {}";

    TasksHepler hepler = new TasksHepler();

    /**
     * Запуск варианта реализации без стримов
     */
    public Optional<?> run() {
        Optional<?> optional = Optional.empty();
        try {
            optional = getListSausages();
        } catch (Exception e) {
            log.warn(SAUSAGE_ERR_MESSAGE, e.getMessage(), e);
        }
        return optional;
    }

    /**
     * Запуск варианта реализации со стримами
     * @return
     */
    public Optional<?> runStream() {
        Optional<?> optional = Optional.empty();
        try {
            optional = getListSausagesStream();
        } catch (Exception e) {
            log.warn(SAUSAGE_ERR_MESSAGE, e.getMessage(), e);
        }
        return optional;
    }

    /**
     * Метод для формировании и заполнению массива на основании данных из файла
     *
     * @return Optional<ArrayList < Sausage>> объект Optional с набором созданных объектов
     * @throws IOException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private Optional<ArrayList<Sausage>> getListSausages() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<String> stringList = hepler.readFromFile(TaskTwo.PATCH_FILE);
        Optional<?> optional = hepler.getNewArrayList();
        if (optional.isEmpty()) {
            throw new RuntimeException(TasksHepler.ERR_NEW_COLLECTION);
        }
        ArrayList<Sausage> sausages = (ArrayList<Sausage>) optional.get();

        for (String s : stringList) {
            try {
                sausages.add(hepler.convertToPojo(s));
            } catch (Exception e) {
                log.warn("Ошибка при создании Sausage Pojo {}", e.getMessage(), e);
            }
        }
        return Optional.of(sausages);
    }

    /**
     * Метод для формировании и заполнению массива на основании данных из файла
     *
     * @return Optional<ArrayList < Sausage>> объект Optional с набором созданных объектов
     * @throws IOException
     * @throws ArrayIndexOutOfBoundsException
     */
    private Optional<List<Sausage>> getListSausagesStream() throws IOException, ArrayIndexOutOfBoundsException {
        List<String> stringList = hepler.readFromFile(TaskTwo.PATCH_FILE);
        return Optional.of(stringList.stream().map(hepler::convertToPojo).collect(Collectors.toList()));
    }
}