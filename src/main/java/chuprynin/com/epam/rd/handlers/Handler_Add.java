package chuprynin.com.epam.rd.handlers;

import chuprynin.com.epam.rd.helper.Lesson5Hepler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Пример: add 5 fileName "text"
 * - add команда на добавление текста.
 * 5 строка в файле (если в файле к примеру всего 3 строки, то необходимо будет добавить 2 строки).
 * Так же строка которая раньше находилась под номером 5 должна стать номером 6 и т.д., короче все сдвигается вниз на одну строку
 * fileName имя файла для редактирования
 * "text" собственно текст который мы хотим добавить. Текст указывается в двойных кавычках.
 * ВНИМАНИЕ, если мы не указываем номер строки, а к примеру даем команду add "text", то мы добавляем просто новую строку с текстом.
 */

@Slf4j
public class Handler_Add {
    private ArrayList<String> addCommandList;

    private String fileName;
    private int lineNumber = -1;
    private String text;

    private Lesson5Hepler hepler;

    /**
     * Конструктор
     * @param addCommandList
     */
    public Handler_Add(ArrayList<String> addCommandList) {
        hepler = new Lesson5Hepler();
        this.addCommandList = addCommandList;
    }

    /**
     * Сохранение данных
     */
    public void save() {
        hepler.writeToFile(fileName, lineNumber, text, addCommandList.get(0));
    }

    /**
     * Проверка добавляемого текста
     * @throws RuntimeException
     */
    private void validate() throws RuntimeException {
        if (!(text.indexOf("\"") == 0) || !(text.lastIndexOf("\"") == text.length() - 1)) {
            // log
            throw new RuntimeException("Команда add имеет не верный формат: текст - введент без ковычек");
        }
    }

    /**
     * Парсинг команды
     * @return
     */
    public boolean parseCommand() {
        try {
            if (hepler.isDigit(addCommandList.get(1))) {
                lineNumber = Integer.valueOf(addCommandList.get(1));
                fileName = addCommandList.get(2);
                text = addCommandList.get(3);

                validate();

                log.debug("lineNumber = {}, fileName = {}, text = {}",lineNumber ,fileName, text);
                return true;
            }
                fileName = addCommandList.get(1);
                text = addCommandList.get(2);

                validate();

                log.debug("lineNumber = {}, fileName = {}, text = {}",lineNumber ,fileName, text);
                return true;

        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка ArrayIndexOutOfBoundsException {}", e.getMessage());
            return false;
        } catch (RuntimeException e) {
            log.warn("Ошибка RuntimeException {}", e.getMessage());
            return false;
        }
    }
}
