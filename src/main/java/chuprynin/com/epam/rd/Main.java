package chuprynin.com.epam.rd;

import chuprynin.com.epam.rd.enums.EnumData;

public class Main {
    public static void main(String[] args) {

        final String TEST_STRING = new String("Java is a set of computer software and specifications developed by James Gosling at Sun Microsystems, which was later acquired by the Oracle Corporation, that provides a system for developing application software and deploying it in a cross-platform computing environment. Java is used in a wide variety of computing platforms from embedded devices and mobile phones to enterprise servers and supercomputers. Java applets, which are less common than standalone Java applications, were commonly run in secure, sandboxed environments to provide many features of native applications through being embedded in HTML pages.\n" +
                "Writing in the Java programming language is the primary way to produce code that will be deployed as byte code in a Java virtual machine (JVM); byte code compilers are also available for other languages, including Ada, JavaScript, Python, and Ruby. In addition, several languages have been designed to run natively on the JVM, including Clojure, Groovy, and Scala. Java syntax borrows heavily from C and C++, but object-oriented features are modeled after Smalltalk and Objective-C.[12] Java eschews certain low-level constructs such as pointers and has a very simple memory model where objects are allocated on the heap (while some implementations e.g. all currently supported by Oracle, may use escape analysis optimization to allocate on the stack instead) and all variables of object types are references. Memory management is handled through integrated automatic garbage collection performed by the JVM.\n" +
                "On November 13, 2006, Sun Microsystems made the bulk of its implementation of Java available under the GNU General Public License (GPL).[13][14]\n" +
                "The latest version is Java 16, released in March 2021. As an open source platform, Java has many distributors, including Amazon, IBM, Azul Systems, and AdoptOpenJDK. Distributions include Amazon Correto, Zulu, AdoptOpenJDK, and Liberica. Regarding Oracle, it distributes Java 8, and also makes available e.g. Java 11, a currently supported long-term support (LTS) version, released on September 25, 2018. Oracle (and others) \"highly recommend that you uninstall older versions of Java\" than Java 8,[15] because of serious risks due to unresolved security issues.[16][17][18] Since Java 9 (and 10, 12, 13, 14 and 15) is no longer supported, Oracle advises its users to \"immediately transition\" to a supported version. Oracle released the last free-for-commercial-use public update for the legacy Java 8 LTS in January 2019, and will continue to support Java 8 with public updates for personal use indefinitely. Oracle extended support for Java 6 ended in December 2018.[19]").intern();

        Lesson1 lesson1 = new Lesson1();

        System.out.println(EnumData.P1.getValue() + " " + lesson1.task_1(TEST_STRING));

        System.out.println(EnumData.P2.getValue() + " " + lesson1.task_2(TEST_STRING, "Hello world!"));

        System.out.println(EnumData.P3.getValue() + " " + lesson1.task_3("New String"));

        System.out.print(EnumData.P4.getValue() + " ");
        char[] arrChar = lesson1.task_4(TEST_STRING);
        for (Character c : arrChar) {
            System.out.print(c.charValue() + " ");
        }
        System.out.println();

        System.out.print(EnumData.P5.getValue() + " ");
        byte[] byteChar = lesson1.task_5(TEST_STRING);
        for (byte b : byteChar) {
            System.out.print(b + " ");
        }
        System.out.println();

        System.out.println(EnumData.P6.getValue() + " " + lesson1.task_6(TEST_STRING));

        System.out.println(EnumData.P7.getValue() + " " + lesson1.task_7(TEST_STRING));

        System.out.println(EnumData.P8.getValue() + " " + lesson1.task_8(TEST_STRING));

        System.out.println(EnumData.P9.getValue() + " " + lesson1.task_9(TEST_STRING));

        System.out.println(EnumData.P10.getValue() + " " + lesson1.task_10(TEST_STRING));

        System.out.println(EnumData.P11.getValue() + " " + lesson1.task_11(TEST_STRING));

        System.out.println(EnumData.P12.getValue() + " " + lesson1.task_12(TEST_STRING));

        System.out.print(EnumData.P13.getValue() + " ");
        try {
            System.out.println(lesson1.task_13(TEST_STRING));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(EnumData.P14.getValue() + " " + lesson1.task_14(TEST_STRING));

        System.out.println(EnumData.P15.getValue() + " " + lesson1.task_15(TEST_STRING));
    }
}
