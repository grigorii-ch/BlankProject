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
    private final String ru_locale = "Ru";
    private final String en_locale = "En";

    public ProjectLocale() {
        this.locale = Locale.ENGLISH;
    }
}
