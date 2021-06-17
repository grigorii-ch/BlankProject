package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.converter.SupplierDTOToSupplierConverter;
import com.chuprynin.epam.rd.blankproject.converter.SupplierToSupplierDTOConverter;
import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import com.chuprynin.epam.rd.blankproject.service.impl.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс контроллер, для обработки запросов /supplier
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SupplierResourceImpl implements SupplierResource {
    @Autowired
    private SupplierService service;
    @Autowired
    private SupplierDTOToSupplierConverter supplierDTOToSupplierConverter;
    @Autowired
    private SupplierToSupplierDTOConverter supplierToSupplierDTOConverter;

    /**
     * Создание нового поставщика
     *
     * @param supplier SupplierDTO
     * @return SupplierDTO
     */
    @Override
    public SupplierDTO create(@RequestBody SupplierDTO supplier) {
        log.debug("Создание нового поставщика");
        return supplierToSupplierDTOConverter.convert(
                service.create(supplierDTOToSupplierConverter.convert(supplier))
        );
    }

    /**
     * Получение списка всех поставщиков
     *
     * @return List<SupplierDTO>
     */
    @Override
    public List<SupplierDTO> get() {
        log.debug("Получение списка всех поставщиков");
        return service.findAll().stream()
                .map(obj -> supplierToSupplierDTOConverter.convert(obj))
                .collect(Collectors.toList());
    }

    /**
     * Получение поставщика по идентификатору
     *
     * @param id идентификатор
     * @return SupplierDTO
     */
    @Override
    public SupplierDTO get(String id) {
        log.debug("Получение поставщика по идентификатору");
        return supplierToSupplierDTOConverter.convert(
                service.findById(Integer.valueOf(id))
        );
    }

    /**
     * Обновление поставщика
     *
     * @param supplier SupplierDTO
     * @return SupplierDTO
     */
    @Override
    public SupplierDTO update(@RequestBody SupplierDTO supplier) {
        log.debug("Обновление поставщика {}", supplier);
        return supplierToSupplierDTOConverter.convert(
                service.create(supplierDTOToSupplierConverter.convert(supplier))
        );
    }

    /**
     * Удаление поставщика по идентификатору
     *
     * @param id идентификатор
     */
    @Override
    public void delete(String id) {
        log.debug("Удаление поставщика с id - {}", id);
        service.delete(Integer.valueOf(id));
    }
}