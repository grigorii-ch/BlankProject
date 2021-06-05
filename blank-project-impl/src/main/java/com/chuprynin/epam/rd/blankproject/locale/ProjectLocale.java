package com.chuprynin.epam.rd.blankproject.locale;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Класс для поднятия бина в котором будет хранится локаль
 */
@Slf4j
@Data
@Component
public class ProjectLocale {
    private Locale locale;

    public ProjectLocale() {
        this.locale = Locale.ENGLISH;
    }
}
