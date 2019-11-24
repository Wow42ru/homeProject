package Lesson13Exception.Exceptions;

public class IncorrectNumeralException extends Exception {

    @Override
    public String getMessage() {
        return "Отсутствуют n или m";
    }
}

