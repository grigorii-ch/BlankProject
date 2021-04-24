package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.pojo.Human;
import com.chuprynin.epam.rd.pojo.Student;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования
 */
public class ProcessTest {
    /**
     * Тест - проверяет все ситуации заполнения из аннотаций
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    void processMethodTestWithValue() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Human human = new Human();
        Process process = new Process();
        human = (Human) process.run(human, human.getClass());
        assertEquals(human.getAge(), 99);
        assertEquals(human.getName(), ("Grisha"));
    }

    /**
     * Тест - проверяет все ситуации заполнения из файла
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    void processMethodTestWithData() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Student student = new Student();
        Process process = new Process();
        student = (Student) process.run(student, student.getClass());
        System.out.println(student.getAge());
        System.out.println(student.getName());
        assertEquals(student.getAge(), 128);
        assertEquals(student.getName(), ("Vasia"));
    }
}