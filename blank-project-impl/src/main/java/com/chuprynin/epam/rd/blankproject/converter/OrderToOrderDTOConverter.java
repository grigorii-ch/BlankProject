package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Order;
import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Конвертация Order в OrderDTO
 */
@Component
public class OrderToOrderDTOConverter implements Converter<Order, OrderDTO> {
    @Override
    public OrderDTO convert(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setTotalAmount(order.getTotalAmount());
        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getCustomerId());
        }
        if (order.getProducts().size() > 0) {
            dto.setProducts(order.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()));
        }
        return dto;
    }
}
