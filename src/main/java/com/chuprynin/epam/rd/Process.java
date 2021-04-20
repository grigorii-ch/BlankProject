package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.annotation.Entity;
import com.chuprynin.epam.rd.annotation.Value;
import com.chuprynin.epam.rd.pojo.Buinding;
import com.chuprynin.epam.rd.pojo.Human;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class Process {

    public void run() {

        Human human = new Human();
        Buinding buinding = new Buinding();

        Class<? extends Human> humanClass = human.getClass();
        Class<? extends Buinding> buindingClass = buinding.getClass();

        if (isEntityAnnotation(humanClass)) {
            if (!isValueFieldAnnotation(humanClass)) {
                if (!isValueMethodAnnotation(humanClass)) {
                    throw new RuntimeException("Tratata");
                }
            }
        }


    }

    private boolean isEntityAnnotation(Class aClass) {
        return Objects.nonNull(aClass.getAnnotation(Entity.class));
    }

    private boolean isValueFieldAnnotation(Class aClass) {
        Field[] fields = aClass.getDeclaredFields();
        System.out.println(fields.length);
        for (Field field : fields) {
            System.out.println(field.getAnnotation(Value.class));
            if (Objects.nonNull(field.getAnnotation(Value.class))) {
                return true;
            }
        }
        return false;
    }

    private boolean isValueMethodAnnotation(Class aClass) {
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (Objects.nonNull(method.getAnnotation(Value.class))) {
                return true;
            }
        }
        return false;
    }

}
