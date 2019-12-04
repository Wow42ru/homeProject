package Lesson17.tasks.task3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PupilTask {
    public static void main(String[] args) {
        // Используя Stream API:
        Pupil pupil1 = new Pupil("Vasa", Pupil.Gender.MALE, LocalDate.of(1994, 12, 7));
        Pupil pupil2 = new Pupil("Petya", Pupil.Gender.MALE, LocalDate.of(1997, 2, 15));
        Pupil pupil3 = new Pupil("Lena", Pupil.Gender.FEMALE, LocalDate.of(1995, 1, 1));
        Pupil pupil4 = new Pupil("Bob", Pupil.Gender.MALE, LocalDate.of(1997, 10, 15));
        Pupil pupil5 = new Pupil("Sam", Pupil.Gender.FEMALE, LocalDate.of(1994, 12, 23));
        Pupil pupil6 = new Pupil("Anya", Pupil.Gender.FEMALE, LocalDate.of(1995, 1, 5));
        Pupil pupil7 = new Pupil("Anya", Pupil.Gender.FEMALE, LocalDate.of(1994, 2, 15));
        Pupil pupil8 = new Pupil("Bob", Pupil.Gender.MALE, LocalDate.of(1995, 3, 8));
        Pupil pupil9 = new Pupil("John", Pupil.Gender.MALE, LocalDate.of(1991, 4, 13));
        Pupil pupil10 = new Pupil("Sasha", Pupil.Gender.MALE, LocalDate.of(1995, 5, 23));
        Pupil pupil11 = new Pupil("Valdemar", Pupil.Gender.MALE, LocalDate.of(1994, 3, 11));
        Pupil pupil12 = new Pupil("Lena", Pupil.Gender.FEMALE, LocalDate.of(1995, 2, 18));
        Pupil pupil13 = new Pupil("Stephan", Pupil.Gender.MALE, LocalDate.of(1995, 5, 19));
        Pupil pupil14 = new Pupil("Nikita", Pupil.Gender.MALE, LocalDate.of(2000, 6, 9));
        Pupil pupil15 = new Pupil("Misha", Pupil.Gender.MALE, LocalDate.of(1995, 10, 1));
        Pupil pupil16 = new Pupil("Michael", Pupil.Gender.MALE, LocalDate.of(1994, 7, 10));
        Pupil pupil17 = new Pupil("Marina", Pupil.Gender.FEMALE, LocalDate.of(1965, 10, 12));

        List<Pupil> list = Arrays.asList(pupil1, pupil2, pupil3, pupil4, pupil5, pupil6, pupil7, // 1. Разделить учеников на две группы: мальчиков и девочек.
                pupil8, pupil9, pupil10, pupil11, pupil12, pupil13, pupil14, pupil15, pupil16, pupil17);      // Результат: Map<Pupil.Gender, ArrayList<Pupil>>
        // 1. Разделить учеников на две группы: мальчиков и девочек.
        Map<Pupil.Gender, ArrayList<Pupil>> maleFemaleSplit = list.stream()
                .collect(Collectors.groupingBy(Pupil::getGender, Collectors.toCollection(ArrayList::new)));// Результат: Map<Pupil.Gender, ArrayList<Pupil>
        System.out.println("1. Разделить учеников на две группы: мальчиков и девочек. " + maleFemaleSplit);

        //2. Найти средний возраст учеников
        double i = list.stream().collect(Collectors.averagingInt(p -> Period.between(p.getBirth(), LocalDate.now()).getYears()));
        System.out.println("2. Найти средний возраст учеников " + i);

        // 3. Найти самого младшего ученика
        Pupil YoungestPupil = list.stream()
                .min((o1, o2) -> o2.getBirth().compareTo(o1.getBirth()))
                .get();
        System.out.println("3. Найти самого младшего ученика " + YoungestPupil);

        // 4. Найти самого старшего ученика
        Pupil oldestPupil = list
                .stream()
                .min(Comparator.comparing(Pupil::getBirth))
                .get();
        System.out.println("4. Найти самого старшего ученика " + oldestPupil);

        // 5. Собрать учеников в группы по году рождения
        Map<Integer, List<Pupil>> groupingByYear = list
                .stream()
                .collect(Collectors.groupingBy(p -> p.getBirth().getYear()));
        System.out.println("5. Собрать учеников в группы по году рождения " + groupingByYear);

        // 6. Убрать учеников с одинаковыми именами, имена и дату рождения оставшихся вывести в консоль
        System.out.println("6. Убрать учеников с одинаковыми именами, имена и дату рождения оставшихся вывести в консоль:\n " + list
                .stream()
                .collect(Collectors.groupingBy(Pupil::getName))
                .values()
                .stream()
                .filter(pupils -> pupils.size() == 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Pupil::getName, Pupil::getBirth)));

        // 7. Отсортировать по полу, потом по дате рождения, потом по имени (в обратном порядке), собрать в список (List)
        List<Pupil> pupilList = list.stream()
                .sorted(Comparator.comparing(Pupil::getGender).reversed().thenComparing(Pupil::getBirth).reversed().thenComparing(Pupil::getName).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("7. Отсортировать по полу, потом по дате рождения, потом по имени (в обратном порядке), собрать в список (List)\n" + pupilList);

        // 8. Вывести в косоль всех учеников в возрасте от N до M лет
        System.out.println("8. Вывести в косоль всех учеников в возрасте от N до M лет: ");
            list.stream()
                .filter(p -> Period.between(p.getBirth(), LocalDate.now()).getYears() > 30 && Period.between(p.getBirth(), LocalDate.now()).getYears() < 70)
                .forEach(System.out::println);

        // 9. Собрать в список всех учеников с именем=someName
        List<Pupil> findByNameList = list
                .stream()
                .filter(p -> "Bob".equals(p.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("\n9. Собрать в список всех учеников с именем=someName" + findByNameList);

        // 10. Собрать Map<Pupil.Gender, Integer>, где Pupil.Gender - пол, Integer - суммарный возраст учеников данного пола
        Map<Pupil.Gender, Integer> genderIntegerMap = list
                .stream()
                .collect(Collectors.groupingBy(Pupil::getGender,
                        Collectors.summingInt(p -> Period.between(p.getBirth(), LocalDate.now()).getYears())));
        System.out.println("\n10. Собрать Map<Pupil.Gender, Integer>, где Pupil.Gender - пол, Integer - суммарный возраст учеников данного пола \n" + genderIntegerMap);
    }
}
