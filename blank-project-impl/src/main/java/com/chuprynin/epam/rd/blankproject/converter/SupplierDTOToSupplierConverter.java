package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Supplier;
import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация SupplierDTO в Supplier
 */
@Component
public class SupplierDTOToSupplierConverter implements Converter<SupplierDTO, Supplier> {
    @Override
    public Supplier convert(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(dto.getSupplierId());
        supplier.setPhone(dto.getPhone());
        supplier.setCompanyName(dto.getCompanyName());
        return supplier;
    }
}
