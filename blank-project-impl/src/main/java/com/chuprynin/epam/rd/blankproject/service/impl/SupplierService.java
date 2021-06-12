package com.chuprynin.epam.rd.blankproject.service.impl;

import com.chuprynin.epam.rd.blankproject.domain.entity.*;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.SupplierRepository;
import com.chuprynin.epam.rd.blankproject.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с поставщиками
 */
@Slf4j
@Service
public class SupplierService implements CommonService<Supplier> {
    private static final String ERR_MESSAGE = "Не найдены данные в таблице Supplier по ID = %s";

    @Autowired
    private SupplierRepository repository;

    /**
     * Создание поставщика
     *
     * @param entity Supplier
     * @return Supplier
     */
    public Supplier create(Supplier entity) {
        return repository.save(entity);
    }

    /**
     * Поиск поставщика по id
     *
     * @param id - идентификатор
     * @return - Supplier
     * @throws DataNotFound - данные не найдены
     */
    public Supplier findById(Integer id) {
        Optional<Supplier> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new DataNotFound(String.format(ERR_MESSAGE, id));
        }
    }

    /**
     * Найти всех поставщиков
     *
     * @return List<CustomerDTO>
     */
    public List<Supplier> findAll() {
        return repository.findAll();
    }

    /**
     * Обновление поставщика
     *
     * @param supplier - Supplier
     * @return Supplier
     */
    public Supplier update(Supplier supplier) throws DataNotFound {
        Optional<Supplier> result = repository.findById(supplier.getSupplierId());
        if (result.isPresent()) {
            return repository.save(supplier);
        } else {
            throw new DataNotFound(String.format(ERR_MESSAGE, supplier.getSupplierId()));
        }
    }

    /**
     * Удаление поставщика
     *
     * @param id - идентификатор
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }

}

