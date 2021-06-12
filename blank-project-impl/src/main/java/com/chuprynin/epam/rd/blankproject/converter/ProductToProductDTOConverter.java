package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация Product в ProductDTO
 */
@Component
public class ProductToProductDTOConverter implements Converter<Product, ProductDTO> {
    @Override
    public ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setIsdiscontined(product.isIsdiscontined());
        dto.setUnitprice(product.getUnitprice());
        if (product.getSupplier() != null) {
            dto.setSupplierId(product.getSupplier().getSupplierId());
        }
        return dto;
    }
}
