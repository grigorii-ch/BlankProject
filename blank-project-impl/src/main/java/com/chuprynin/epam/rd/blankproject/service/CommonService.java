package com.chuprynin.epam.rd.blankproject.service;

import java.util.List;

/**
 * Интерфейс описывающий обшее поведение сервисов.
 *
 * @param <T>
 */
public interface CommonService<T> {
    void create(T dto);

    T findById(Integer id);

    List<T> findAll();

    void update(T dto);

    void delete(Integer id);
}
