package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;
import com.chuprynin.epam.rd.pojo.Building;
import com.chuprynin.epam.rd.pojo.Human;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ProcessTest {
    @Test
    void processMethodTestWithStandartHumanClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Human human = new Human();
        Process process = new Process();
        human = (Human) process.run(human, human.getClass());
        assertEquals(human.getAge(), 25);
        assertEquals(human.getName(), ("Vasia"));
    }

    @Test
    void processMethodTestWithTestHumanFieldsClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HumanTestFields humanTestFields = new HumanTestFields();
        Process process = new Process();
        humanTestFields = (HumanTestFields) process.run(humanTestFields, humanTestFields.getClass());
        assertEquals(humanTestFields.getAge(), 1);
        assertEquals(humanTestFields.getName(), ("TestName"));
    }

    @Test
    void processMethodTestWithTestHumanMethodsClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HumanTestMethods humanTestMethods = new HumanTestMethods();
        Process process = new Process();
        humanTestMethods = (HumanTestMethods) process.run(humanTestMethods, humanTestMethods.getClass());
        assertEquals(humanTestMethods.getAge(), 1);
        assertEquals(humanTestMethods.getName(), ("TestName"));
    }

    @Test
    void processMethodTestWithTestHumanMethodsClassFromFile() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HumanTestMethodsFromData humanTestMethodsFromData = new HumanTestMethodsFromData();
        Process process = new Process();
        humanTestMethodsFromData = (HumanTestMethodsFromData) process.run(humanTestMethodsFromData, humanTestMethodsFromData.getClass());
        assertEquals(1, humanTestMethodsFromData.getAge());
        assertEquals("TestName", humanTestMethodsFromData.getName());
    }

    @Test
    void processMethodTestWithStandartBuildingClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Building building = new Building();
        Process process = new Process();
        building = (Building) process.run(building, building.getClass());
        assertEquals(building.getNumber(),0);
        assertNull(building.getStreet());
    }

    @Entity
    class HumanTestFields {
        @Value(value = "1")
        private int age;
        @Value(value = "TestName")
        private String name;

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "HumanTest{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Entity
    class HumanTestMethods {
        private int age;
        private String name;

        @Value(value = "1")
        public void setAge(int age) {
            this.age = age;
        }

        @Value(value = "TestName")
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "HumanTest{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Entity
    class HumanTestMethodsFromData {
        private int age;
        private String name;

        @Value(value = "1", path = "age")
        public void setAge(int age) {
            this.age = age;
        }

        @Value(value = "TestName", path = "name")
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "HumanTest{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}