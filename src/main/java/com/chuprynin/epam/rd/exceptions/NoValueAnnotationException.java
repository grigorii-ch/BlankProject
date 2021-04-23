package com.chuprynin.epam.rd.exceptions;

/**
 * Исключение при наличии аннотации Entity и отсутсвии у полей pojo анотации Value
 * т.к Entity и Value - должны идти в паре
 */
public class NoValueAnnotationException extends Exception{
    public NoValueAnnotationException(String message) {
        super(message);
    }
}
