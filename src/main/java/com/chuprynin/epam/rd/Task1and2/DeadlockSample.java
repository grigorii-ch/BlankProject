package com.chuprynin.epam.rd.Task1and2;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс имитации Deadlock процесса
 */

@Slf4j
public class DeadlockSample {

    private final Object egg = new Object();
    private final Object chicken = new Object();

    /**
     * Запуск процесса имитирующий взаимную блокировку
     * @param timeout - время таймаута между вызовами, при 0 - произойдет блокировка.
     * @throws InterruptedException - ошибка прерывания процесса
     */
    public void runDeadLock(int timeout) throws InterruptedException {
        Thread th1 = new Thread(setEgg, "eggThread");
        Thread th2 = new Thread(setChicken, "chickenThread");
        th1.start();
        Thread.sleep(timeout);
        th2.start();

    }

    /**
     * Блокировка объекта egg, chicken - последовательно
     */
    private Runnable setEgg = () -> {
        synchronized (egg) {
            log.debug("Блокируем egg");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.debug("egg ждет chicken");
            }
            synchronized (chicken) {
                log.debug("В egg блокируем chicken");
            }
        }
    };

    /**
     * Блокировка объекта chicken, egg - последовательно
     */
    private Runnable setChicken = () -> {
        synchronized (chicken) {
            log.debug("Блокируем chicken");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.debug("chicken ждет egg");
            }
            synchronized (egg) {
                log.debug("В chicken блокируем egg");
            }
        }
    };
}

