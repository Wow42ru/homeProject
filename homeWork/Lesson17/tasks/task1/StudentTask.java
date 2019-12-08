package Lesson17.tasks.task1;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StudentTask {
    public static void main(String[] args) {
        //TODO: найти студентов с уникальными предметами. Задачу решать с использованием stream API
    }
}

class Student {

    // можно добавить дополнительные поля
    private String login;
    private List<String> subjects;

    public Student(String login, List<String> subjects) {
        this.login = login;
        this.subjects = subjects;
    }

    public static Map<String, List<Student>> findUniqueSubjects(List<Student> students) {

       /* Collector<List<String>, List<String>, List<String>> toList = Collector.of(
                ArrayList::new, // метод инициализации аккумулятора
                (s1, s2) -> s1.addAll(s2),// метод обработки каждого элемента
                (l1, l2) -> {
                    l1.addAll(l2);
                    return l1;
                } // метод соединения двух аккумуляторов при параллельном выполнении
        );*/ // Вариант со своим коллектором

        /*students.stream().collect(Collectors.groupingBy(o -> {
                    ArrayList<String> subjects = students.stream()
                            .flatMap(student -> student.getSubjects().stream())
                            .collect(Collectors.groupingBy(su -> su, Collectors.counting()))
                            .entrySet()
                            .stream()
                            .filter(s -> s.getValue() == 1)
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toCollection(ArrayList::new));
                    for (String s : o.getSubjects()) {
                        for (String subject : subjects) {


                        }
                 ){*/



     /*   System.out.println( students
                .stream()
                .collect(Collectors.groupingBy(s-> {students
                        .stream()
                        .map(Student::getSubjects)
                        .collect(toList)
                        .stream()
                        .distinct()
                        .toArray().}*/

        return students.stream()
                .flatMap(student -> student.getSubjects().stream())        // получаем список предметов (с повторениями)
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()))// подсчитываем копии
                .entrySet()
                .stream()
                .filter(s -> s.getValue() == 1)// оставляем уникальные
                .map(Map.Entry::getKey)
                .collect(Collectors.toMap(o -> o,
                        s -> students.stream()
                                .filter(stu -> stu.getSubjects().contains(s))
                                .distinct()// не уверен, что необходимо
                                .collect(Collectors.toList())));

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "\nStudent{" +
                "login='" + login + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}

class TestStudent {


    public static void main(String[] args) {

        List<Student> students = new ArrayList<>(SubjectGenerator.studentGenerate(10, 3));// Создаём студентов
        // Проверка на всякое
        students.add(new Student("Test student", Arrays.asList("Test subject", "test subject2")));
        students.add(new Student("Test student2", Arrays.asList("Test subject")));
        students.add(new Student("Test student2", Arrays.asList("Test subject3", "Test subject4")));
        System.out.println(students);
        System.out.println("=========");
        Student.findUniqueSubjects(students).forEach((a, b) -> System.out.println(b.stream().map(Student::getLogin).findFirst().get() + " посещает уникальный предмет: " + a));//просто потому что могу
    }

}

class MyCollector implements Collector {
    @Override
    public Supplier supplier() {
        return null;
    }

    @Override
    public BiConsumer accumulator() {
        return null;
    }

    @Override
    public BinaryOperator combiner() {
        return null;
    }

    @Override
    public Function finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
