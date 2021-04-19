package com.chuprynin.epam.rd;

public class StorageElementNotExists extends RuntimeException {
    public StorageElementNotExists(String message) {
        super(message);
    }

}
