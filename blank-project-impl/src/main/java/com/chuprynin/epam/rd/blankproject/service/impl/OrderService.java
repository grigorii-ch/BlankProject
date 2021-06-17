package com.chuprynin.epam.rd.blankproject.service.impl;

import com.chuprynin.epam.rd.blankproject.annotation.Logging;
import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.OrderRepository;
import com.chuprynin.epam.rd.blankproject.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с заказами
 */
@Service
public class OrderService implements CommonService<Order> {
    private static final String ERR_MESSAGE = "Не найдены данные в таблице Order по ID = %s";

    @Autowired
    private OrderRepository repository;

    /**
     * Создание заказа
     *
     * @param order Order
     * @return Order
     */
    public Order create(Order order) {
        return repository.save(order);
    }

    /**
     * Поиск заказа по id
     *
     * @param id - идентификатор
     * @return - dto
     */
    @Logging
    public Order findById(Integer id) {
        Optional<Order> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new DataNotFound(String.format(ERR_MESSAGE, id));
        }
    }

    /**
     * Найти все заказы
     *
     * @return List<OrderDTO>
     */
    public List<Order> findAll() {
        return repository.findAll();
    }

    /**
     * Обновление заказа
     *
     * @param order Order
     * @return List<OrderDTO>
     */
    public Order update(Order order) {
        Optional<Order> result = repository.findById(order.getOrderId());
        if (result.isPresent()) {
            return repository.save(order);
        } else {
            throw new DataNotFound(String.format(ERR_MESSAGE, order.getOrderId()));
        }
    }

    /**
     * Удаление заказа
     *
     * @param id идентификатор
     */
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}