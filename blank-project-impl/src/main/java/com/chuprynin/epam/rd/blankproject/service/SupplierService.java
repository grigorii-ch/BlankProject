package com.chuprynin.epam.rd.blankproject.service;

import com.chuprynin.epam.rd.blankproject.domain.entity.*;
import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.CrudRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с поставщиками
 */
@Slf4j
public class SupplierService implements CommonService<SupplierDTO> {
    private final CrudRepository repository = new CrudRepository();
    private final Class clas;

    public SupplierService() {
        this.clas = Supplier.class;
    }

    /**
     * Создание поставщика
     *
     * @param dto
     */
    public void create(SupplierDTO dto) {
        Supplier entity = new Supplier();
        entity.setPhone(dto.getPhone());
        entity.setCompanyName(dto.getCompanyName());

        repository.create(entity);
    }

    /**
     * Поиск поставщика по id
     *
     * @param id - идентификатор
     * @return - dto
     * @throws DataNotFound - данные не найдены
     */
    public SupplierDTO findById(Integer id) {
        Optional result = repository.findById(clas, id);
        if (result.isPresent()) {
            Supplier entity = (Supplier) result.get();
            return getSupplierDTO(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Supplier по ID = %s", id));
        }
    }

    /**
     * Найти всех поставщиков
     *
     * @return List<CustomerDTO>
     */
    public List<SupplierDTO> findAll() {
        return repository.findAll(clas).stream()
                .map(obj -> getSupplierDTO((Supplier) obj))
                .collect(Collectors.toList());
    }

    /**
     * Обновление поставщика
     *
     * @param dto - dto
     */
    public void update(SupplierDTO dto) throws DataNotFound {
        Optional result = repository.findById(clas, dto.getSupplierId());
        if (result.isPresent()) {
            Supplier entity = (Supplier) result.get();
            entity.setPhone(dto.getPhone());
            entity.setCompanyName(dto.getCompanyName());
            repository.update(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Supplier по ID = %s", dto.getSupplierId()));
        }
    }

    /**
     * Удаление поставщика
     *
     * @param id - идентификатор
     */
    public void delete(Integer id) {
        repository.delete(clas, id);
    }

    /**
     * Метод конвертации entity в CustomerDTO
     *
     * @param entity Supplier
     * @return SupplierDTO
     */
    private SupplierDTO getSupplierDTO(Supplier entity) {
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierId(entity.getSupplierId());
        dto.setPhone(entity.getPhone());
        dto.setCompanyName(entity.getCompanyName());
        return dto;
    }
}

