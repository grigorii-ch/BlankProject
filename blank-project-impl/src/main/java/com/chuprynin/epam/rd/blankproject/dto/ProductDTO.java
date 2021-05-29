package com.chuprynin.epam.rd.blankproject.dto;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Класс для трансфера данных продукта из контроллера в сервис и обратно
 */
@Data
public class ProductDTO {
    private Integer productId;
    private String productName;
    private BigDecimal unitprice;
    private boolean isdiscontined;
    private Integer supplierId;
}
