package com.chuprynin.epam.rd.blankproject;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Around(value = "callLogging()")
    public Object loggingDataTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("Informatin from method - {}", joinPoint.getSignature());
        Arrays.stream(joinPoint.getArgs())
                .forEach(obj -> {
                    log.debug("Argument type: {}, argument value: {}", obj.getClass().getName(), obj.toString());
                });
        return joinPoint.proceed();
    }
}
