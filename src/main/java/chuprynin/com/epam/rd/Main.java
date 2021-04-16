package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@Slf4j
public class Main {

    private final static String DEFAULT_FILE_PATH = ".class";

    public static void main(String[] args) {

        log.debug("Start");

        loadClass(args);

        getImitateStackOverflow();

        getImitateOutOfMemory();

        log.debug("end");
    }

    private static void loadClass(String[] args) {

        try {
            String filePath = DEFAULT_FILE_PATH;

            if(args.length > 0) {
                if (!Objects.isNull(args[0])) {
                    filePath = args[0];
                }
            }

            CustomClassLoader classLoader = new CustomClassLoader();
            Class c = classLoader.findClass(filePath);
            Object obj = c.getDeclaredConstructor().newInstance();
            System.out.println(obj);

        } catch (Exception e) {
            log.warn("Ошибка: {}", e.getMessage(), e);
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
