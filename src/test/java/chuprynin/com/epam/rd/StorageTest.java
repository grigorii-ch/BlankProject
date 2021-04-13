package chuprynin.com.epam.rd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    public void checkStorageConstructorWithParam() {
        Storage<String> storage = new Storage<>(new String[]{"Test1"});

        assertTrue(storage.getCapacity() == 1 && storage.getCache().getCache().length == 1);
    }

    @Test
    public void checkStorageConstructorWithOutParam() {
        Storage<String> storage = new Storage<>();

        assertTrue(storage.getCapacity() == 10 && storage.getCache().getCache().length == 10);
    }

    @Test
    public void checkStorageAddElement() {
        String testElement = "testString";

        Storage<String> storage = new Storage<>();
        storage.add(testElement);

        assertEquals(testElement, storage.get(0));
    }

    @Test
    public void checkStorageDeleteElement() {
        String testElement = "Test5";

        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", testElement});
        storage.delete();

        assertNotEquals(testElement, storage.get(4));
        assertFalse(storage.getCache().isPresent(testElement));
    }

    @Test
    public void checkStorageClearElements() {
        Storage<String> storage = new Storage<>(new String[]{"Test1"});
        storage.clear();

        assertNull(storage.get(0));
    }

    @Test
    public void checkStorageGetLastElement() {
        String testElement = "Test5";
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3", "Test4", testElement});

        assertTrue(testElement.equals(storage.getLast()));
    }

    @Test
    public void checkStorageGetLastElementNull() {
        Storage<String> storage = new Storage<>(new String[]{null});

        assertNull(storage.getLast());
    }


    @Test
    public void checkStorageGetElementReturnNull() {
        Storage<String> storage = new Storage<>();

        assertNull(storage.get(11));
    }

    @Test
    public void checkStorageGetElementReturnFromArray() {
        Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3"});
        storage.add("Test4");

        assertFalse(storage.getCache().isPresent("Test4"));
        assertEquals("Test4", storage.get(3));
    }

    @Test
    public void checkStorageGetElementWithStorageElementNotExists() {
        Assertions.assertThrows(StorageElementNotExists.class, () -> {
                    Storage<String> storage = new Storage<>(new String[]{"Test1", "Test2", "Test3"});
                    storage.add(null);
                }
        );
    }
}