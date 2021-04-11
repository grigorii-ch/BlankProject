package chuprynin.com.epam.rd.handlers;

import chuprynin.com.epam.rd.emums.Commands;
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
public class AddCommandHandler implements CommandHandler {
    /**
     * Конструктор
     */
    public AddCommandHandler(){
    }

    /**
     * Сохранение данных
     */
    private void save(Lesson5Hepler hepler, String fileName, int lineNumber, String text, String command) {
        hepler.writeToFile(fileName, lineNumber, text, command);
    }

    /**
     * Проверка добавляемого текста
     *
     * @throws RuntimeException
     */
    private void validate(String text) throws RuntimeException {
        if (!(text.indexOf("\"") == 0) || !(text.lastIndexOf("\"") == text.length() - 1)) {
            throw new RuntimeException("Команда add имеет не верный формат: текст - введент без ковычек");
        }
    }

    @Override
    public void handle(ArrayList<String> addCommandList) {
        Lesson5Hepler hepler = new Lesson5Hepler();
        int lineNumber = -1;
        String fileName = addCommandList.get(1);
        String text = addCommandList.get(2);

        if (hepler.isDigit(addCommandList.get(1))) {
            lineNumber = Integer.valueOf(addCommandList.get(1));
            fileName = addCommandList.get(2);
            text = addCommandList.get(3);
        }

        log.debug("Команда {}", addCommandList.get(0));
        log.debug("lineNumber = {}, fileName = {}, text = {}", lineNumber, fileName, text);

        try {
            validate(text);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка ArrayIndexOutOfBoundsException {}", e.getMessage());
        } catch (RuntimeException e) {
            log.warn("Ошибка RuntimeException {}", e.getMessage());
            return;
        }

        save(hepler, fileName, lineNumber, text, Commands.ADD.getValue());
        System.out.println(" - Опперация add выполнена");
    }
}
