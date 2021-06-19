package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.CustomerRepository;
import com.chuprynin.epam.rd.blankproject.service.impl.CustomerService;
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
 * Тесты класса CustomerResourceImpl с H2 db
 */
@DataJpaTest
@RunWith(SpringRunner.class)
class CustomerResourceImplTest {

    @Autowired
    private CustomerService customerService;

    /**
     * Создание кастомера
     */
    @Test
    public void createCustomerTest() {
        Customer customer = new Customer();
        customerService.create(customer);
        Customer savedCustomer = customerService.findById(customer.getCustomerId());
        Assert.assertEquals(savedCustomer.getCustomerId(), customer.getCustomerId());
    }

    /**
     * Получение кастомера
     */
    @Test
    public void getCustomerTest() {
        Customer customer = new Customer();
        customerService.create(customer);
        Customer savedCustomer = customerService.findById(customer.getCustomerId());
        Assert.assertEquals(savedCustomer.getCustomerId(), customer.getCustomerId());
    }

    /**
     * Обновление кастомера
     */
    @Test
    public void updateCustomerTest() {
        Customer customer = new Customer();
        customerService.create(customer);
        customer.setCustomerName("NewName");
        customerService.update(customer);
        Customer savedCustomer = customerService.findById(customer.getCustomerId());
        Assert.assertEquals(savedCustomer.getCustomerName(), customer.getCustomerName());
    }

    /**
     * Удаление кастомера
     */
    @Test
    public void deleteCustomerTest() {
        Customer customer = new Customer();
        customerService.create(customer);
        customerService.delete(customer.getCustomerId());
        Assertions.assertThrows(DataNotFound.class, () -> {
            customerService.findById(customer.getCustomerId());
        });
    }

    /**
     * Конфигурация для поднятия бина customerService
     */
    @TestConfiguration
    protected static class MyCustomerConfiguration {
        @Bean
        public CustomerService customerService(CustomerRepository repository) {
            return new CustomerService(repository);
        }
    }
}