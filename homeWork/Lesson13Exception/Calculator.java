package Lesson13Exception;

import Lesson13Exception.Exceptions.DivideByZero;
import Lesson13Exception.Exceptions.IncorrectNumeralException;
import Lesson13Exception.Exceptions.WrongSign;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: 16.11.2019 Калькулятор считывает с консоли одно из выражений (n+m / n-m / nХm / n/m, где n и m - целые числа) или exit для завершения работы.
//  Калькулятор выводит результат вычисления в консоль, после чего ожидает ввода нового выражения или exit для завершения работы.
//  В случае если введенное выражение не совпадает с заданным шабломом, выбрасывает исключения: отсутствуют n или m - выбрасывать один тип исключения,
//  указан не тот оператор - выбрасывать другое исключение, в качестве оператора указан /, а второй операнд 0, либо n или m не целые числа - выбрасывать третий тип исключения.
//  Необходимо написать свои классы исключений. Исключения должны быть проверяемыми (checked). Метод исключений getMessage() необходимо переопределить (реализация на Ваше усмотрение).
public class Calculator {
    private Scanner scanner = new Scanner(System.in);
    private Pattern pattern = Pattern.compile("(\\d*\\.?\\,?\\d*)(.?)(\\d*\\.?\\,?\\d*)");//  [+\-\\*/]
    private double a;
    private double b;

    private void plus(double a, double b) {
        System.out.println(a + b);
    }

    private void minus(double a, double b) {
        System.out.println(a - b);
    }

    private void multi(double a, double b) {
        System.out.println(a * b);
    }

    private void divide(double a, double b) {
        if (b==0)
        {
            try {
                throw new DivideByZero();
            } catch (DivideByZero divideByZero) {
                System.out.println(divideByZero.getMessage());
            }

        }
       else System.out.println(a / b);
    }

    public void working() {
        System.out.println("Hello! Для выхода введите \"exit\"");
       Loop: while (true) {
           System.out.println("Введите выражение вида : n+m / n-m / n*m / n/m, где n и m -  числа ");
            String scan = scanner.nextLine();
            if (scan.equals("exit"))
                System.exit(0);
            scan = scan.replaceAll("\\s", "");
            Matcher matcher = pattern.matcher(scan);
            // System.out.print(matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3) + " = ");// индекс группы начинается с 1 (минус 3 часа жизни)
            try {
                matcher.find();
                a = Double.parseDouble(matcher.group(1));// про то, что на входе должны быть целые числа, прочитал поздно
                b = Double.parseDouble(matcher.group(3));// обидно удалять для 3 задания =(
            } catch (Exception e) {// не совсем понял, правильно ли сделал. При переводе строки(не числа) в число в
                // любом случае возникает ArithmeticException(unchecked), по условию должно быть проверяемое исключения, из-за этого пришлось изврашаться
                try {
                    throw new IncorrectNumeralException();
                } catch (IncorrectNumeralException ex) {
                    System.out.println(ex.getMessage());
                    continue Loop;
                }
            }
            String sign = matcher.group(2);
            switch (sign) {
                case "+":
                    plus(a, b);
                    break;
                case "-":
                    minus(a, b);
                    break;
                case "/":
                    divide(a, b);
                    break;
                case "*":
                    multi(a, b);
                    break;
                default:
                    try {
                        throw new WrongSign();
                    } catch (WrongSign wrongSign) {
                        System.out.println(wrongSign.getMessage());// для неарифметического знака
                    }
            }
        }
    }
}

class TestCalc {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.working();
    }
}