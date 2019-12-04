package Lesson17.tasks.task3;

import java.time.LocalDate;

public class Pupil {
    enum Gender {
        MALE, FEMALE
    }
    static int counter=0;
    private int number; // уникальное значение для каждого ученика
    private String name;
    private Gender gender;
    private LocalDate birth;

    @Override
    public String toString() {
        return "\nPupil{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                '}';
    }

    public Pupil(String name, Gender gender, LocalDate birth) {
       number = counter;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        counter++;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }
// TODO: добавить все необходимые методы

}
