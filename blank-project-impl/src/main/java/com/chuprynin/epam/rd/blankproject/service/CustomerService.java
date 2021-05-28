package com.chuprynin.epam.rd.blankproject.service;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.dto.CustomerDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с заказчиками
 */
public class CustomerService implements CommonService<CustomerDTO> {
    private final CrudRepository repository = new CrudRepository();
    private final Class clas;

    public CustomerService() {
        this.clas = Customer.class;
    }

    /**
     * Создание заказчика
     *
     * @param dto
     */
    public void create(CustomerDTO dto) {
        Customer entity = new Customer();
        entity.setPhone(dto.getPhone());
        entity.setCustomerName(dto.getCustomerName());
        repository.create(entity);
    }

    /**
     * Поиск заказчика по id
     *
     * @param id - идентификатор
     * @return - dto
     * @throws DataNotFound - данные не найдены
     */
    public CustomerDTO findById(Integer id) throws DataNotFound {
        Optional result = repository.findById(clas, id);
        if (result.isPresent()) {
            Customer entity = (Customer) result.get();
            return getCustomerDTO(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Customer по ID = %s", id));
        }
    }

    /**
     * Найти всех заказчиков
     *
     * @return List<CustomerDTO>
     */
    public List<CustomerDTO> findAll() {
        return repository.findAll(clas).stream()
                .map(obj -> getCustomerDTO((Customer) obj))
                .collect(Collectors.toList());
    }

    /**
     * Обновление заказчика
     *
     * @param dto - dto
     */
    public void update(CustomerDTO dto) {
        Optional result = repository.findById(clas, dto.getCustomerId());
        if (result.isPresent()) {
            Customer entity = (Customer) result.get();
            entity.setPhone(dto.getPhone());
            entity.setCustomerName(dto.getCustomerName());
            repository.update(entity);
        } else {
            throw new DataNotFound(String.format("Не найдены данные в таблице Supplier по ID = %s", dto.getCustomerId()));
        }
    }

    /**
     * Удаление заказчика
     *
     * @param id - идентификатор
     */
    public void delete(Integer id) {
        repository.delete(clas, id);
    }

    /**
     * Метод конвертации entity в CustomerDTO
     *
     * @param entity Customer
     * @return CustomerDTO
     */
    private CustomerDTO getCustomerDTO(Customer entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(entity.getCustomerId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setPhone(entity.getPhone());
        return dto;
    }
}