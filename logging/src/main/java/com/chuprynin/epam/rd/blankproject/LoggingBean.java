package com.chuprynin.epam.rd.blankproject;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * Аспект, для логирования информации по методам с аннотацией @Logging
 */
@Slf4j
@Aspect
public class LoggingBean {
    @Pointcut("@annotation(com.chuprynin.epam.rd.blankproject.annotation.Logging)")
    public void callLogging() {
    }

    @After(value = "callLogging()")
    public void loggingData(JoinPoint joinPoint) {
        log.debug("Informatin from method - {}", joinPoint.getSignature());
        Arrays.stream(joinPoint.getArgs())
                .forEach(obj -> {
                    log.debug("Argument type: {}, argument value: {}", obj.getClass().getName(), obj.toString());
                });
        //return joinPoint.proceed();
    }
}
