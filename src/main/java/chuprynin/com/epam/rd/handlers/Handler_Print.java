package chuprynin.com.epam.rd.handlers;

import chuprynin.com.epam.rd.helper.Lesson5Hepler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Пример: print 4 fileName
 * - print команда на печать
 * 4 это номер строки которую мы хотим распечатать.
 * ВНИМАНИЕ, если мы не указываем номер строки, а к примеру даем команду print, то мы печатаем весь файл.
 */

@Slf4j
public class Handler_Print {
    private ArrayList<String> printCommandList;

    private String fileName;
    private int lineNumber = -1;

    private Lesson5Hepler hepler;

    /**
     * Конструктор
     * @param printArray
     */
    public Handler_Print(ArrayList<String> printArray) {
        hepler = new Lesson5Hepler();
        this.printCommandList = printArray;
    }

    /**
     * Парсинг команды
     * @return
     */
    public boolean parseCommand() {
        try {
            if (hepler.isDigit(printCommandList.get(1))) {
                lineNumber = Integer.valueOf(printCommandList.get(1));
                fileName = printCommandList.get(2);

                log.debug("lineNumber = {}, fileName = {}",lineNumber ,fileName);
                hepler.validate(printCommandList);

                return true;
            }
            fileName = printCommandList.get(1);
            log.debug("lineNumber = {}, fileName = {}",lineNumber ,fileName);

            hepler.validate(printCommandList);

            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Ошибка ArrayIndexOutOfBoundsException {}", e.getMessage());
            return false;
        } catch (RuntimeException e) {
            log.warn("Ошибка RuntimeException {}", e.getMessage());
            return false;
        }
    }

    /**
     * Вывод результата
     */
    public void print() {
        ArrayList<String> printList = hepler.readFile(fileName);

        if ((lineNumber == -1) || (lineNumber > printList.size()-1)) {
            for (String s : printList){
                System.out.println(s);
            }
            return;
        }
        System.out.println(printList.get(lineNumber-1));
    }

}
