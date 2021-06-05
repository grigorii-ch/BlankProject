package com.chuprynin.epam.rd.blankproject.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Класс для трансфера данных заказа из контроллера в сервис и обратно
 */
@Data
public class OrderDTO {
    private Integer orderId;
    private String orderNumber;
    private Date orderDate;
    private BigDecimal totalAmount;
    private Integer customerId;
    private List<Integer> products;
}
