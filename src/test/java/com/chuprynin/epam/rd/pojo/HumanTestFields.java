package com.chuprynin.epam.rd.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTestFields {

    @Test
    void getAge() {
        Human human = new Human();
        human.setAge(10);
        assertEquals(human.getAge(),10);
    }

    @Test
    void getName() {
        Human human = new Human();
        human.setName("Test");
        assertEquals(human.getName(),"Test");
    }

    @Test
    void testToString() {
        Human human = new Human();
        assertEquals(human.toString(), "Human{age=0, name='null'}");
    }
}