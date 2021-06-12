package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация Customer в CustomerDTO
 */
@Component
public class CustomerToCustomerDTOConverter implements Converter<Customer, CustomerDTO> {
    @Override
    public CustomerDTO convert(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        dto.setCustomerName(customer.getCustomerName());
        dto.setPhone(customer.getPhone());
        return dto;
    }
}
