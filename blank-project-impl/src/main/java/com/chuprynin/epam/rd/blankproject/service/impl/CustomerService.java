package com.chuprynin.epam.rd.blankproject.service.impl;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.CustomerRepository;
import com.chuprynin.epam.rd.blankproject.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с заказчиками
 */
@Service
public class CustomerService implements CommonService<Customer> {

    @Autowired
    private CustomerRepository repository;

    /**
     * Создание заказчика
     *
     * @param entity Customer
     * @return Customer
     */
    public Customer create(Customer entity) {
        return repository.save(entity);
    }

    /**
     * Поиск заказчика по id
     *
     * @param id идентификатор
     * @return Customer
     * @throws DataNotFound
     */
    public Customer findById(Integer id) throws DataNotFound {
        Optional<Customer> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Customer по ID = %s", id));
        }
    }

    /**
     * Найти всех заказчиков
     *
     * @return List<CustomerDTO>
     */
    public List<Customer> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    /**
     * Обновление заказчика
     *
     * @param entity Customer
     * @return Customer
     */
    public Customer update(Customer entity) {
        Optional<Customer> result = repository.findById(entity.getCustomerId());
        if (result.isPresent()) {
            return repository.save(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Supplier по ID = %s", entity.getCustomerId()));
        }
    }

    /**
     * Удаление заказчика
     *
     * @param id - идентификатор
     */
    public void delete(Integer id) {
        Optional<Customer> result = repository.findById(id);
        result.ifPresent(customer -> repository.delete(customer));
    }
}