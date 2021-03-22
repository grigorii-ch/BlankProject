package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.model.ViewData;

public class Lesson1 {
    private static final String TEST_STRING = "Java is a set of computer software and specifications developed by James Gosling at Sun Microsystems, which was later acquired by the Oracle Corporation, that provides a system for developing application software and deploying it in a cross-platform computing environment. Java is used in a wide variety of computing platforms from embedded devices and mobile phones to enterprise servers and supercomputers. Java applets, which are less common than standalone Java applications, were commonly run in secure, sandboxed environments to provide many features of native applications through being embedded in HTML pages.\n" +
            "Writing in the Java programming language is the primary way to produce code that will be deployed as byte code in a Java virtual machine (JVM); byte code compilers are also available for other languages, including Ada, JavaScript, Python, and Ruby. In addition, several languages have been designed to run natively on the JVM, including Clojure, Groovy, and Scala. Java syntax borrows heavily from C and C++, but object-oriented features are modeled after Smalltalk and Objective-C.[12] Java eschews certain low-level constructs such as pointers and has a very simple memory model where objects are allocated on the heap (while some implementations e.g. all currently supported by Oracle, may use escape analysis optimization to allocate on the stack instead) and all variables of object types are references. Memory management is handled through integrated automatic garbage collection performed by the JVM.\n" +
            "On November 13, 2006, Sun Microsystems made the bulk of its implementation of Java available under the GNU General Public License (GPL).[13][14]\n" +
            "The latest version is Java 16, released in March 2021. As an open source platform, Java has many distributors, including Amazon, IBM, Azul Systems, and AdoptOpenJDK. Distributions include Amazon Correto, Zulu, AdoptOpenJDK, and Liberica. Regarding Oracle, it distributes Java 8, and also makes available e.g. Java 11, a currently supported long-term support (LTS) version, released on September 25, 2018. Oracle (and others) \"highly recommend that you uninstall older versions of Java\" than Java 8,[15] because of serious risks due to unresolved security issues.[16][17][18] Since Java 9 (and 10, 12, 13, 14 and 15) is no longer supported, Oracle advises its users to \"immediately transition\" to a supported version. Oracle released the last free-for-commercial-use public update for the legacy Java 8 LTS in January 2019, and will continue to support Java 8 with public updates for personal use indefinitely. Oracle extended support for Java 6 ended in December 2018.[19]";

    ViewData viewData;

    Lesson1(){
        System.out.println("Строка для тестирования: \n" + TEST_STRING);
    }

    void Point_1 () {
        System.out.println(viewData.P1.getValue() + TEST_STRING.length());
    }

    void Point_2 (String secondStrin) {
        System.out.println(viewData.P2.getValue() + "Сравнивание тестовой строки с " + secondStrin + " = " + TEST_STRING.equalsIgnoreCase(secondStrin));
    }

    void Point_3 () {
        String stringPull = new String("Hello World").intern();
        System.out.println(viewData.P3.getValue() + stringPull);
    }

    void Point_4 () {
        char[] charArr = TEST_STRING.toCharArray();
        System.out.println(viewData.P4.getValue());
        for(Character elem: charArr)
            System.out.println(elem);
    }

    void Point_5 () {
        byte[] byteArr = TEST_STRING.getBytes();
        System.out.println(viewData.P5.getValue());
        for(byte elem: byteArr)
            System.out.println(elem);

    }

    void Point_6 () {
        System.out.println(viewData.P6.getValue());
        System.out.println(TEST_STRING.toUpperCase());
    }

    void Point_7 () {
        System.out.println(viewData.P7.getValue());
        System.out.println(TEST_STRING.indexOf('a'));
    }

    void Point_8 () {
        System.out.println(viewData.P8.getValue());
        System.out.println(TEST_STRING.lastIndexOf('a'));
    }

    void Point_9 () {
        System.out.println(viewData.P9.getValue());
        System.out.println(TEST_STRING.contains("Sun"));
    }

    void Point_10 () {
        System.out.println(viewData.P10.getValue());
        String lastWold = "Oracle";
        System.out.println(TEST_STRING.lastIndexOf(lastWold) == TEST_STRING.length() - lastWold.length());
    }

    void Point_11 () {
        System.out.println(viewData.P11.getValue());
        String firstWold = "Java";
        System.out.println(TEST_STRING.indexOf(firstWold) == 0);
    }

    void Point_12 () {
        System.out.println(viewData.P12.getValue());
        System.out.println(TEST_STRING.replace('a','o'));
    }

    void Point_13 () {
        System.out.println(viewData.P13.getValue());
        System.out.println(TEST_STRING.substring(44,90));
    }

    void Point_14 () {
        System.out.println(viewData.P14.getValue());
        String[] stringArr = TEST_STRING.split(" ");
        for (int i = 0; i < stringArr.length; i++)
            System.out.println(stringArr[i]);
    }

    void Point_15 () {
        System.out.println(viewData.P14.getValue());
        System.out.println(new StringBuilder(TEST_STRING).reverse().toString());
    }



}
