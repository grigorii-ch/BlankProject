package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.emums.Commands;
import chuprynin.com.epam.rd.handlers.AddCommandHandler;
import chuprynin.com.epam.rd.handlers.CommandHandler;
import chuprynin.com.epam.rd.handlers.DeleteCommandHandler;
import chuprynin.com.epam.rd.handlers.PrintCommandHandler;
import chuprynin.com.epam.rd.helper.Lesson5Hepler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CommandFilter {
    private boolean endProgram;
    Map mapHandler = new HashMap<String,CommandHandler>();

    /**
     * Конструктор
     */
    public CommandFilter() {
        mapHandler.put(Commands.ADD.getValue(),new AddCommandHandler());
        mapHandler.put(Commands.DELETE.getValue(),new DeleteCommandHandler());
        mapHandler.put(Commands.PRINT.getValue(),new PrintCommandHandler());

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
        ArrayList<String> parseCommand =  new ArrayList<>();
        parseCommand.addAll(Arrays.asList(command.split(" ")));

        Lesson5Hepler lesson5Hepler = new Lesson5Hepler();
        parseCommand = lesson5Hepler.removeSpaces(parseCommand);

        if (Commands.EXIT.getValue().equals(parseCommand.get(0))) {
            this.endProgram = true;
            return;
        }

        try {
            CommandHandler handler = (CommandHandler) mapHandler.get(parseCommand.get(0));
            handler.handle(parseCommand);
        } catch (NullPointerException e) {
            log.warn("Ошибка {},  NullPointerException {}", "Не удалось определить команду", e.getMessage());
            System.out.println("Не удалось определить команду");
        }
    }
}
