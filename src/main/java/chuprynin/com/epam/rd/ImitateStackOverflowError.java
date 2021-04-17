package chuprynin.com.epam.rd;

/**
 * Создание исключения StackOverflowError
 */
public class ImitateStackOverflowError {
    public void doIt(){
        this.cicle();
    }

    public void cicle(){
        this.doIt();
    }
}
