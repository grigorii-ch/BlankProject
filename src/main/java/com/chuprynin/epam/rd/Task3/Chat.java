package com.chuprynin.epam.rd.Task3;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс имитирует работу SMS часа ёмкостью 100 операций
 */
@Slf4j
@Data
public class Chat {
    private List<String> chatTred = new ArrayList<>();
    private List<Runnable> chatActions = new ArrayList<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * Максимально кол-во сообщений в чате
     */
    int maxChatSize = 25;
    /**
     * Кол-во операций
     */
    int maxActionSize = 100;

    /**
     * Метод для запуска
     */
    public void run() {
        imitationChat();
        chatActions.forEach(elem -> {
            Thread th = new Thread(elem);
            th.start();
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
     * @return Имплементация интерфейса Runnable, содержит операцию SMS чата
     */
    private Runnable write() {
        return () -> {
            readWriteLock.writeLock().lock();
            try {
                if (chatTred.size() < maxChatSize) {
                    Thread.sleep(20 + new Random().nextInt(40));
                    String newSms = UUID.randomUUID().toString();
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
     * @return Имплементация интерфейса Runnable, содержит операцию SMS чата
     */
    private Runnable update() {
        return () -> {
            readWriteLock.writeLock().lock();
            try {
                if (chatTred.size() > 0) {
                    int randonSms = new Random().nextInt(chatTred.size());
                    String tmp = chatTred.get(randonSms);
                    String newValue = tmp + " changed";
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
     * @return Имплементация интерфейса Runnable, содержит операцию SMS чата
     */
    private Runnable read() {
        return () -> {
            readWriteLock.writeLock().lock();
            try {
                Thread.sleep(30 + new Random().nextInt(20));
                if (chatTred.size() > 0) {
                    log.debug("Счиьывание SMS записи из чата. {}",chatTred.get(chatTred.size() - 1));
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