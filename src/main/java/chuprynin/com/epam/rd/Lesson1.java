package chuprynin.com.epam.rd;

/**
 * Класс для работы со строками
 * @author Григорий
 */
public class Lesson1 {

    /**
     * @param testString строка для подсчета
     * @return длинна проверочной строки
     */
    public int task_1(String testString) {
        return testString.length();
    }

    /**
     * @param testString первая строка
     * @param secondStrin вторая строка
     * @return результат сравнения двух строк без учета регистра
     */
    public boolean task_2(String testString, String secondStrin) {
        return testString.equalsIgnoreCase(secondStrin);
    }

    /**
     * @param newString текст для новой строки
     * @return новая строка
     */
    public String task_3(String newString) {
        return new String(newString).intern();
    }

    /**
     * @param testString строка для преобразования
     * @return массив символов
     */
    public char[] task_4(String testString) {
        return testString.toCharArray();
    }

    /**
     * @param testString строка для преобразования
     * @return массив байтов
    */
    public byte[] task_5(String testString) {
        return testString.getBytes();
    }

    /**
     * @param testString строка для преобразования
     * @return строка в верхнем регистре
     */
    public String task_6(String testString) {
        return testString.toUpperCase();
    }

    /**
     * @param testString строка для подсчета
     * @return первая позиция символа "а" в строке
     */
    public int task_7(String testString) {
        return testString.indexOf('a');
    }

    /**
     * @param testString строка для подсчета
     * @return последняя позиция символа "а" в строке
     */
    public int task_8(String testString) {
        return testString.lastIndexOf('a');
    }

    /**
     * @param testString строка для проверки
     * @return результат поиска слова "Sun"
     */
    public boolean task_9(String testString) {
        return testString.contains("Sun");
    }

    /**
     * @param testString строка для проверки
     * @return результат проверки, оканчивается-ли строка на "Oracle"
     */
    public boolean task_10(String testString) {
        return testString.lastIndexOf("Oracle") == testString.length() - "Oracle".length();
    }

    /**
     * @param testString строка для проверки
     * @return результат проверки, начинается-ли строка на "Java"
     */
    public boolean task_11(String testString) {
        return testString.indexOf("Java") == 0;
    }

    /**
     * @param testString строка для преобразования
     * @return строка с заменой символов 'a' на 'o'
     */
    public String task_12(String testString) {
        return testString.replace('a', 'o');
    }

    /**
     * @param testString строка для поиска
     * @return подстрока с 44 символа длиной в 90 символов
     * @exception Exception
     */
    public String task_13(String testString) throws Exception {
        if (testString.length() < 134) {
            throw new Exception("Длина строки не позволяет получить запрашиваемую подстроку");
        } else {
            return testString.substring(44, 90);
        }
    }

    /**
     * @param testString строка для преобразования
     * @return массив слов из строки
     */
    public String[] task_14(String testString) {
        return testString.split(" ");
    }

    /**
     * @param testString строка для преобразования
     * @return строка с обратной последовательность символов
     */
    public String task_15(String testString) {
        return new StringBuilder(testString).reverse().toString();
    }
}
