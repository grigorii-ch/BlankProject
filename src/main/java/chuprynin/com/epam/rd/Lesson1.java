package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.enums.EnumData;

public class Lesson1 {

    public int task_1(String testString) {
        return testString.length();
    }

    public boolean task_2(String testString, String secondStrin) {
        return testString.equalsIgnoreCase(secondStrin);
    }

    public String task_3(String newString) {
        return new String(newString).intern();
    }

    public char[] task_4(String testString) {
        return testString.toCharArray();
    }

    public byte[] task_5(String testString) {
        return testString.getBytes();
    }

    public String task_6(String testString) {
        return testString.toUpperCase();
    }

    public int task_7(String testString) {
        return testString.indexOf('a');
    }

    public int task_8(String testString) {
        return testString.lastIndexOf('a');
    }

    public boolean task_9(String testString) {
        return testString.contains("Sun");
    }

    public boolean task_10(String testString) {
        return testString.lastIndexOf("Oracle") == testString.length() - "Oracle".length();
    }

    public boolean task_11(String testString) {
        return testString.indexOf("Java") == 0;
    }

    public String task_12(String testString) {
        return testString.replace('a', 'o');
    }

    public String task_13(String testString) throws Exception {
        if (testString.length() < 134) {
            throw new Exception("Длина строки не позволяет получить запрашиваемую подстроку");
        } else {
            return testString.substring(44, 90);
        }
    }

    public String[] task_14(String testString) {
        return testString.split(" ");
    }

    public String task_15(String testString) {
        return new StringBuilder(testString).reverse().toString();
    }
}
