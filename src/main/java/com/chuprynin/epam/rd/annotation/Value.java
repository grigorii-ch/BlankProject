package com.chuprynin.epam.rd.annotation;

import java.lang.annotation.*;

/**
 * Аннотакия Value для полей и методов класов
 * назначение - хранения данных и использования во время выполнения через reflection
 * Работает в паре с аннотацией Entity
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value() default "7777777";
    String path() default "";
}
