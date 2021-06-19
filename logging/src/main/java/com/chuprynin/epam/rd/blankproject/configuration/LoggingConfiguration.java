package com.chuprynin.epam.rd.blankproject.configuration;

import com.chuprynin.epam.rd.blankproject.LoggingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для бина loggingBean
 */
@Configuration
@ConditionalOnProperty(prefix = "logging", name = "enabled", havingValue = "true")
public class LoggingConfiguration {
    @Bean
    public LoggingBean loggingBean() {
        return new LoggingBean();
    }
}
