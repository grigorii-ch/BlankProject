package com.chuprynin.epam.rd.annotation;

import java.lang.annotation.*;

/**
 * Аннотакия Entity для класов
 * назначение - использование во время выполнения через reflection
 * Работает в паре с аннотацией Value
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
 }
