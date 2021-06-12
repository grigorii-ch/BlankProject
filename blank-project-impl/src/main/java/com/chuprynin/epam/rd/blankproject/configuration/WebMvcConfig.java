package com.chuprynin.epam.rd.blankproject.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.chuprynin.epam.rd.blankproject")
public class WebMvcConfig implements WebMvcConfigurer {

}
