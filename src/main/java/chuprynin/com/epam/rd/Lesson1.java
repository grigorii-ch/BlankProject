package chuprynin.com.epam.rd;

/**
 * Класс для работы со строками
 * @author Григорий
 */
public class Lesson1 {

    /**
     * Считает длину строки
     * @param testString строка для подсчета
     * @return длинна проверочной строки
     */
    public int task_1(String testString) {
        return testString.length();
    }

    /**
     * Сравнивает 2 строки без учета регистра
     * @param testString первая строка
     * @param secondStrin вторая строка
     * @return результат сравнения двух строк без учета регистра
     */
    public boolean task_2(String testString, String secondStrin) {
        return testString.equalsIgnoreCase(secondStrin);
    }

    /**
     * Создает новую строку с помощью конструктора и занесит ее в пул литералов
     * @param newString текст для новой строки
     * @return новая строка
     */
    public String task_3(String newString) {
        return new String(newString).intern();
    }

    /**
     * Превращает строку массив символов
     * @param testString строка для преобразования
     * @return массив символов
     */
    public char[] task_4(String testString) {
        return testString.toCharArray();
    }

    /**
     * Превращает строку массив байтов
     * @param testString строка для преобразования
     * @return массив байтов
    */
    public byte[] task_5(String testString) {
        return testString.getBytes();
    }

    /**
     * Переводит строку в верхний регистр
     * @param testString строка для преобразования
     * @return строка в верхнем регистре
     */
    public String task_6(String testString) {
        return testString.toUpperCase();
    }

    /**
     * Находит первую позицию символа "а" в строке
     * @param testString строка для подсчета
     * @return первая позиция символа "а" в строке
     */
    public int task_7(String testString) {
        return testString.indexOf('a');
    }

    /**
     * Находит последнюю позицию символа "а" в строке
     * @param testString строка для подсчета
     * @return последняя позиция символа "а" в строке
     */
    public int task_8(String testString) {
        return testString.lastIndexOf('a');
    }

    /**
     * Проверяет содержит-ли строка слово "Sun"
     * @param testString строка для проверки
     * @return результат поиска слова "Sun"
     */
    public boolean task_9(String testString) {
        return testString.contains("Sun");
    }

    /**
     * Проверяет оканчивается-ли строка на слово "Oracle"
     * @param testString строка для проверки
     * @return результат проверки, оканчивается-ли строка на "Oracle"
     */
    public boolean task_10(String testString) {
        return testString.lastIndexOf("Oracle") == testString.length() - "Oracle".length();
    }

    /**
     * Проверяет начинается-ли строка на "Java"
     * @param testString строка для проверки
     * @return результат проверки, начинается-ли строка на "Java"
     */
    public boolean task_11(String testString) {
        return testString.indexOf("Java") == 0;
    }

    /**
     * Заменяет все символы "а" в строке на символ "о"
     * @param testString строка для преобразования
     * @return строка с заменой символов 'a' на 'o'
     */
    public String task_12(String testString) {
        return testString.replace('a', 'o');
    }

    /**
     * Получает подстроку с 44 символа по 90 символ:
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
     * Разбивает строку в массив по символу пробел
     * @param testString строка для преобразования
     * @return массив слов из строки
     */
    public String[] task_14(String testString) {
        return testString.split(" ");
    }

    /**
     * Меняет последовательность символов в строке на обратную
     * @param testString строка для преобразования
     * @return строка с обратной последовательность символов
     */
    public String task_15(String testString) {
        return new StringBuilder(testString).reverse().toString();
    }
}
