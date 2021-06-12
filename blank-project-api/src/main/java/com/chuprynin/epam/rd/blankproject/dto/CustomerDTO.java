package com.chuprynin.epam.rd.blankproject.dto;

import lombok.Data;

/**
 * Класс для трансфера данных заказчика из контроллера в сервис и обратно
 */
@Data
public class CustomerDTO {
    private Integer customerId;
    private String customerName;
    private String phone;
}