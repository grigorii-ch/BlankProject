package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для ресурса /product
 */
@RequestMapping(value = "/product")
public interface ProductResource {
    @PostMapping
    ProductDTO create(@RequestBody ProductDTO product);

    @GetMapping
    List<ProductDTO> get();

    @GetMapping("/{id}")
    ProductDTO get(@PathVariable("id") String id);

    @PutMapping
    ProductDTO update(@RequestBody ProductDTO product);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id);
}
