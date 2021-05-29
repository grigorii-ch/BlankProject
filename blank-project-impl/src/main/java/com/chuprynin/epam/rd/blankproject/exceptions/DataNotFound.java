package com.chuprynin.epam.rd.blankproject.exceptions;

/**
 * Ошибка используется когда в БД не нашли данные
 */
public class DataNotFound extends RuntimeException {
    public DataNotFound(String message) {
        super(message);
    }
}
