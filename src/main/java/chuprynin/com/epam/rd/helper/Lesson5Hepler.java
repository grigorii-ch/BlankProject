package chuprynin.com.epam.rd.helper;

import chuprynin.com.epam.rd.emums.Commands;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс хелпер для обших методов.
 */
@Slf4j
public class Lesson5Hepler {

    /**
     * Конструктор
     */
    public Lesson5Hepler() {
    }

    /**
     * Метод для изменения ArrayList<String> при добавлении в середину файлв
     * @param strings
     * @param lineNumber
     * @param text
     * @param operation
     * @return
     */
    public ArrayList<String> changeArray(ArrayList<String> strings, int lineNumber, String text, String operation) {
        log.debug("ArrayList<String> strings {}, int lineNumber {}, String text {}, String operation {}",
                strings.toString(), lineNumber, text,operation);

        ArrayList<String> changetArray = new ArrayList<>();

        if (Commands.ADD.getValue().equals(operation) && lineNumber > strings.size() - 1) {
            changetArray.addAll(strings);

            for (int i = 0; i < lineNumber - strings.size() - 1; i++) {
                changetArray.add("");
            }
            changetArray.add(text);

            log.debug("changetArray {}", changetArray.toString());
            return changetArray;
        }

        if (Commands.ADD.getValue().equals(operation)) {
            changetArray.addAll(strings.subList(0, lineNumber - 1));
            changetArray.add(text);
            changetArray.addAll(strings.subList(lineNumber - 1, strings.size()));

            log.debug("changetArray {}", changetArray.toString());
            return changetArray;
        }

        changetArray.addAll(strings);
        changetArray.remove(lineNumber - 1);

        log.debug("changetArray {}", changetArray.toString());
        return changetArray;
    }

    /**
     * Запись в файл
     * @param fileName
     * @param lineNumber
     * @param text
     * @param operation
     */
    public void writeToFile(String fileName, int lineNumber, String text, String operation) {
        log.debug("String fileName {}, int lineNumber {}, String text {}, String operation {}",
                fileName, lineNumber, text,operation);

        ArrayList<String> strings = readFile(fileName);

        if (operation.equals(Commands.ADD.getValue())) {
            strings = prepareStringsAddOpperation(lineNumber, text, operation, strings);
        }

        if (operation.equals(Commands.DELETE.getValue())) {
            prepareStringsDelleteOpperation(lineNumber, strings);
        }

        write(fileName, strings);
    }

    /**
     * Формирование текста с учетом удаляемой строки
     * @param lineNumber
     * @param strings
     */
    private void prepareStringsDelleteOpperation(int lineNumber, ArrayList<String> strings) {
        if (lineNumber == -1 || lineNumber >= strings.size() - 1) {
            try {
                strings.remove(strings.size() - 1);

            } catch (ArrayIndexOutOfBoundsException e) {
                log.warn("ArrayIndexOutOfBoundsException: {}", e.getMessage());
            }
        } else {
            strings.remove(lineNumber - 1);
        }
    }

    /**
     * Формирование текста с определение позици добовляемой строки
     * @param lineNumber
     * @param text
     * @param operation
     * @param strings
     * @return
     */
    private ArrayList<String> prepareStringsAddOpperation(int lineNumber, String text, String operation, ArrayList<String> strings) {
        if (lineNumber == -1) {
            strings.add(text);
        } else {
            strings = changeArray(strings, lineNumber, text, operation);
        }
        return strings;
    }

    /**
     * Записываем в файл
     * @param fileName
     * @param strings
     */
    private void write(String fileName, ArrayList<String> strings) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            log.debug("Запись в файл {}", fileName);

            for (int i = 0; i < strings.size(); i++) {
                bw.write(strings.get(i));

                if (i != strings.size() - 1) {
                    bw.newLine();
                }
            }
            bw.flush();

        } catch (IOException e) {
            log.warn("Ошибка IOException {}", e.getMessage());
        }
    }

    /**
     * Считывание файла
     * @param fileName
     * @return
     */
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> strings = new ArrayList<>();
        BufferedReader br;

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println(String.format("Файл %s не найден", fileName));
                return strings;
            }

            System.out.println("Создаем файл.");
            br = new BufferedReader(new FileReader(file));

            String s;
            while ((s = br.readLine()) != null) {
                strings.add(s);
            }
            br.close();

        } catch (IOException e) {
            log.warn("Ошибка IOException {}", e.getMessage());
        } catch (NullPointerException e) {
            log.warn("Ошибка NullPointerException {}", e.getMessage());
        }
        return strings;
    }

    /**
     * Чистит пробелы в введеной команде
     * @param arrayList
     * @return
     */
    public ArrayList<String> removeSpaces(ArrayList<String> arrayList) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {
            if (!"".equals(arrayList.get(i))) {
                list.add(arrayList.get(i));
            }
        }

        return list;
    }

    /**
     * Проверка на число.
     * @param s
     * @return
     */
    public boolean isDigit(String s) {
        return Character.isDigit(s.toCharArray()[0]);
    }

    /**
     * Проверка введенной команды на минимальное кол-во переменных
     * @param list
     * @throws RuntimeException
     */
    public void validate(ArrayList<String> list) throws RuntimeException {
        if (list.size() < 2) {
            log.warn("Команда add имеет не верный формат {}", list.toString());
            throw new RuntimeException("Команда add имеет не верный формат");
        }
    }
}
