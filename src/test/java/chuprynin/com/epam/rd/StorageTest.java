package chuprynin.com.epam.rd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    public void checkStorageConstructorWithParam(){
        Storage<String> storage = new Storage<>(new String[]{"Test1"});
        assertTrue(storage.getCapacity() == 1 && storage.getCache().getCache().length == 1);
    }
}