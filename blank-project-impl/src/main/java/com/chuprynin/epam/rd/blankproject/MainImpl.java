package com.chuprynin.epam.rd.blankproject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainImpl {
    public static void main(String[] args) {
        log.info("Запуск приложения");
        CrudView CrudService = new CrudView();
        CrudService.run();
        log.info("Завершение приложения");
    }
}
