package chuprynin.com.epam.rd;

import java.util.ArrayList;

/**
 * Создание исключения OutOfMemoryError
 */
public class ImitateOutOfMemoryError {
    public void doIt(){
        ArrayList<String[]> arrayList = new ArrayList<>();

        for (;;) {
            arrayList.add(new String[1000]);
        }
    }
}
