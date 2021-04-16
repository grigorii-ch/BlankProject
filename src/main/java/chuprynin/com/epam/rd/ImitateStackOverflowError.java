package chuprynin.com.epam.rd;

public class ImitateStackOverflowError {
    public void doIt(){
        this.cicle();
    }

    public void cicle(){
        this.doIt();
    }
}
