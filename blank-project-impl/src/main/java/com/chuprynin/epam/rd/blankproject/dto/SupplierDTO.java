package com.chuprynin.epam.rd.blankproject.dto;

import lombok.Data;

/**
 * Класс для трансфера данных поставщика из контроллера в сервис и обратно
 */
@Data
public class SupplierDTO {
    private Integer supplierId;
    private String companyName;
    private String phone;
}
