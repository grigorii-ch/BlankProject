package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.converter.OrderDTOToOrderConverter;
import com.chuprynin.epam.rd.blankproject.converter.OrderToOrderDTOConverter;
import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import com.chuprynin.epam.rd.blankproject.dto.OrderDTO;
import com.chuprynin.epam.rd.blankproject.service.impl.CustomerService;
import com.chuprynin.epam.rd.blankproject.service.impl.OrderService;
import com.chuprynin.epam.rd.blankproject.service.impl.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс контроллер, для обработки запросов /orders
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderResourceImpl implements OrderResource {
    @Autowired
    private OrderService service;
    @Autowired
    private OrderDTOToOrderConverter orderDTOToOrderConverter;
    @Autowired
    private OrderToOrderDTOConverter orderToOrderDTOConverter;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    /**
     * Создание нового заказа
     *
     * @param order OrderDTO
     * @return OrderDTO
     */
    @Override
    public OrderDTO create(@RequestBody OrderDTO order) {
        order.setOrderId(null);
        log.debug("Создание нового заказа {}", order);
        return orderToOrderDTOConverter.convert(getOrderEntity(order));
    }

    /**
     * Получение списка всех заказов
     *
     * @return List<OrderDTO>
     */
    @Override
    public List<OrderDTO> get() {
        log.debug("Получение списка всех заказов");
        return service.findAll().stream()
                .map(obj -> orderToOrderDTOConverter.convert(obj))
                .collect(Collectors.toList());
    }

    /**
     * Получение заказа по идентификатору
     *
     * @param id идентификатор
     * @return
     */
    @Override
    public OrderDTO get(String id) {
        log.debug("Получение заказа по id");
        return orderToOrderDTOConverter.convert(service.findById(Integer.valueOf(id)));

    }

    /**
     * Обновление заказа
     *
     * @param order OrderDTO
     * @return OrderDTO
     */
    @Override
    public OrderDTO update(@RequestBody OrderDTO order) {
        log.debug("Обновление заказа {}", order);
        return orderToOrderDTOConverter.convert(service.update(getOrderEntity(order)));
    }

    /**
     * Удаление заказа по идентификатору
     *
     * @param id идентификатор
     */
    @Override
    public void delete(String id) {
        log.debug("Удаление заказа с id - {}", id);
        service.delete(Integer.valueOf(id));
    }

    /**
     * Формирование сущности order
     *
     * @param order OrderDTO
     * @return Order
     */
    private Order getOrderEntity(OrderDTO order) {
        Order entity = service.create(orderDTOToOrderConverter.convert(order));
        if (order.getCustomerId() != null) {
            entity.setCustomer(customerService.findById(order.getCustomerId()));
        }
        if (order.getProducts() != null) {
            entity.setProducts(order.getProducts().stream()
                    .map(obj -> productService.findById(obj))
                    .collect(Collectors.toSet()));
        }
        return entity;
    }
}