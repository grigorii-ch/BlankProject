package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для ресурса /customer
 */
@RequestMapping(value = "/customer")
public interface CustomerResource {
    @PostMapping
    CustomerDTO create(@RequestBody CustomerDTO customer);

    @GetMapping
    List<CustomerDTO> get();

    @GetMapping("/{id}")
    CustomerDTO get(@PathVariable("id") String id);

    @PutMapping
    CustomerDTO update(@RequestBody CustomerDTO customer);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id);
}
