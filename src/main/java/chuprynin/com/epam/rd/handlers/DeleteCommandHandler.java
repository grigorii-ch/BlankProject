package chuprynin.com.epam.rd.handlers;

import chuprynin.com.epam.rd.emums.Commands;
import chuprynin.com.epam.rd.helper.Lesson5Hepler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Пример: delete 5 fileName
 * - delete команда на удаление строки
 * 5 номер строки которую нам необходимо удалить.
 * ВНИМАНИЕ, если мы не указываем номер строки, а к примеру даем команду delete, то мы удаляем последнюю строку из файла.
 */
@Slf4j
public class DeleteCommandHandler implements CommandHandler {
    /**
     * Конструктор
     */
    public DeleteCommandHandler() {
    }

    /**
     * Метод для передачи данных на сохранение
     */
    private void delete(Lesson5Hepler hepler, String fileName, int lineNumber) {
        hepler.writeToFile(fileName, lineNumber, null, Commands.DELETE.getValue());
    }

    /**
     * Парсинг введенной команды
     * @return
     */

    @Override
    public void handle(ArrayList<String> delArray) {
        Lesson5Hepler hepler = new Lesson5Hepler();
        int lineNumber = -1;
        String fileName = delArray.get(1);
        String text = delArray.get(2);

        if (hepler.isDigit(delArray.get(1))) {
            lineNumber = Integer.valueOf(delArray.get(1));
            fileName = delArray.get(2);
            text = delArray.get(3);
        }

        log.debug("Команда {}", delArray.get(0));
        log.debug("lineNumber = {}, fileName = {}, text = {}", lineNumber, fileName, text);

        delete(hepler,fileName,lineNumber);
        System.out.println(" - Опперация delete выполнена");
        return;
    }

}
