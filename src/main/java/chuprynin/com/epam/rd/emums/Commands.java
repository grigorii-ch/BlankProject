package chuprynin.com.epam.rd.emums;

public enum Commands {
    ADD("add"),
    DELETE("delete"),
    PRINT("print"),
    EXIT("exit");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
