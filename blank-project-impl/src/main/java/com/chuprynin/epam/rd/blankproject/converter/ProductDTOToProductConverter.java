package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация ProductDTO в Product
 */
@Component
public class ProductDTOToProductConverter implements Converter<ProductDTO, Product> {

    @Override
    public Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setIsdiscontined(dto.isIsdiscontined());
        product.setUnitprice(dto.getUnitprice());


        return product;
    }
}
