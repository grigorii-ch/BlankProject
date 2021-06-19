package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.domain.entity.Supplier;
import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import com.chuprynin.epam.rd.blankproject.service.impl.SupplierService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты класса SupplierService с MockMvc
 */
@WebMvcTest(SupplierResourceImpl.class)
@RunWith(SpringRunner.class)
public class SupplierResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private SupplierService supplierService;

    /**
     * Создание поставщика
     *
     * @throws Exception - ошибка
     */
    @Test
    public void createSupplierTest() throws Exception {
        SupplierDTO dto = getSupplierDTO();
        Supplier supplier = getNewSupplier();

        when(supplierService.create(supplier)).thenReturn(supplier);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
        ).andExpect(status().isOk()).andReturn();

        String ar = result.getResponse().getContentAsString();
        String er =  mapper.writeValueAsString(dto);
        Assert.assertEquals(ar,er);
    }

    /**
     * Получение поставщика
     *
     * @throws Exception - ошибка
     */
    @Test
    public void getSupplierTest() throws Exception {
        Supplier supplier = getNewSupplier();
        SupplierDTO dto = getSupplierDTO();
        when(supplierService.findById(1)).thenReturn(supplier);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/supplier/1")).andReturn();

        String ar = result.getResponse().getContentAsString();
        String er =  mapper.writeValueAsString(dto);
        Assert.assertEquals(ar,er);
    }

    /**
     * Обновление поставщика
     *
     * @throws Exception - ошибка
     */
    @Test
    public void updateSupplierTest() throws Exception {
        SupplierDTO dto = getSupplierDTO();
        Supplier supplier = getNewSupplier();

        when(supplierService.update(supplier)).thenReturn(supplier);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put("/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
        ).andExpect(status().isOk()).andReturn();

        String ar = result.getResponse().getContentAsString();
        String er =  mapper.writeValueAsString(dto);
        Assert.assertEquals(ar,er);
    }

    /**
     * Удаление поставщика
     *
     * @throws Exception - ошибка
     */
    @Test
    public void deleteSupplierTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/supplier/1")).andExpect(status().isOk());
    }

    /**
     * Создание тестового DTO
     *
     * @return SupplierDTO
     */
    private SupplierDTO getSupplierDTO() {
        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierId(1);
        dto.setPhone("123");
        dto.setCompanyName("Name");
        return dto;
    }

    /**
     * Создание тестового Entity
     *
     * @return Supplier
     */
    private Supplier getNewSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1);
        supplier.setPhone("123");
        supplier.setCompanyName("Name");
        return supplier;
    }
}