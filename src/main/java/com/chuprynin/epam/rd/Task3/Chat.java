package com.chuprynin.epam.rd.Task3;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс имитирует работу SMS чата ёмкостью 100 операций
 */
@Slf4j
@Data
public class Chat {
    private List<String> chatTred = new ArrayList<>();
    private List<Runnable> chatActions = new ArrayList<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * Максимально кол-во сообщений в чате
     */
    private int maxChatSize = 25;
    /**
     * Кол-во операций
     */
    private int maxActionSize = 100;

    /**
     * Метод для запуска
     * запускаем повторяемый поток для 100 операций с задержкой в секунду
     */
    public void run() {
        imitationChat();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);
        chatActions.forEach(elem -> {
            executor.scheduleWithFixedDelay(elem, 0, 1, TimeUnit.SECONDS);
        });
    }

    /**
     * Метод имитирует работу чата емкостью 100 операций
     */
    private void imitationChat() {
        Map<Integer, Runnable> map = new HashMap<>();
        map.put(0, write());
        map.put(1, update());
        map.put(2, read());
        for (int i = 0; i < maxActionSize; i++) {
            chatActions.add(map.get(new Random().nextInt(map.size())));
        }
    }

    /**
     * Метод для добавления записи в чате
     *
     * @return Имплементация интерфейса Runnable, содержит операцию SMS чата
     */
    private Runnable write() {
        return () -> {
            try {
                Thread.sleep(20000 + new Random().nextInt(40000));
                if (chatTred.size() < maxChatSize) {
                    String newSms = UUID.randomUUID().toString();
                    readWriteLock.writeLock().lock();
                    log.debug("Добавление SMS в чат {}", newSms);
                    chatTred.add(newSms);
                } else {
                    log.debug("Добавление SMS, не возможно - чат переполнен");
                }
            } catch (Exception e) {
                log.warn("Ошибка: {} ", e.getMessage(), e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        };
    }

    /**
     * Метод для обновления записи в чате
     *
     * @return Имплементация интерфейса Runnable, содержит операцию SMS чата
     */
    private Runnable update() {
        return () -> {
            try {
                // Добавил таймаут для update, без задержки - он будет бесконечно обновлять записи
                Thread.sleep(30000 + new Random().nextInt(20000));
                readWriteLock.writeLock().lock();
                if (chatTred.size() > 0) {
                    var randonSms = new Random().nextInt(chatTred.size());
                    var tmp = chatTred.get(randonSms);
                    var newValue = tmp + " changed";
                    log.debug("Обновление SMS сообщения:{}, новое значение:{}", tmp, newValue);
                    chatTred.remove(randonSms);
                    chatTred.add(randonSms, newValue);
                } else {
                    log.debug("Обновление SMS не возможно - чат пуст");
                }
            } catch (Exception e) {
                log.warn("Ошибка: {} ", e.getMessage(), e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        };
    }

    /**
     * Метод для считывания (удаления) записи в чате
     *
     * @return Имплементация интерфейса Runnable, содержит операцию SMS чата
     */
    private Runnable read() {
        return () -> {
            try {
                Thread.sleep(30000 + new Random().nextInt(20000));
                readWriteLock.writeLock().lock();
                if (chatTred.size() > 0) {
                    log.debug("Считывание SMS записи из чата. {}", chatTred.get(chatTred.size() - 1));
                    chatTred.remove(chatTred.size() - 1);
                } else {
                    log.debug("Нет возможности считать - чат пуст.");
                }
            } catch (Exception e) {
                log.warn("Ошибка: {} ", e.getMessage(), e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        };
    }
}