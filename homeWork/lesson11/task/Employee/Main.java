package lesson11.task.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Comparator<Employee> employeeComparatorName = new EmployeeNameComporator();
        Comparator<Employee> employeeComparatorNameAndSalary = employeeComparatorName.thenComparing(new EmployeeSalaryComporator());
        Comparator<Employee> employeeComparatorFull = employeeComparatorNameAndSalary.thenComparing(new EmployeeAgeComporator().thenComparing(new EmployeeSalaryComporator()));

        List<Employee> employees = Employee.employeeGenerator(50);// не совсем понимаю, как это происходит
//Лист же абстрактный, или тут неявно создаётся объект другого класса? Разобрался, ссылочная переменная типа List, создаётся ArrayList
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
// Пробую разный синтаксис
        ArrayList <Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.addAll(employees);
        System.out.println(employeeArrayList);
        System.out.println("!!!!!!!!!!!!!!!!!!!");
        Collections.sort(employeeArrayList,employeeComparatorName);
        System.out.println(employeeArrayList);
        System.out.println("==========");
//  List<Employee> employees = new ArrayList<>(Employee.employeeGenerator(15) ==  List<Employee> employees = Employee.employeeGenerator(15)???
        List<Employee> employeeList = new ArrayList<>(Employee.employeeGenerator(15));
        System.out.println(employeeList);
        System.out.println("!!!!!!!!!!!!!!!!!!!");
        employeeList.sort(employeeComparatorFull);
        System.out.println(employeeList);

        System.out.println("@@@@@@@@@@");
        List<Employee> testComparable = new ArrayList<>(Employee.employeeGenerator(15));
        System.out.println(testComparable);
        Collections.sort(testComparable);//Можно перегрузить(?) метод сорт
        System.out.println(testComparable);


    }
}
