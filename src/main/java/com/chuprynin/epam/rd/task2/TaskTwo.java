package com.chuprynin.epam.rd.task2;

import com.chuprynin.epam.rd.helpers.TasksHepler;
import com.chuprynin.epam.rd.task2.pojo.Sausage;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для формирования экземпляров класса Sausage на основе данных из файла
 */
@Slf4j
public class TaskTwo {

    private final static String PATCH_FILE = "src/main/resources/File.txt";

    TasksHepler hepler = new TasksHepler();

    /**
     * Запуск варианта реализации без стримов
     * @return
     */
    public List<Sausage> run() throws IOException {
        return getListSausages();
    }

    /**
     * Запуск варианта реализации со стримами
     * @return
     */
    public List<Sausage> runStream() throws IOException {
        return getListSausagesStream();
    }

    /**
     * Метод для формировании и заполнению массива на основании данных из файла
     *
     * @return List<Sausage> с набором созданных объектов
     * @throws IOException
     */
    private List<Sausage> getListSausages() throws IOException {
        List<String> stringList = hepler.readFromFile(TaskTwo.PATCH_FILE);
        List<Sausage> sausages = hepler.getNewList();
        for (String s : stringList) {
            try {
                sausages.add(hepler.convertToPojo(s));
            } catch (Exception e) {
                log.warn("Ошибка при создании Sausage Pojo {}", e.getMessage(), e);
            }
        }
        return sausages;
    }

    /**
     * Метод для формировании и заполнению массива на основании данных из файла
     *
     * @return List<Sausage> с набором созданных объектов
     * @throws IOException
     * @throws ArrayIndexOutOfBoundsException
     */
    private List<Sausage> getListSausagesStream() throws IOException, ArrayIndexOutOfBoundsException {
        List<String> stringList = hepler.readFromFile(TaskTwo.PATCH_FILE);
        return stringList.stream()
                .map(hepler::convertToPojo)
                .collect(Collectors.toList());
    }
}