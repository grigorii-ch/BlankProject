package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import com.chuprynin.epam.rd.blankproject.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация OrderDTO в Order
 */
@Component
public class OrderDTOToOrderConverter implements Converter<OrderDTO, Order> {

    @Override
    public Order convert(OrderDTO dto) {
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setOrderDate(dto.getOrderDate());
        order.setOrderNumber(dto.getOrderNumber());
        order.setTotalAmount(dto.getTotalAmount());

        return order;
    }
}
