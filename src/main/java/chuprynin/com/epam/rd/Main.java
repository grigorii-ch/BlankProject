package chuprynin.com.epam.rd;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        try {
//            ImitateOutOfMemoryError imitate = new ImitateOutOfMemoryError();
//            imitate.doIt();
//        } catch (OutOfMemoryError e) {
//            System.out.println("OutOfMemoryError - Yes!");
//        }
//
//        try {
//            ImitateStackOverflowError imitate = new ImitateStackOverflowError();
//            imitate.doIt();
//        } catch (StackOverflowError e) {
//            System.out.println("StackOverflowError - Yes!");
//        }

        try {
            String filePath = "D:\\TestJava";

            if(args.length > 0) {
                if (!Objects.isNull(args[0])) {
                    filePath = args[0];
                }
            }

            CustomClassLoader classLoader = new CustomClassLoader();
            classLoader.findClass(filePath);

        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
