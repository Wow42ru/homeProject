package lesson11.task.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Comparator<Employee> employeeComparatorName = new EmployeeNameComporator();
        Comparator<Employee> employeeComparatorNameAndSalary = employeeComparatorName.thenComparing(new EmployeeSalaryComporator());
        Comparator<Employee> employeeComparatorFull = employeeComparatorNameAndSalary.thenComparing(new EmployeeAgeComporator().thenComparing(new EmployeeSalaryComporator()));

        List<Employee> employees = Employee.employeeGenerator(50);
      
        TreeSet<Employee> employeeTreeSet = new TreeSet<>(employeeComparatorName);
        TreeSet<Employee> employeeTreeSet1 = new TreeSet<>(employeeComparatorNameAndSalary);
        TreeSet<Employee> employeeTreeSet2 = new TreeSet<>(employeeComparatorFull);

        employeeTreeSet.addAll(employees);
        employeeTreeSet1.addAll(employees);
        employeeTreeSet2.addAll(employees);

        System.out.println(employeeTreeSet);
        System.out.println("-------");
        System.out.println(employeeTreeSet1);
        System.out.println("-------");
        System.out.println(employeeTreeSet2);
        System.out.println("-------");

    }
}
