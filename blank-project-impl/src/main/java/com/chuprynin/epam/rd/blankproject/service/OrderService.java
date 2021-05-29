package com.chuprynin.epam.rd.blankproject.service;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.domain.entity.EntityDB;
import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.dto.OrderDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.CrudRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для работы с заказами
 */
public class OrderService implements CommonService<OrderDTO> {
    private final CrudRepository repository = new CrudRepository();
    private final Class clas;

    public OrderService() {
        this.clas = Order.class;
    }

    /**
     * Создание заказа
     *
     * @param dto
     */
    public void create(OrderDTO dto) {
        Order entity = new Order();
        entity.setOrderDate(dto.getOrderDate());
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setOrderId(dto.getOrderId());
        Optional<EntityDB> optionalCustomer = repository.findById(Customer.class, dto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            Customer customer = (Customer) optionalCustomer.get();
            entity.setCustomer(customer);
        } else {
            throw new DataNotFound(
                    String.format(
                            "При операции создания заказа не был найден Заказчик c идентификатором = %s",
                            dto.getCustomerId()
                    )
            );
        }

        Set<Product> products = new HashSet<>();
        dto.getProducts().forEach(productId -> {
            Optional<EntityDB> optionalProduct = repository.findById(Product.class, productId);
            if (optionalProduct.isPresent()) {
                Product product = (Product) optionalProduct.get();
                products.add(product);

            } else {
                throw new DataNotFound(
                        String.format(
                                "При операции создания заказа не был найден продукт c идентификатором = %s",
                                dto.getCustomerId()
                        )
                );
            }
        });
        entity.setProducts(products);
        repository.create(entity);
    }

    /**
     * Поиск заказа по id
     *
     * @param id - идентификатор
     * @return - dto
     */
    public OrderDTO findById(Integer id) {
        Optional result = repository.findById(clas, id);
        if (result.isPresent()) {
            Order entity = (Order) result.get();
            return getOrderDTO(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Order по ID = %s", id));
        }
    }

    /**
     * Найти всех заказчиков
     *
     * @return List<OrderDTO>
     */
    public List<OrderDTO> findAll() {
        return repository.findAll(clas).stream()
                .map(obj -> getOrderDTO((Order) obj))
                .collect(Collectors.toList());
    }

    /**
     * Обновление заказа
     *
     * @param dto - dto
     */
    public void update(OrderDTO dto) {
        Optional result = repository.findById(clas, dto.getOrderId());
        if (result.isPresent()) {
            Order entity = (Order) result.get();
            entity.setOrderId(dto.getOrderId());
            entity.setOrderDate(dto.getOrderDate());
            entity.setOrderNumber(dto.getOrderNumber());
            entity.setTotalAmount(dto.getTotalAmount());
            repository.update(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Order по ID = %s", dto.getOrderId()));
        }
    }

    /**
     * Удаление заказа
     *
     * @param id
     */
    public void delete(Integer id) {
        repository.delete(clas, id);
    }

    /**
     * Метод конвертации entity в OrderDTO
     *
     * @param entity - Order
     * @return - OrderDTO
     */
    private OrderDTO getOrderDTO(Order entity) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setOrderDate(entity.getOrderDate());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setTotalAmount(entity.getTotalAmount());
        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getCustomerId());
        }
        return dto;
    }

}