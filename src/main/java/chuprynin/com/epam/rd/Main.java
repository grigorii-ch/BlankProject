package chuprynin.com.epam.rd;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("Запуск программы");
        Scanner scanner = new Scanner(System.in);

        CommandFilter commandFilter = new CommandFilter();

         do {
            String inLine = scanner.nextLine();
            log.debug("Введенно с консоли: {}", inLine);
            commandFilter.defineCommand(inLine);
        } while (!commandFilter.isEndProgram());
        log.debug("Завершение программы программы");
    }
}
