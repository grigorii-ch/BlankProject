package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.converter.ProductDTOToProductConverter;
import com.chuprynin.epam.rd.blankproject.converter.ProductToProductDTOConverter;
import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import com.chuprynin.epam.rd.blankproject.service.impl.ProductService;
import com.chuprynin.epam.rd.blankproject.service.impl.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс контроллер, для обработки запросов /products
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductResourceImpl implements ProductResource {
    @Autowired
    private ProductService service;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductDTOToProductConverter productDTOToProductConverter;
    @Autowired
    private ProductToProductDTOConverter productToProductDTOConverter;

    /**
     * Создание нового продукта
     *
     * @param product ProductDTO
     * @return ProductDTO
     */
    @Override
    public ProductDTO create(@RequestBody ProductDTO product) {
        product.setProductId(null);
        log.debug("Создание нового продукта");
        return productToProductDTOConverter.convert(service.create(getProductEntity(product)));
    }

    /**
     * Получение списка всех продуктов
     *
     * @return List<ProductDTO>
     */
    @Override
    public List<ProductDTO> get() {
        log.debug("Получение списка всех продуктов");
        return service.findAll().stream()
                .map(obj -> productToProductDTOConverter.convert(obj))
                .collect(Collectors.toList());
    }

    /**
     * Получение продукт по идентификатору
     *
     * @param id идентификатор
     * @return ProductDTO
     */
    @Override
    public ProductDTO get(String id) {
        log.debug("Получение продукт по ID {}", id);
        return productToProductDTOConverter.convert(
                service.findById(Integer.valueOf(id))
        );
    }

    /**
     * Обновление продукта
     *
     * @param product ProductDTO
     * @return ProductDTO
     */
    @Override
    public ProductDTO update(@RequestBody ProductDTO product) {
        log.debug("Обновление продукта");
        return productToProductDTOConverter.convert(
                service.update(getProductEntity(product))
        );
    }

    /**
     * Удаление продукт по идентификатору
     *
     * @param id идентификатор
     */
    @Override
    public void delete(String id) {
        log.debug("Удаление продукт по id - {}", id);
        service.delete(Integer.valueOf(id));
    }

    /**
     * Формирование сущности product
     *
     * @param product ProductDTO
     * @return Product
     */
    private Product getProductEntity(ProductDTO product) {
        Product entity = productDTOToProductConverter.convert(product);
        if (product.getSupplierId() != null) {
            entity.setSupplier(supplierService.findById(product.getSupplierId()));
        }
        return entity;
    }
}