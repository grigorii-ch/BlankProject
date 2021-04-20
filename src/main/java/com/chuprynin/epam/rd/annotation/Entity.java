package com.chuprynin.epam.rd.annotation;

import java.lang.annotation.*;

@Inherited /** аннотация может быть расширена подклассами аннотируемого класса */
@Target(ElementType.TYPE) /** какие элементы аннотации могут быть к ней применены */
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
 }
