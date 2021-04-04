package chuprynin.com.epam.rd;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

public class CheckedNoSuchElementException extends Exception {
    public CheckedNoSuchElementException(String message) {
        super(message);
    }

    public CheckedNoSuchElementException() {
        super("CheckedNoSuchElementException");
    }

    public String printFullStackTrace(){
        StringWriter sw = new StringWriter();
        super.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
