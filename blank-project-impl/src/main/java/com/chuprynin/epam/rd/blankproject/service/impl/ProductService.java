package com.chuprynin.epam.rd.blankproject.service.impl;

import com.chuprynin.epam.rd.blankproject.domain.entity.EntityDB;
import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.domain.entity.Supplier;
import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.CrudRepository;
import com.chuprynin.epam.rd.blankproject.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с продуктом
 */
@Service
public class ProductService implements CommonService<ProductDTO> {
    @Autowired
    private CrudRepository repository;
    private final Class clas;

    public ProductService() {
        this.clas = Product.class;
    }

    /**
     * Создание продукта
     *
     * @param dto
     */
    public void create(ProductDTO dto) throws DataNotFound {
        Product entity = new Product();
        entity.setProductName(dto.getProductName());
        entity.setIsdiscontined(dto.isIsdiscontined());
        entity.setIsdiscontined(dto.isIsdiscontined());
        entity.setProductId(dto.getProductId());
        Optional<EntityDB> optionalSupplier = repository.findById(Supplier.class, dto.getSupplierId());
        if (optionalSupplier.isPresent()) {
            Supplier supplier = (Supplier) optionalSupplier.get();
            entity.setSupplier(supplier);
        } else {
            throw new DataNotFound(
                    String.format(
                            "При операции создания заказа не был найден Заказчик c идентификатором = %s",
                            dto.getSupplierId()
                    )
            );
        }
        repository.create(entity);
    }

    /**
     * Поиск продукта по id
     *
     * @param id - идентификатор
     * @return - dto
     * @throws DataNotFound
     */
    public ProductDTO findById(Integer id) {
        Optional result = repository.findById(clas, id);
        if (result.isPresent()) {
            Product entity = (Product) result.get();
            return getProductDTO(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Product по ID = %s", id));
        }
    }

    /**
     * Найти всё продукты
     *
     * @return List<ProductDTO>
     */
    public List<ProductDTO> findAll() {
        return repository.findAll(clas).stream()
                .map(obj -> getProductDTO((Product) obj))
                .collect(Collectors.toList());
    }

    /**
     * Обновление заказчика
     *
     * @param dto - dto
     */
    public void update(ProductDTO dto) {
        Optional result = repository.findById(clas, dto.getProductId());
        if (result.isPresent()) {
            Product entity = (Product) result.get();
            entity.setUnitprice(dto.getUnitprice());
            entity.setProductName(dto.getProductName());
            entity.setIsdiscontined(dto.isIsdiscontined());
            entity.setProductId(dto.getProductId());
            repository.update(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Product по ID = %s", dto.getProductId()));
        }
    }

    /**
     * Удаление продукта
     *
     * @param id
     */
    public void delete(Integer id) {
        repository.delete(clas, id);
    }

    /**
     * Метод конвертации entity в ProductDTO
     *
     * @param entity - Product
     * @return - ProductDTO
     */
    private ProductDTO getProductDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setProductName(entity.getProductName());
        dto.setProductId(entity.getProductId());
        dto.setIsdiscontined(entity.isIsdiscontined());
        dto.setUnitprice(entity.getUnitprice());
        if (entity.getSupplier() != null) {
            dto.setSupplierId(entity.getSupplier().getSupplierId());
        }
        return dto;
    }
}