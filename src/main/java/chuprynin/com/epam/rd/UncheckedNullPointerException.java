package chuprynin.com.epam.rd;

import java.io.PrintWriter;
import java.io.StringWriter;

public class UncheckedNullPointerException extends RuntimeException {
    public UncheckedNullPointerException(String message) {
        super(message);
    }

    public String printFullStackTrace(){
        StringWriter sw = new StringWriter();
        super.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public UncheckedNullPointerException() {
        super("Ошибка UncheckedNullPointerException");
    }
}
