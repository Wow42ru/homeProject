package Lesson17.tasks.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static List<Student> findUniqueSubjects(List<Student> students) {
        Map<List<String>, List<Student>> stringStudentMap ;
               // students.stream().collect(Collectors.groupingBy(Student::getSubjects,Collectors.mapping()));
        System.out.println("======");


        System.out.println(students);
        return null;
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

        List<Student> students = new ArrayList<>(SubjectGenerator.studentGenerate(20, 6));
        System.out.println(students);
        Student.findUniqueSubjects(students);
    }
}
