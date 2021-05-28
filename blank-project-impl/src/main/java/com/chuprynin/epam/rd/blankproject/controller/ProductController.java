package com.chuprynin.epam.rd.blankproject.controller;

import com.chuprynin.epam.rd.blankproject.dto.ProductDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.service.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс контроллер, для обработки запросов /products
 */
@Slf4j
public class ProductController  extends HttpServlet {
    private final ProductService service;
    private final ObjectMapper mapper;

    public ProductController() {
        this.service = new ProductService();
        this.mapper = new ObjectMapper();
    }

    /**
     * Создание нового товара
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Создание нового продукта");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            ProductDTO product = getPeoductDTO(req);
            service.create(product);
            log.debug("Продукт успешно создан {}", product);
            out.println("Продукт успешно создан");
        } catch (JsonParseException | JsonMappingException | DataNotFound e) {
            log.error("Ошибка: {}", e.getMessage(), e);
            resp.getWriter().format("Ошибка %s", e.getMessage());
        }
    }

    /**
     * Получение списка всех продуктов
     * GET /products
     * Получение продукта по уникальному идентификатору
     * GET /products?id={id}
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id != null) {
            log.info("Получение продукта");
            try {
                var result = service.findById(Integer.valueOf(id));
                log.debug("Полученый продукт {}", result);
                out.println(mapper.writeValueAsString(result));
            } catch (DataNotFound e) {
                log.error("Ошибка: {}", e.getMessage(), e);
                out.println(String.format("Ошибка: %s", e.getMessage()));
            }
        } else {
            log.info("Получение списка всех продуктов");
            List<ProductDTO> result =  service.findAll();
            List<String> strings =  result.stream().map(obj -> {
                try {
                    return mapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            log.debug("Полученые продукты {}", strings);
            out.println(strings);
        }
    }

    /**
     * Обновление всех полей продукта по уникальному идентификатору
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Обновление всех полей продукта по уникальному идентификатору");
        String id = req.getParameter("id");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id != null) {
            try {
                ProductDTO product = getPeoductDTO(req);
                product.setProductId(Integer.valueOf(id));
                service.update(product);
                log.debug("Продукт успешно обновлен {}", product);
                out.println("Продукт успешно обновлен");
            } catch (JsonParseException | JsonMappingException | DataNotFound e) {
                resp.getWriter().format("Ошибка %s", e.getMessage());
            }
        } else {
            out.println("Не задан обязательный параметр ID");
        }
    }

    /**
     * Удаление продукта по уникальному идентификатору
     * DELETE /producs?id={id}
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Удаление продукта");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        out.write("id = " + id);
        if (id != null) {
            log.debug("Удаление продукта с id - {}", id);
            service.delete(Integer.valueOf(id));
        }
    }

    /**
     * Метод конвертации реквеста в dto объект
     *
     * @param req - реквест
     * @return - dto
     * @throws IOException - ошибка  IO
     */
    private ProductDTO getPeoductDTO(HttpServletRequest req) throws IOException {
        var requestString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        var product = mapper.readValue(requestString, ProductDTO.class);
        return product;
    }
}