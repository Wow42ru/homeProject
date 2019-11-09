package lesson11.task.Employee;

import java.util.Comparator;

public class EmployeeAgeComporator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
    }

}


