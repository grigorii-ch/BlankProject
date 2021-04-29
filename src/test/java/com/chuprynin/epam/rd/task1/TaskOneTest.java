package com.chuprynin.epam.rd.task1;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskOneTest {

    @Test
    void run() {
        TaskOne taskOne = new TaskOne();
        Optional optional = taskOne.run();
        assertTrue(optional.isPresent());
    }

    @Test
    void runStream() {
        TaskOne taskOne = new TaskOne();
        Optional optional = taskOne.runStream();
        assertTrue(optional.isPresent());
    }
}