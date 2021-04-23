package com.chuprynin.epam.rd.helper;

import com.chuprynin.epam.rd.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelper {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String FILE = "src\\main\\resources\\file.txt";

    public static String getDataFromFile() {
        try {
            List<String> strings = Files.lines(Paths.get(FILE), StandardCharsets.ISO_8859_1)
                    .collect(Collectors.toList());

            return strings.get(0);

        } catch (IOException e) {
            logger.warn("Ошибка {}", e.getMessage(), e);
        }
        return "";
    }
}
