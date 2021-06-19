package com.chuprynin.epam.rd.blankproject.resource;

import com.chuprynin.epam.rd.blankproject.domain.entity.Product;
import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import com.chuprynin.epam.rd.blankproject.service.impl.ProductService;
import com.chuprynin.epam.rd.blankproject.service.impl.SupplierService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты класса ProductService с MockMvc
 */
@WebMvcTest(ProductResourceImpl.class)
@RunWith(SpringRunner.class)
public class ProductResourceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductService productService;

    @MockBean
    private SupplierService supplierService;

    /**
     * Создание продукта
     *
     * @throws Exception - ошибка
     */
    @Test
    public void createProductTest() throws Exception {
        ProductDTO dto = getProductDTO();
        Product product = getNewProduct();

        when(productService.create(product)).thenReturn(product);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
        ).andExpect(status().isOk()).andReturn();

        String ar = result.getResponse().getContentAsString();
        String er = mapper.writeValueAsString(dto);
        System.out.println(ar);
        System.out.println(er);

        Assert.assertEquals(ar, er);
    }

    /**
     * Получение продукта
     *
     * @throws Exception - ошибка
     */
    @Test
    public void getProductTest() throws Exception {
        ProductDTO dto = getProductDTO();
        Product product = getNewProduct();
        when(productService.findById(1)).thenReturn(product);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/product/1")).andReturn();

        String ar = result.getResponse().getContentAsString();
        String er = mapper.writeValueAsString(dto);
        Assert.assertEquals(ar, er);
    }

    /**
     * Обновление продукта
     *
     * @throws Exception - ошибка
     */
    @Test
    public void updateProductTest() throws Exception {
        ProductDTO dto = getProductDTO();
        Product product = getNewProduct();

        when(productService.update(product)).thenReturn(product);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.put("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
        ).andExpect(status().isOk()).andReturn();

        String ar = result.getResponse().getContentAsString();
        String er = mapper.writeValueAsString(dto);
        Assert.assertEquals(ar, er);
    }

    /**
     * Удаление продукта
     *
     * @throws Exception - ошибка
     */
    @Test
    public void deleteProductTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/1")).andExpect(status().isOk());
    }

    /**
     * Создание тестового DTO
     *
     * @return ProductDTO
     */
    private ProductDTO getProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(1);
        dto.setProductName("Name");
        dto.setIsdiscontined(true);
        return dto;
    }

    /**
     * Создание тестового Entity
     *
     * @return Product
     */
    private Product getNewProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Name");
        product.setIsdiscontined(true);
        return product;
    }
}