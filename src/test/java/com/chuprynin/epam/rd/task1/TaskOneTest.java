package com.chuprynin.epam.rd.task1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.*;

class TaskOneTest {

    @Test
    void run() throws IOException {
        TaskOne taskOne = new TaskOne();
        assertTrue(taskOne.run().size() > 0);
    }

    @Test
    void runStream() throws IOException {
        TaskOne taskOne = new TaskOne();
        assertTrue(taskOne.runStream().size() > 0);
    }
}