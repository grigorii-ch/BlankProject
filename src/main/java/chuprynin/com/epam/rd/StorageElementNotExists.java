package chuprynin.com.epam.rd;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StorageElementNotExists extends RuntimeException {
    public StorageElementNotExists(String message) {
        super(message);
    }

    public String printFullStackTrace(){
        StringWriter sw = new StringWriter();
        super.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public StorageElementNotExists() {
        super("Ошибка UncheckedNullPointerException");
    }
}
