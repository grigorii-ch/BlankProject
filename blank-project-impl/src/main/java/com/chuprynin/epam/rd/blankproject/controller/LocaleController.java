package com.chuprynin.epam.rd.blankproject.controller;

import com.chuprynin.epam.rd.blankproject.locale.ProjectLocale;
import org.springframework.context.ApplicationContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Класс для установки локализации
 */
public class LocaleController extends HttpServlet {
    private ProjectLocale projectLocale;
    private final String RU_LOCALE = "Ru";
    private final String EN_LOCALE = "En";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext context = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

        projectLocale = context.getBean(ProjectLocale.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        String requestlocale = req.getParameter("locale");
        PrintWriter out = resp.getWriter();
        out.println(String.format("Текущая локализация %s", projectLocale.toString()));
        if (requestlocale.equals(RU_LOCALE)) {
            projectLocale.setLocale(Locale.getDefault());
            out.println(String.format("Установленна локализация %s", projectLocale.toString()));
        } else if (requestlocale.equals(EN_LOCALE)) {
            projectLocale.setLocale(Locale.ENGLISH);
            out.println(String.format("Установленна локализация %s", projectLocale.toString()));
        } else {
            out.println(String.format("Ошибка, введено не верное значение локализаци в запросе \n ожидалось (Ru/En) \n полученно %s", requestlocale));
        }
    }
}
