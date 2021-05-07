package com.chuprynin.epam.rd;

import com.chuprynin.epam.rd.Task1and2.DeadlockSample;
import com.chuprynin.epam.rd.Task1and2.RaceConditionSample;
import com.chuprynin.epam.rd.Task3.Chat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        var raceConditionSample = new RaceConditionSample();
        log.info("Запуск RaceCondition");
        raceConditionSample.runRaceCondition();
        /* Ждем 5 секунд для завершения предыдушей таски */

        Thread.sleep(5000);
        log.info("Запуск решение RaceCondition");
        raceConditionSample.decisionRaceCondition();
        log.info("Запуск решение Deadlock");
        var deadlockSample = new DeadlockSample();
        // Вызов метода с 0 таймаутом заблокирует процесс
        // deadlockSample.runDeadLock(0);
        deadlockSample.runDeadLock(2000);

        Thread.sleep(5000);
        log.info("Запуск Chat - емкость 100 операций, с произвольным количеством write/read/update");
        var chat = new Chat();
        chat.run();
    }

}