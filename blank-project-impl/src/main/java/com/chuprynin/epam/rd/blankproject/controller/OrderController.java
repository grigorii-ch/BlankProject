package com.chuprynin.epam.rd.blankproject.controller;

import com.chuprynin.epam.rd.blankproject.dto.OrderDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.service.OrderService;
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
 * Класс контроллер, для обработки запросов /orders
 */
@Slf4j
public class OrderController extends HttpServlet {
    private final OrderService service;
    private final ObjectMapper mapper;

    public OrderController() {
        this.service = new OrderService();
        this.mapper = new ObjectMapper();
    }
    /**
     * Создание нового заказа
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Создание нового заказа");
        setResponseParameters(resp);
        PrintWriter out = resp.getWriter();
        try {
            OrderDTO order = getOrderDTO(req);
            service.create(order);
            log.debug("Заказ успешно создан {}", order);
            out.println("Заказ успешно создан");
        } catch (JsonParseException | JsonMappingException | DataNotFound e) {
            resp.getWriter().format("Ошибка %s", e.getMessage());
        }
    }

    /**
     * Получение списка всех заказов
     * GET /orders
     * Получение заказа по уникальному идентификатору
     * GET /orders?id={id}
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        setResponseParameters(resp);
        PrintWriter out = resp.getWriter();
        if (id != null) {
            log.info("Получение заказа");
            try {
                var result = service.findById(Integer.valueOf(id));
                log.debug("Полученный заказ {}", result);
                out.println(mapper.writeValueAsString(result));
            } catch (DataNotFound e) {
                log.error("Ошибка: {}", e.getMessage(), e);
                out.println(String.format("Ошибка: %s", e.getMessage()));
            }
        } else {
            log.info("Получение списка всех заказов");
            List<OrderDTO> result = service.findAll();
            List<String> strings = result.stream().map(obj -> {
                try {
                    return mapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            log.debug("Полученые заказы {}", strings);
            out.println(strings);
        }
    }

    /**
     * Обновление всех полей заказа по уникальному идентификатору
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Обновление всех полей заказов по уникальному идентификатору");
        String id = req.getParameter("id");
        setResponseParameters(resp);
        PrintWriter out = resp.getWriter();
        if (id != null) {
            try {
                OrderDTO order = getOrderDTO(req);
                order.setOrderId(Integer.valueOf(id));
                service.update(order);
                log.debug("Заказ успешно обновлен {}", order);
                out.println("Заказ успешно обновлен");
            } catch (JsonParseException | JsonMappingException | DataNotFound e) {
                resp.getWriter().format("Ошибка %s", e.getMessage());
            }
        } else {
            out.println("Не задан обязательный параметр ID");
        }
    }

    /**
     * Удаление заказа по уникальному идентификатору
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Удаление заказа");
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("id");
        setResponseParameters(resp);
        out.write("id = " + id);
        if (id != null) {
            log.debug("Удаление покупателя с id - {}", id);
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
    private OrderDTO getOrderDTO(HttpServletRequest req) throws IOException {
        var requestString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        var order = mapper.readValue(requestString, OrderDTO.class);
        return order;
    }

    /**
     * Установка ContentType = "text/json", CharacterEncoding = "UTF-8" в респонз
     * @param resp респонз
     */
    private void setResponseParameters(HttpServletResponse resp) {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
    }
}