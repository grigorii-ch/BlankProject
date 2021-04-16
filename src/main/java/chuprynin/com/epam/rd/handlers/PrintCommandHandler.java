package chuprynin.com.epam.rd.handlers;

import chuprynin.com.epam.rd.emums.Commands;
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
public class PrintCommandHandler implements CommandHandler {
    /**
     * Конструктор
     */
    public PrintCommandHandler() {
    }

    /**
     * Вывод результата
     */
    private void print(Lesson5Hepler hepler, String fileName, int lineNumber) {
        ArrayList<String> printList = hepler.readFile(fileName);

        if ((lineNumber == -1) || (lineNumber > printList.size()-1)) {
            for (String s : printList){
                System.out.println(s);
            }
            return;
        }
        System.out.println(printList.get(lineNumber-1));
    }

    @Override
    public void handle(ArrayList<String> printArray) {
        Lesson5Hepler hepler = new Lesson5Hepler();
        int lineNumber = -1;
        String fileName = printArray.get(1);

        if (hepler.isDigit(printArray.get(1))) {
            lineNumber = Integer.valueOf(printArray.get(1));
            fileName = printArray.get(2);
        }

        log.debug("Команда {}", printArray.get(0));
        log.debug("lineNumber = {}, fileName = {}", lineNumber, fileName);

        print(hepler, fileName, lineNumber);
    }
}
