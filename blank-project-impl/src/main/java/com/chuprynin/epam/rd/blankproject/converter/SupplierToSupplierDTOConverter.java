package com.chuprynin.epam.rd.blankproject.converter;

import com.chuprynin.epam.rd.blankproject.domain.entity.Supplier;
import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Конвертация Supplier в SupplierDTO
 */
@Component
public class SupplierToSupplierDTOConverter implements Converter<Supplier, SupplierDTO> {
    @Override
    public SupplierDTO convert(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getSupplierId());
        supplierDTO.setPhone(supplier.getPhone());
        supplierDTO.setCompanyName(supplier.getCompanyName());
        return supplierDTO;
    }
}
