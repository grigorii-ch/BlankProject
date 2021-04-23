package com.chuprynin.epam.rd.annotation;

import java.lang.annotation.*;


@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    String value() default "7777777";
    String path() default "";
}
