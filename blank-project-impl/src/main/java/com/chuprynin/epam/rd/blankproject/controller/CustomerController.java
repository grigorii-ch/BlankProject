package com.chuprynin.epam.rd.blankproject.controller;

import com.chuprynin.epam.rd.blankproject.dto.CustomerDTO;
import com.chuprynin.epam.rd.blankproject.exceptions.DataNotFound;
import com.chuprynin.epam.rd.blankproject.service.impl.CustomerService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
public class CustomerController extends HttpServlet {
    private CustomerService service;
    private ObjectMapper mapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

        service = context.getBean(CustomerService.class);
        mapper = context.getBean(ObjectMapper.class);
    }

    /**
     * Создание нового покупателя
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Создание нового покупателя");
        setResponseParameters(resp);
        PrintWriter out = resp.getWriter();
        try {
            CustomerDTO customer = getCustomerDTO(req);
            service.create(customer);
            log.debug("Покупатель успешно создан {}", customer);
            out.println("Покупатель успешно создан");
        } catch (JsonParseException | JsonMappingException | DataNotFound e) {
            log.error("Ошибка: {}", e.getMessage(), e);
            resp.getWriter().format("Ошибка %s", e.getMessage());
        }
    }

    /**
     * Получение списка всех покупателей
     * GET /customers
     * Получение продукта по уникальному идентификатору
     * GET /customers?id={id}
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
            log.info("Получение покупателя");
            try {
                var result = service.findById(Integer.valueOf(id));
                log.debug("Полученный покупатель {}", result);
                out.println(mapper.writeValueAsString(result));
            } catch (DataNotFound e) {
                log.error("Ошибка: {}", e.getMessage(), e);
                out.println(String.format("Ошибка: %s", e.getMessage()));
            }
        } else {
            log.info("Получение списка всех покупателей");
            List<CustomerDTO> result = service.findAll();
            List<String> strings = result.stream().map(obj -> {
                try {
                    return mapper.writeValueAsString(obj);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            log.debug("Полученые покупатели {}", strings);
            out.println(strings);
        }
    }

    /**
     * Обновление всех полей покупателя по уникальному идентификатору
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Обновление всех полей покупателя по уникальному идентификатору");
        String id = req.getParameter("id");
        setResponseParameters(resp);
        PrintWriter out = resp.getWriter();
        if (id != null) {
            try {
                CustomerDTO customer = getCustomerDTO(req);
                customer.setCustomerId(Integer.valueOf(id));
                service.update(customer);
                log.debug("Заказчик успешно обновлен {}", customer);
                out.println("Заказчик успешно обновлен");
            } catch (JsonParseException | JsonMappingException | DataNotFound e) {
                resp.getWriter().format("Ошибка %s", e.getMessage());
                log.error("Ошибка: {}", e.getMessage(), e);
            }
        } else {
            out.println("Не задан обязательный параметр ID");
        }
    }

    /**
     * Удаление покупателя по уникальному идентификатору
     *
     * @param req  - реквест
     * @param resp - респонз
     * @throws IOException - ошибка  IO
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Удаление покупателя");
        String id = req.getParameter("id");
        setResponseParameters(resp);
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
    private CustomerDTO getCustomerDTO(HttpServletRequest req) throws IOException {
        var requestString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        var customer = mapper.readValue(requestString, CustomerDTO.class);
        return customer;
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