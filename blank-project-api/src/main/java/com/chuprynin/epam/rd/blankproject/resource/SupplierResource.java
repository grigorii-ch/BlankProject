package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для ресурса /supplier
 */
@RequestMapping(value = "/supplier")
public interface SupplierResource {
    @PostMapping
    SupplierDTO create(@RequestBody SupplierDTO supplier);

    @GetMapping
    List<SupplierDTO> get();

    @GetMapping("/{id}")
    SupplierDTO get(@PathVariable("id") String id);

    @PutMapping
    SupplierDTO update(@RequestBody SupplierDTO supplier);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id);
}
