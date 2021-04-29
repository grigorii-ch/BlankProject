package com.chuprynin.epam.rd.task2;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для проведения тестов
 */
class TaskTwoTest {
    /**
     * Провека задания Test1
     */
    @Test
    void run() throws IOException {
        TaskTwo taskTwo = new TaskTwo();
        assertTrue(taskTwo.run().size() > 0);
    }
    /**
     * Провека задания Test1
     */
    @Test
    void runStream() throws IOException {
        TaskTwo taskTwo = new TaskTwo();
        assertTrue(taskTwo.runStream().size() > 0);
    }
}