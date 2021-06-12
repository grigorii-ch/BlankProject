package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Интерфейс для ресурса /order
 */
@RequestMapping(value = "/order")
public interface OrderResource {
    @PostMapping
    OrderDTO create(@RequestBody OrderDTO order);

    @GetMapping
    List<OrderDTO> get();

    @GetMapping("/{id}")
    OrderDTO get(@PathVariable("id") String id);

    @PutMapping
    OrderDTO update(@RequestBody OrderDTO order);

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") String id);
}
