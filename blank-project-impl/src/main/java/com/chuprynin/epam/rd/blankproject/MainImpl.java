package com.chuprynin.epam.rd.blankproject;

import com.chuprynin.epam.rd.blankproject.locale.ProjectLocale;
import com.chuprynin.epam.rd.blankproject.service.impl.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

@Slf4j
public class MainImpl {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.chuprynin.epam.rd.blankproject");

        CustomerService customerService = context.getBean(CustomerService.class);
        ProjectLocale projectLocale = context.getBean(ProjectLocale.class);

        System.out.println(customerService.findAll());
        projectLocale.setLocale(Locale.getDefault());
        System.out.println(customerService.findAll());
    }
}
