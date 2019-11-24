package Lesson13Exception.Exceptions;

public class WrongSign extends Exception {
    @Override
    public String getMessage() {
        return "Пропущен или введён некоректный арифметический знак";
    }
}
