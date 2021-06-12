package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Customer;
import com.chuprynin.epam.rd.blankproject.dto.CustomerDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация CustomerDTO в Customer
 */
@Component
public class CustomerDTOToCustomerConverter implements Converter<CustomerDTO, Customer> {
    @Override
    public Customer convert(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }
}
