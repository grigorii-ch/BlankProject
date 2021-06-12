package com.chuprynin.epam.rd.blankproject.service;

import com.chuprynin.epam.rd.blankproject.domain.entity.Supplier;

import java.util.List;

/**
 * Интерфейс описывающий обшее поведение сервисов.
 *
 * @param <T>
 */
public interface CommonService<T> {
    T create(T entity);

    T findById(Integer id);

    List<T> findAll();

    T update(T entity);

    void delete(Integer id);
}
