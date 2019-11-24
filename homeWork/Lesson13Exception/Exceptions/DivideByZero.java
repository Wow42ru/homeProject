package Lesson13Exception.Exceptions;

public class DivideByZero extends Exception{
    @Override
    public String getMessage() {
        return "Делить на ноль программа не умеет, матанализ не изучала";
    }
}
