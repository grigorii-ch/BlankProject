package com.chuprynin.epam.rd.Task1and2;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс для демонстрации RaceCondition
 */
@Slf4j
public class RaceConditionSample {
    private static final String EGG = "egg";
    private static final String CHICKEN = "chicken";
    private String first;

    /**
     * Метод отображает результат гонки данных
     */
    public void runRaceCondition() {
        Runnable newEgg = getRunnable();
        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(newEgg, String.format("Thread: %d", i) );
            th.start();
            setFirst(CHICKEN);
        }
    }

    /**
     * Метод предотвращает гонку данных путем ожидания предыдущего потока
     */
    public void decisionRaceCondition() throws InterruptedException {
        Runnable newEgg = getRunnable();
        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(newEgg, String.format("Thread: %d", i) );
            th.start();
            th.join();
            setFirst(CHICKEN);
        }
    }

    /**
     * Получение имплементации интерфейса
     * @return Имплементация интерфейса Runnable
     */
    private Runnable getRunnable() {
        return () -> setFirst(EGG);
    }

    /**
     * Метод для установки и отображения текущего значения
     * @param first Имплементация интерфейса Runnable
     */
    private void setFirst(String first) {
        this.first = first;
        log.debug("first = {}", this.first);
    }
}