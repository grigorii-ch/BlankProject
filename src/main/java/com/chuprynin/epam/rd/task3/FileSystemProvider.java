package com.chuprynin.epam.rd.task3;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для отображения дерева файлов и каталогов
 */
@Slf4j
@Getter
public class FileSystemProvider {
    private String treePatch = "\n";

    /**
     * Метод для формирования дерева каталогов переданной директории
     *
     * @param path     Путь до директории
     * @param iterator цикл итерации
     * @throws IOException
     */
    public void run(String path, int iterator) throws IOException {
        List<File> directoryList = getDirectoryObjects(path);
        String indent = Stream.iterate(0, e -> e + 1).limit(iterator).map(e -> "-").collect(Collectors.joining());
        directoryList.stream().forEach(e -> {
            if (e.isDirectory()) {
                treePatch += indent + e.getName() + "\n";
                try {
                    run(e.getPath(), iterator + 1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                treePatch += indent + e.getName() + "\n";
            }
        });
    }

    /**
     * Возвращает лист файлов и каталогов из полученной директории
     *
     * @param path путь до директории
     * @return List<File> лист файлов и директорий
     */
    public List<File> getDirectoryObjects(String path) {
        return Arrays.asList(Objects.requireNonNull(new File(path).listFiles()));
    }
}
