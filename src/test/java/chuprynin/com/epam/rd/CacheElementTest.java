package chuprynin.com.epam.rd;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CacheElementTest {

    @Test
    void checkCacheElementConstructor() {
        CacheElement<String> cacheElement = new CacheElement<>("Test1", 1);
        assertTrue(cacheElement.getClass() == CacheElement.class);
    }

    @Test
    void checkCacheElementGetElement() {
        String expectedResult = "Test1";
        CacheElement<String> cacheElement = new CacheElement<>(expectedResult, 1);
        assertEquals(expectedResult, cacheElement.getElement());
    }

    @Test
    void checkCacheElementSetElement() {
        String expectedResult = "Test1";
        String actualtedResult = "Test2";

        CacheElement<String> cacheElement = new CacheElement<>(expectedResult, 1);
        cacheElement.setElement(actualtedResult);

        assertEquals(actualtedResult, cacheElement.getElement());
    }

    @Test
    void checkCacheElementGetIndex() {
        int expectedResult = 1;
        CacheElement<String> cacheElement = new CacheElement<>("Test1", expectedResult);
        assertTrue(expectedResult == cacheElement.getIndex());
    }

    @Test
    void checkCacheElementIndex() {
        int expectedResult = 1;
        int actualtedResult = 2;

        CacheElement<String> cacheElement = new CacheElement<>("Test1", expectedResult);
        cacheElement.setIndex(actualtedResult);

        assertTrue(actualtedResult == cacheElement.getIndex());
    }

    @Test
    void checkCacheElementEquals() {
        CacheElement<String> cacheElement = new CacheElement<>("Test1", 1);
        CacheElement<String> cacheElement2 = new CacheElement<>("Test1", 1);
        assertTrue(cacheElement.equals(cacheElement));
        assertFalse(cacheElement.equals(null));
        assertTrue(cacheElement.equals(cacheElement2));
    }

    @Test
    void checkCacheHashCode() {
        CacheElement<String> cacheElement = new CacheElement<>("Test1", 1);
        assertTrue(Objects.hash("Test1", 1) == cacheElement.hashCode());
    }
}