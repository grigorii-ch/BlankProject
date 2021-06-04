package com.chuprynin.epam.rd.blankproject.repository.impl.stub;

import com.chuprynin.epam.rd.blankproject.domain.entity.*;
import com.chuprynin.epam.rd.blankproject.locale.ProjectLocale;
import com.chuprynin.epam.rd.blankproject.repository.CrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.*;

/**
 * Класс для работы с CRUD операциями (Стаба)
 */
@Slf4j
@Repository
@Profile("local")
public class CrudRepositoryStub implements CrudRepository {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ProjectLocale projectLocale;

    /**
     * Метод добавления запись в БД
     *
     * @param entityDB - entity
     */
    public void create(EntityDB entityDB) {
        log.debug("Create {}", entityDB);
    }

    /**
     * Метод Удаления записи в БД
     *
     * @param id - PK
     */
    public void delete(Class clas, Integer id) {
        log.debug("Удаление записи из таблицы {} с id = {}", clas.getName().substring(clas.getName().lastIndexOf(".") + 1), id);
    }

    /**
     * Метод для поиска сущности в БД по ключу
     *
     * @param clas - тип класса
     * @param id   - PK
     * @return Optional<EntityDB>
     */
    public Optional<EntityDB> findById(Class clas, Integer id) {

        return Optional.ofNullable((EntityDB) fillStubData().get(clas));
    }

    /**
     * Метод для поиска всех сущности в БД по типу класса
     *
     * @param clas - тип класса
     * @return
     */
    public List<EntityDB> findAll(Class clas) {
        return List.of((EntityDB) fillStubData().get(clas));
    }

    /**
     * Метод для обновления сущности в БД
     *
     * @param entityDB - entity
     */
    public void update(EntityDB entityDB) {
        log.debug("Обновление записи {}", entityDB);
    }

    /**
     * Генерация данный для возвращения захардкоженных значений
     * @return
     */
    public Map fillStubData() {

        Map stubData = new HashMap<Class, EntityDB>();

        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName(messageSource.getMessage("customer_name", null, "default msg", projectLocale.getLocale()));
        customer.setPhone("87-77-88");

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderNumber("51");
        order.setTotalAmount(new BigDecimal("77.7"));

        Product product = new Product();
        product.setProductName(messageSource.getMessage("product_name", null, "", projectLocale.getLocale()));
        product.setIsdiscontined(true);
        product.setUnitprice(new BigDecimal("99.90"));

        Supplier supplier = new Supplier();
        supplier.setCompanyName(messageSource.getMessage("company_name", null, "", projectLocale.getLocale()));
        supplier.setPhone("11-11-15");

        stubData.put(Customer.class, customer);
        stubData.put(Order.class, order);
        stubData.put(Product.class, product);
        stubData.put(Supplier.class, supplier);

        return stubData;
    }

}