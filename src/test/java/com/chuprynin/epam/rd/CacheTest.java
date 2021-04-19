package com.chuprynin.epam.rd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CacheTest {

    @Test
    void checkCacheConstructor() {
        Cache<String> cache = new Cache<>(2);
        assertEquals(cache.getCache().length, 2);
    }

    @Test
    void checkCacheAddToFreePosition() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);

        assertEquals(cache.get(2), "Test2");
    }

    @Test
    void checkCacheAddToLastPositionWithMoveSubRange() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);
        cache.add("Test3", 3);

        assertNull(cache.get(1), "Test1");
        assertEquals(cache.get(2), "Test2");
        assertEquals(cache.get(3), "Test3");
    }


    @Test
    void checkCacheDelete() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);

        assertTrue(cache.isPresent("Test2"));
        cache.delete("Test2");
        assertFalse(cache.isPresent("Test2"));
    }

    @Test
    void checkCacheIsPresentByElement() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);

        assertTrue(cache.isPresent("Test2"));
    }

    @Test
    void checkCacheIsNoPresentByElement() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);

        assertFalse(cache.isPresent("Test20"));
    }

    @Test
    void checkCacheIsPresentByIndex() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);

        assertTrue(cache.isPresent(2));
    }

    @Test
    void checkCacheIsNotPresentByIndex() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(2);
        cache.add("Test1", 1);
        cache.add("Test2", 2);

        assertFalse(cache.isPresent(20));
    }

    @Test
    void checkCacheGetElement() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(1);
        cache.add("Test1", 1);

        assertEquals(cache.get(1), "Test1");
    }

    @Test
    void checkCacheGetNull() throws CacheElementNotExists {
        Cache<String> cache = new Cache<>(1);
        cache.add("Test1", 1);

        assertNull(cache.get(2));
    }

    @Test
    void checkCacheAddWithCacheElementNotExistsException() {
        Assertions.assertThrows(CacheElementNotExists.class, () -> {
                    Cache<String> cache = new Cache<>(1);
                    cache.add(null, 100);
                }
        );
    }

    @Test
    void checkCacheToString() {
        Cache<String> cache = new Cache<>(1);
        String expectedResoult = "Cache{cache=[null], capacity=1}";
        assertEquals(expectedResoult, cache.toString());
    }
}