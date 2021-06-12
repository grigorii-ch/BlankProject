package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.converter.CustomerDTOToCustomerConverter;
import com.chuprynin.epam.rd.blankproject.converter.CustomerToCustomerDTOConverter;
import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.dto.CustomerDTO;
import com.chuprynin.epam.rd.blankproject.service.impl.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс контроллер, для обработки запросов /customers
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerResourceImpl implements CustomerResource {
    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerDTOToCustomerConverter customerDTOToCustomerConverter;
    @Autowired
    private CustomerToCustomerDTOConverter customerToCustomerDTOConverter;

    /**
     * Создание нового покупателя
     *
     * @param customer CustomerDTO
     * @return CustomerDTO
     */
    @Override
    public CustomerDTO create(@RequestBody CustomerDTO customer) {
        customer.setCustomerId(null);
        log.debug("Создание покупателя {}", customer);
        return customerToCustomerDTOConverter.convert(
                service.create(customerDTOToCustomerConverter.convert(customer))
        );
    }

    /**
     * Получение списка всех покупателе
     *
     * @return List<CustomerDTO>
     */
    @Override
    public List<CustomerDTO> get() {
        log.debug("Получение списка всех покупателей");
        List<Customer> result = service.findAll();
        return result.stream()
                .map(obj -> customerToCustomerDTOConverter.convert(obj))
                .collect(Collectors.toList());
    }

    /**
     * Получение покупателя по идентификатору
     *
     * @param id - идентификатор
     * @return CustomerDTO
     */
    @Override
    public CustomerDTO get(@PathVariable("id") String id) {
        log.debug("Получение покупателя по идентификатору {}", id);
        CustomerDTO result = customerToCustomerDTOConverter.convert(service.findById(Integer.valueOf(id)));
        return result;
    }

    /**
     * Обновление полей покупателя
     *
     * @param dto CustomerDTO
     * @return CustomerDTO
     */
    @Override
    public CustomerDTO update(@RequestBody CustomerDTO dto) {
        log.debug("Обновление полей покупателя {}", dto);
        return customerToCustomerDTOConverter.convert(service.update(customerDTOToCustomerConverter.convert(dto)));
    }

    /**
     * Удаление покупателя по идентификатору
     *
     * @param id - идентификатор
     */
    @Override
    public void delete(@PathVariable("id") String id) {
        log.debug("Удаление покупателя с id - {}", id);
        service.delete(Integer.valueOf(id));
    }
}