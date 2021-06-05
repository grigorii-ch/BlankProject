package com.chuprynin.epam.rd.blankproject.repository.impl;

import com.chuprynin.epam.rd.blankproject.domain.entity.EntityDB;
import com.chuprynin.epam.rd.blankproject.repository.CrudRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Класс для работы с CRUD операциями
 */
@Slf4j
@Data
@Repository
@Profile("!local")
public class CrudRepositoryImpl implements CrudRepository {
    private Locale locale;

    @Autowired
    private EntityManagerFactory entityManager;

    /**
     * Метод добавления запись в БД
     *
     * @param entityDB - entity
     */

    public void create(EntityDB entityDB) {
        EntityManager em = entityManager.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entityDB);
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка: {}", e.getCause(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Метод Удаления записи в БД
     *
     * @param id       - PK
     */
    public void delete(Class clas, Integer id) {
        EntityManager em = entityManager.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.find(clas, id));
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка: {}", e.getCause(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Метод для поиска сущности в БД по ключу
     *
     * @param clas - тип класса
     * @param id   - PK
     * @return Optional<EntityDB>
     */
    public Optional<EntityDB> findById(Class clas, Integer id) {
        EntityManager em = entityManager.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            EntityDB result = (EntityDB) em.find(clas, id);
            transaction.commit();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            log.error("Ошибка: {}", e.getCause(), e);
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    /**
     *  Метод для поиска всех сущности в БД по типу класса
     *
     * @param clas - тип класса
     * @return
     */
    public List<EntityDB> findAll(Class clas) {
        EntityManager em = entityManager.createEntityManager();
        List customers = new ArrayList<>();
        try {
            String tableName = clas.getName().substring(clas.getName().lastIndexOf(".") + 1);
            String query = String.format("select s from %s s", tableName);
            customers = em.createQuery(query, clas).getResultList();
            log.debug("Found {} : {}", clas.getName(), customers);
            return customers;
        } catch (IllegalArgumentException e) {
            log.error("Ошибка: {}", e.getCause(), e);
            return customers;
        } finally {
            em.close();
        }
    }

    /**
     * Метод для обновления сущности в БД
     *
     * @param entityDB - entity
     */
    public void update(EntityDB entityDB) {
        EntityManager em = entityManager.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(entityDB);
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка: {}", e.getCause(), e);
        } finally {
            em.close();
        }
    }
}