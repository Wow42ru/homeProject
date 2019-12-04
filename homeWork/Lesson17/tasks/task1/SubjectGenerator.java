package Lesson17.tasks.task1;

import java.util.*;

public class SubjectGenerator {
    public static void main(String[] args) {
        System.out.println(studentGenerate(10,5));
    }

    private static ArrayList generate(int number) {
        Set<String> subjects = new HashSet<>();
        List<String> strings = Arrays.asList("Math", "Biology", "Art", "Algebra", "English", "History", "Computer Science");

        if (number > strings.size())
            number = strings.size();

        for (int i = 0; i <number ; i++) {
            subjects.add(strings.get( (int) Math.floor(Math.random() * strings.size())));
        }
        return new ArrayList(subjects);
    }
    public static List<Student> studentGenerate(int students,int maxSubjects){
        List<Student> studentsList =new ArrayList<>();
        for (int i = 0; i <students ; i++) {
            studentsList.add(new Student(String.valueOf(i),generate(maxSubjects)));
        }
    return studentsList;
    }
}
