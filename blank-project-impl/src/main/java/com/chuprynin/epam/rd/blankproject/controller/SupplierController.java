package com.chuprynin.epam.rd.blankproject.controller;

import com.chuprynin.epam.rd.blankproject.dto.SupplierDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.service.SupplierService;
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
 * Класс контроллер, для обработки запросов /customers
 */
@Slf4j
public class SupplierController extends HttpServlet {
    private final SupplierService service;
    private final ObjectMapper mapper;

    public SupplierController() {
        this.service = new SupplierService();
        this.mapper = new ObjectMapper();
    }

    /**
     * Создание нового поставщика
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Создание нового поставщика");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            SupplierDTO supplier = getSupplierDTO(req);
            service.create(supplier);
            log.debug("Поставщик успешно создан {}", supplier);
            out.println("Поставщик успешно создан");
        } catch (JsonParseException | JsonMappingException | DataNotFound e) {
            resp.getWriter().format("Ошибка %s", e.getMessage());
        }
    }

    /**
     * Получение списка всех поставщиков
     * GET /suppliers
     * Получение поставщиков по уникальному идентификатору
     * GET /suppliers?id={id}
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
            log.info("Получение поставщика");
            try {
                var result = service.findById(Integer.valueOf(id));
                out.println(mapper.writeValueAsString(result));
                log.debug("Полученный поставщик {}", result);
            } catch (DataNotFound e) {
                log.error("Ошибка: {}", e.getMessage(), e);
                out.println(String.format("Ошибка: %s", e.getMessage()));
            }
        } else {
            log.info("Получение списка всех поставщиков");
            List<SupplierDTO> result = service.findAll();
            List<String> strings = result.stream().map(obj -> {
                try {
                    return mapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    log.error("Ошибка: {}", e.getMessage(), e);
                }
                return null;
            }).collect(Collectors.toList());
            log.debug("Полученые поставщики {}", strings);
            out.println(strings);
        }
    }

    /**
     * Обновление всех полей поставщика по уникальному идентификатору
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Обновление всех полей поставщиков по уникальному идентификатору");
        String id = req.getParameter("id");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id != null) {
            try {
                SupplierDTO supplier = getSupplierDTO(req);
                supplier.setSupplierId(Integer.valueOf(id));
                service.update(supplier);
                log.debug("Заказчик успешно обновлен {}", supplier);
                out.println("Поставщик успешно обновлен");
            } catch (JsonParseException | JsonMappingException | DataNotFound e) {
                resp.getWriter().format("Ошибка %s", e.getMessage());
                log.error("Ошибка: {}", e.getMessage(), e);
            }
        } else {
            out.println("Не задан обязательный параметр ID");
        }
    }

    /**
     * Удаление поставщиков по уникальному идентификатору
     * DELETE /suppliers?id={id}
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Удаление поставщика");
        String id = req.getParameter("id");
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if (id != null) {
            log.debug("Удаление поставщика с id - {}", id);
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
    private SupplierDTO getSupplierDTO(HttpServletRequest req) throws IOException {
        var requestString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        var supplier = mapper.readValue(requestString, SupplierDTO.class);
        return supplier;
    }
}