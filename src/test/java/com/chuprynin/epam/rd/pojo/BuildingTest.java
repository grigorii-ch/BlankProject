package com.chuprynin.epam.rd.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {

    @Test
    void getNumber() {
        Building building = new Building();
        building.setNumber(10);
        assertEquals(building.getNumber(), 10);
    }

    @Test
    void getStreet() {
        Building building = new Building();
        building.setStreet("Test");
        assertEquals(building.getStreet(), "Test");

    }
}