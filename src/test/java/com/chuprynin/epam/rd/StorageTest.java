package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.exceptions.StorageElementNotExists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void checkStorageConstructorWithParam() {
        Storage<String> storage = new Storage<>(new String[]{"Test1"});

        assertEquals(storage.getCapacity(), 1);
        assertEquals(storage.getCache().getCache().length, 1);
    }

    @Test
    void checkStorageConstructorWithOutParam() {
        Storage<String> storage = new Storage<>();

        assertEquals(storage.getCapacity(), 10);
        assertEquals(storage.getCache().getCache().length, 10);
    }

    @Test
    void checkStorageAddElement() {
        String testElement = "testString";

        Storage<String> storage = new Storage<>();
        storage.add(testElement);

        assertEquals(testElement, storage.get(0));
    }

    @Test
    void checkStorageDeleteElement() {
        String testElement = "Test5";

        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", testElement});
        storage.delete();

        assertNotEquals(testElement, storage.get(4));
        assertFalse(storage.getCache().isPresent(testElement));
    }

    @Test
    void checkStorageGetLastElement() {
        String testElement = "Test5";
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", testElement});

        assertEquals(testElement, storage.getLast());
    }

    @Test
    void checkStorageGetElementReturnFromArray() {
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3"});
        storage.add("Test4");

        assertFalse(storage.getCache().isPresent("Test4"));
        assertEquals("Test4", storage.get(3));
    }

    @Test
    void checkStorageGetElementWithStorageElementNotExists() {
        Assertions.assertThrows(StorageElementNotExists.class, () -> {
                    Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3"});
                    storage.add(null);
                }
        );
    }

    @Test
    void checkStorageClear() {
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3"});
        storage.clear();
        Object[] storage1 = storage.getStorage();
        assertNull(storage1[0]);


    }
}