package com.chuprynin.epam.rd.blankproject;

import com.chuprynin.epam.rd.blankproject.domain.entity.*;
import com.chuprynin.epam.rd.blankproject.service.CrudService;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

/**
 * Класс для отображения работы CRUD операций
 */
@Slf4j
public class CrudView {
    private final CrudService service = new CrudService();
    private final int ID_FOR_TEST = 1;

    /**
     * Конструктор, при вызове заполняет таблицы в бд тестовым данными
     */
    public CrudView() {
        log.info("Заполнение таблиц тестовыми данными");
        addCustomer();
        addSupplier();
    }

    /**
     * Метод для запуска демонстрации CRUD операций
     */
    public void run() {
        log.info("Проверка CRUD операций");
        log.debug("Проверка сгенерированных данных таблиц Customer и Order");
        log.info("Проверка CRUD операций - find");
        Optional<EntityDB> customerResult = service.findById(Customer.class, ID_FOR_TEST);

        if (customerResult.isPresent()) {
            Customer customer = (Customer) customerResult.get();
            log.debug("Результат выборки связанных данных, таблиц Customer и Order с сustomerId = {} : {}", customer, ID_FOR_TEST);
            log.debug("Текущий номер телефона {} - {} ", customer.getCustomerName(), customer.getPhone());
            customer.setPhone("777");
            log.debug("Изменение номера телефона для {}", customer.getCustomerName());
            log.info("Проверка CRUD операций - update");
            service.update(customer);
            Optional<EntityDB> customerRefresh = service.findById(Customer.class, ID_FOR_TEST);
            if (customerRefresh.isPresent()) {
                customer = (Customer) customerRefresh.get();
                log.debug("Новый номер телефона {} - {} ", customer.getCustomerName(), customer.getPhone());
            }
            log.debug("Удаление customer с id = {}", ID_FOR_TEST);
            log.info("Проверка CRUD операций - delete");
            service.delete(customer, ID_FOR_TEST);
        } else {
            log.debug("customer с id = {} не найден", ID_FOR_TEST);
        }

        log.debug("Проверка сгенерированных данных таблиц Customer и Order");
        Optional<EntityDB> supplierResult = service.findById(Supplier.class, ID_FOR_TEST);
        if (supplierResult.isPresent()) {
            Supplier supplier = (Supplier) supplierResult.get();
            log.debug("Результат выборки связанных данных, таблиц Supplier и Product с supplierId = {} : {}", supplier, ID_FOR_TEST);
            log.debug("Удаление supplier с id = {}", ID_FOR_TEST);
            service.delete(supplier, ID_FOR_TEST);
        } else {
            log.debug("supplier с id = {}} не найден", ID_FOR_TEST);
        }
    }

    /**
     * Метод заполнения таблиц Customer и Order тестовыми, связанными данными
     */
    private void addCustomer() {
        log.info("Заполнение таблиц Customer и Order");
        Order firstOrder = new Order();
        firstOrder.setOrderNumber("100");
        firstOrder.setOrderDate(new Date());
        firstOrder.setTotalAmount(new BigDecimal("123.45"));
        Order secondOrder = new Order();
        secondOrder.setOrderNumber("200");
        secondOrder.setOrderDate(new Date());
        secondOrder.setTotalAmount(new BigDecimal("678.90"));
        Customer customer = new Customer();
        customer.setCustomerName("Coca-Cola");
        customer.setPhone("8 (846) 379-27-80");
        firstOrder.setCustomer(customer);
        secondOrder.setCustomer(customer);
        List<Order> orders = Arrays.asList(firstOrder, secondOrder);
        customer.setOrders(orders);
        service.create(customer);
    }

    /**
     * Метод заполнения таблиц Supplier и Product тестовыми, связанными данными
     */
    private void addSupplier() {
        log.info("Заполнение таблиц Supplier и Product");
        Product firstProduct = new Product();
        firstProduct.setProductName("CocaColla");
        firstProduct.setUnitprice(new BigDecimal("89.99"));
        firstProduct.setIsdiscontined(true);
        Product seconfProduct = new Product();
        seconfProduct.setProductName("Fanta");
        seconfProduct.setUnitprice(new BigDecimal("150.01"));
        seconfProduct.setIsdiscontined(false);
        Supplier supplier = new Supplier();
        supplier.setCompanyName("Lenta");
        supplier.setPhone("8 (800) 700-41-11");
        firstProduct.setSupplier(supplier);
        seconfProduct.setSupplier(supplier);
        List<Product> products = Arrays.asList(firstProduct, seconfProduct);
        supplier.setProducts(products);
        service.create(supplier);
    }
}