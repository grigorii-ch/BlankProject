package com.chuprynin.epam.rd.blankproject.repository;

import com.chuprynin.epam.rd.blankproject.domain.entity.EntityDB;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс описывает общие методы для CRUD опперации
 */
public interface CrudRepository {
    void create(EntityDB entityDB);

    void delete(Class clas, Integer id);

    Optional<EntityDB> findById(Class clas, Integer id);

    List<EntityDB> findAll(Class clas);

    void update(EntityDB entityDB);
}
