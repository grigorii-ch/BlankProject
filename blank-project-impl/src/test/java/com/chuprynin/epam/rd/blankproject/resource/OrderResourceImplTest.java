package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.OrderRepository;
import com.chuprynin.epam.rd.blankproject.service.impl.OrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Тесты класса OrderResourceImpl с H2 db
 */
@DataJpaTest
@RunWith(SpringRunner.class)
class OrderResourceImplTest {

    @Autowired
    private OrderService orderService;

    /**
     * Создание заказа
     */
    @Test
    public void createOrderTest() {
        Order order = new Order();
        orderService.create(order);
        Order savedOrder = orderService.findById(order.getOrderId());
        Assert.assertEquals(savedOrder.getOrderId(), order.getOrderId());

    }

    /**
     * Получение заказа
     */
    @Test
    public void getOrderTest() {
        Order order = new Order();
        orderService.create(order);
        Order savedOrder = orderService.findById(order.getOrderId());
        Assert.assertEquals(savedOrder.getOrderId(), order.getOrderId());
    }

    /**
     * Обновление заказа
     */
    @Test
    public void updateOrderTest() {
        Order order = new Order();
        orderService.create(order);
        order.setOrderNumber("NewName");
        orderService.update(order);
        Order savedOrder = orderService.findById(order.getOrderId());
        Assert.assertEquals(savedOrder.getOrderId(), order.getOrderId());
    }

    /**
     * Удаление заказа
     */
    @Test
    public void deleteOrderTest() {
        Order order = new Order();
        orderService.create(order);
        orderService.delete(order.getOrderId());
        Assertions.assertThrows(DataNotFound.class, () -> {
            orderService.findById(order.getOrderId());
        });
    }

    /**
     * Конфигурация для поднятия бина customerService
     */
    @TestConfiguration
    protected static class MyOrderConfiguration {
        @Bean
        public OrderService orderService(OrderRepository repository) {
            return new OrderService(repository);
        }
    }
}