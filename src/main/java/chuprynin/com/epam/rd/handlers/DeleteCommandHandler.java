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

    private String fileName;
    private int lineNumber = -1;

    private Lesson5Hepler hepler;

    /**
     * Конструктор
     */
    public DeleteCommandHandler() {
    }

    /**
     * Метод для передачи данных на сохранение
     */
    private void delete() {
        hepler.writeToFile(fileName, lineNumber, null, Commands.DELETE.getValue());
    }

    /**
     * Парсинг введенной команды
     * @return
     */
    private boolean parseCommand(ArrayList<String> dellCommandList) {
        try {
            hepler.validate(dellCommandList);

            if (hepler.isDigit(dellCommandList.get(1))) {
                lineNumber = Integer.valueOf(dellCommandList.get(1));
                fileName = dellCommandList.get(2);

                log.debug("lineNumber = {}, fileName = {}", lineNumber, fileName);
                return true;
            }
            fileName = dellCommandList.get(1);

            log.debug("lineNumber = {}, fileName = {}", lineNumber, fileName);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка ArrayIndexOutOfBoundsException {}", e.getMessage());
            return false;
        } catch (RuntimeException e) {
            log.warn("Ошибка RuntimeException {}", e.getMessage());
            return false;
        }
    }

    @Override
    public void handle(ArrayList<String> delArray) {
        hepler = new Lesson5Hepler();
        log.debug("Команда {}", Commands.DELETE.getValue());
        if (parseCommand(delArray)) {
            delete();
            System.out.println(" - Опперация delete выполнена");
            return;
        } else {
            System.out.println(" - Комманда имеет не верный формат");
            return;
        }
    }

    @Override
    public String toString() {
        return "DeleteCommandHandler{" +
                ", fileName='" + fileName + '\'' +
                ", lineNumber=" + lineNumber +
                ", hepler=" + hepler +
                '}';
    }
}
