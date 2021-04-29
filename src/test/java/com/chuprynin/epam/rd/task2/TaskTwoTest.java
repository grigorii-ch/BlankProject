package com.chuprynin.epam.rd.task2;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для проведения тестов
 */
class TaskTwoTest {
    /**
     * Провека задания Test1
     */
    @Test
    void run() {
        TaskTwo taskTwo = new TaskTwo();
        Optional optional = taskTwo.run();
        assertTrue(optional.isPresent());
    }
    /**
     * Провека задания Test1
     */
    @Test
    void runStream() {
        TaskTwo taskTwo = new TaskTwo();
        Optional optional = taskTwo.runStream();
        assertTrue(optional.isPresent());
    }
}