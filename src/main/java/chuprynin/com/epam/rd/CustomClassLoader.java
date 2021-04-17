package chuprynin.com.epam.rd;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для загрузки .class файлов в рабочее приложение
 */
@Slf4j
public class CustomClassLoader extends ClassLoader {

    private final static String VALLID_EXTENSION = ".class";

    /**
     * Входной метод
     *
     * @param pathName путь до файла
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String pathName) throws ClassNotFoundException {

        List<String> fileNames = getListFileNames(pathName);

        Class loadClass = Object.class;
        try {
            InputStream inStream = null;
            for (String fileName : fileNames) {
                File file = new File(pathName + "\\" + fileName);

                inStream = new BufferedInputStream(new FileInputStream(file));
                byte[] bytes = new byte[(int) file.length()];
                inStream.read(bytes);

                loadClass = defineClass(fileName.replace(VALLID_EXTENSION, ""), bytes, 0, bytes.length);
                return loadClass;
            }
        } catch (Exception e) {
            log.warn("Ошибка {}", e.getMessage(), e);
            throw new ClassNotFoundException("Проблемы с байт кодом");
        }
        return loadClass;
    }

    /**
     * Получение всех файлов в директории
     *
     * @param pathName
     * @return
     * @throws ClassNotFoundException
     */
    private List<String> getListFileNames(String pathName) throws ClassNotFoundException {

        Path path = Paths.get(pathName);

        if (!Files.exists(path)) {
            throw new ClassNotFoundException(String.format("Заданного каталога \"%s\" не сушествует ", pathName));
        }

        File file = new File(pathName);
        List<String> files = checkFiles(file.list());

        if (files.size() == 0) {
            throw new ClassNotFoundException(String.format("В заданном каталоге \"%s\" нет файлов с расширением .class", pathName));
        }

        return files;
    }

    /**
     * Проверка, Фильтруются файлы только с расширением .class
     *
     * @param files
     * @return
     */
    private List<String> checkFiles(String[] files) {

        List<String> listFiles = new ArrayList(Arrays.asList(files));

        for (int i = 0; i < listFiles.size(); i++) {
            if (!listFiles.get(i).contains(VALLID_EXTENSION)) {
                listFiles.remove(i);
            }
        }

        return listFiles;
    }
}
