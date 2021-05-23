package com.chuprynin.epam.rd.blankproject.service;

import com.chuprynin.epam.rd.blankproject.domain.entity.EntityDB;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Optional;

/**
 * Класс для работы с CRUD операциями
 */
@Slf4j
public class CrudService {
    EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("blankProjectPersistenceUnit");

    /**
     * Метод добавления запись в БД
     * @param entityDB - entity
     */
    public void create(EntityDB entityDB){
        EntityManager em =  entityManager.createEntityManager();
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
     * @param entityDB - entity
     * @param id - PK
     */
    public void delete(EntityDB entityDB, Integer id) {
        EntityManager em =  entityManager.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            em.remove(em.find(entityDB.getClass(), id));
            transaction.commit();
        } catch (Exception e) {
            log.error("Ошибка: {}", e.getCause(), e);
        } finally {
            em.close();
        }
    }

    /**
     * Метод для поиска сущности в БД по ключу
     * @param clas - тип класса
     * @param id - PK
     * @return Optional<EntityDB>
     */
    public Optional<EntityDB> findById(Class clas, Integer id) {
        EntityManager em =  entityManager.createEntityManager();
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
     * Метод для обновления сущности в БД
     * @param entityDB - entity
     */
    public void update(EntityDB entityDB) {
        EntityManager em =  entityManager.createEntityManager();
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