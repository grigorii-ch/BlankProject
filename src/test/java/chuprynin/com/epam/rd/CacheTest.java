package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CacheTest {

    @Test
    void checkCacheConstructor() {
        Cache<String> cache = new Cache<>(2);
        assertTrue(cache.getCache().length == 2);
    }

    @Test
    void checkCacheAddToFreePosition() {
        Cache<String> cache = new Cache<>(2);
        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertEquals(cache.get(2), "Test2");
    }

    @Test
    void checkCacheAddToLastPositionWithMoveSubRange() {
        Cache<String> cache = new Cache<>(2);
        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
            cache.add("Test3", 3);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertNull(cache.get(1), "Test1");
        assertEquals(cache.get(2), "Test2");
        assertEquals(cache.get(3), "Test3");
    }


    @Test
    void checkCacheDelete() {
        Cache<String> cache = new Cache<>(2);
        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertTrue(cache.isPresent("Test2"));
        cache.delete("Test2");
        assertFalse(cache.isPresent("Test2"));
    }

    @Test
    void checkCacheIsPresentByElement() {
        Cache<String> cache = new Cache<>(2);

        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertTrue(cache.isPresent("Test2"));
    }

    @Test
    void checkCacheIsNoPresentByElement() {
        Cache<String> cache = new Cache<>(2);
        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertFalse(cache.isPresent("Test20"));
    }

    @Test
    void checkCacheIsPresentByIndex() {
        Cache<String> cache = new Cache<>(2);
        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertTrue(cache.isPresent(2));
    }

    @Test
    void checkCacheIsNotPresentByIndex() {
        Cache<String> cache = new Cache<>(2);
        try {
            cache.add("Test1", 1);
            cache.add("Test2", 2);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertFalse(cache.isPresent(20));
    }

    @Test
    void checkCacheGetElemend() {
        Cache<String> cache = new Cache<>(1);
        try {
            cache.add("Test1", 1);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

        assertTrue("Test1".equals(cache.get(1)));
    }

    @Test
    void checkCacheGetNull() {
        Cache<String> cache = new Cache<>(1);
        try {
            cache.add("Test1", 1);
        } catch (CacheElementNotExists cacheElementNotExists) {
            log.warn(cacheElementNotExists.printFullStackTrace());
        }

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
}