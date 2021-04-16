package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("Start");

        loadClass(args);

        getImitateStackOverflow();

        getImitateOutOfMemory();

        log.debug("end");
    }
    private static void loadClass(String[] args) {

        try {
            String filePath = "C:\\myClasses";

            if(args.length > 0) {
                if (!Objects.isNull(args[0])) {
                    filePath = args[0];
                }
            }

            CustomClassLoader classLoader = new CustomClassLoader();
            Class c = classLoader.findClass(filePath);
            Object obj = c.newInstance();
            System.out.println(obj);

        } catch (ClassNotFoundException e) {
            log.warn("Ошибка: {}", e.getMessage());
        } catch (IllegalAccessException e) {
            log.warn("Ошибка: {}", e.getMessage());
        } catch (InstantiationException e) {
            log.warn("Ошибка: {}", e.getMessage());
        }
    }

    private static void getImitateStackOverflow() {
        try {
            ImitateStackOverflowError imitate = new ImitateStackOverflowError();
            imitate.doIt();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError - Yes!");
        }
    }

    private static void getImitateOutOfMemory() {
        try {
            ImitateOutOfMemoryError imitate = new ImitateOutOfMemoryError();
            imitate.doIt();
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError - Yes!");
        }
    }
}
