package com.chuprynin.epam.rd.blankproject.service.impl;

import com.chuprynin.epam.rd.blankproject.annotation.Logging;
import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.ProductRepository;
import com.chuprynin.epam.rd.blankproject.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с продуктом
 */
@Service
public class ProductService implements CommonService<Product> {
    private static final String ERR_MESSAGE = "Не найдены данные в таблице Product по ID = %s";

    @Autowired
    private ProductRepository repository;

    /**
     * Создание продукта
     *
     * @param product Product
     * @return Product
     * @throws DataNotFound данные не найдены
     */
    public Product create(Product product) throws DataNotFound {
        return repository.save(product);
    }

    /**
     * Поиск продукта по id
     *
     * @param id - идентификатор
     * @return - Product
     * @throws DataNotFound данные не найдены
     */
    @Logging
    public Product findById(Integer id) {
        Optional<Product> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new DataNotFound(String.format(ERR_MESSAGE, id));
        }
    }

    /**
     * Найти всё продукты
     *
     * @return List<ProductDTO>
     */
    public List<Product> findAll() {
        return repository.findAll();
    }

    /**
     * Обновление заказчика
     * @param product - идентификатор
     * @return - Product
     */
    public Product update(Product product) {
        Optional<Product> result = repository.findById(product.getProductId());
        if (result.isPresent()) {
            return repository.save(product);
        } else {
            throw new DataNotFound(String.format(ERR_MESSAGE, product.getProductId()));
        }
    }

    /**
     * Удаление продукта
     *
     * @param id идентификатор
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}