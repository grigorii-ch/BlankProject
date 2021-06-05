package com.chuprynin.epam.rd.blankproject.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Листенер для формирования ApplicationContext, используется получения бинов в контроллерах (сервлеты на томкат сервере)
 */
public class SpringApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.chuprynin.epam.rd.blankproject");
        sce.getServletContext().setAttribute("applicationContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}