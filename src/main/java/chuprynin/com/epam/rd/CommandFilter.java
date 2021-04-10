package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.emums.Commands;
import chuprynin.com.epam.rd.handlers.Handler_Add;
import chuprynin.com.epam.rd.handlers.Handler_Delete;
import chuprynin.com.epam.rd.handlers.Handler_Print;
import chuprynin.com.epam.rd.helper.Lesson5Hepler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class CommandFilter {
    private boolean endProgram;
    private String command;

    /**
     * Конструктор
     */
    public CommandFilter() {
        this.endProgram = false;
    }

    /**
     * провер на завершение программы
     * @return
     */
    public boolean isEndProgram() {
        return endProgram;
    }

    /**
     * метод для определения введенной команды
     * @param command
     */
    public void defineCommand(String command) {
        ArrayList<String> parseCommand = new ArrayList<>();
        parseCommand.addAll(Arrays.asList(command.split(" ")));

        Lesson5Hepler lesson5Hepler = new Lesson5Hepler();
        parseCommand = lesson5Hepler.removeSpaces(parseCommand);

        command = parseCommand.get(0);


        if (Commands.ADD.getValue().equals(command)) {
            log.debug("Команда {}", command);
            Handler_Add handlerAdd = new Handler_Add(parseCommand);
            if (handlerAdd.parseCommand()) {
                handlerAdd.save();
                System.out.println(" - Опперация add выполнена");
                return;
            } else {
                System.out.println(" - Комманда имеет не верный формат");
                return;
            }
        }

        if (Commands.DELETE.getValue().equals(command)) {
            log.debug("Команда {}", command);
            Handler_Delete handlerDelete = new Handler_Delete(parseCommand);

            if (handlerDelete.parseCommand()) {
                handlerDelete.delete();
                System.out.println(" - Опперация delete выполнена");
                return;
            } else {
                System.out.println(" - Комманда имеет не верный формат");
                return;
            }
        }

        if (Commands.PRINT.getValue().equals(command)) {
            log.debug("Команда {}", command);
            Handler_Print handlerPrint = new Handler_Print(parseCommand);
            if (handlerPrint.parseCommand()) {
                handlerPrint.print();
                return;
            }
            return;
        }

        if (Commands.EXIT.getValue().equals(command)) {
            log.debug("Команда {}", command);
            this.endProgram = true;
            System.out.println("Заверщение программы.");
            return;
        }
        log.debug("Введена не извесьная команда {}", command);
        System.out.println(String.format("%s не известная команда.", command));
    }
}
