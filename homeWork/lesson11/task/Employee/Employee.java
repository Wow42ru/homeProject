package lesson11.task.Employee;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

// Создать список объетов List<Employee>
// и сортировать по:
// имени
// имени и зарплете
// имени, зарплете, возрасту и компании

public class Employee implements Comparable<Employee>{
    private String name;
    private String company;
    private int salary;
    private int age;

    public Employee(String name, String company, int salary, int age) {
        setName(name);
        setAge(age);
        setCompany(company);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name != "")
            this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (company != null && company != "")
            this.company = company;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary >= 0)
            this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 18)
            this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary &&
                age == employee.age &&
                Objects.equals(name, employee.name) &&
                Objects.equals(company, employee.company);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, company, salary, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}'+"\n";
    }

    public static List<Employee> employeeGenerator(int num) {
        // метод для создания списка объектов класса Employee
        String[] names = {"Mike", "Tom", "Alex", "John", "Peter", "Jack", "Charlie", "Max", "Jenifer", "Linda", "Elizabeth"}; // массив с именами
        String[] companies = {"Microsoft", "IBM", "Google", "General Electric", "Siemens", "Samsung", "Apple"}; // массив с названиями компаний

        List<Employee> employees = new ArrayList<>(num);

        // добавление num объектов Employee в список (employees)
        for (int i = 0; i < num; i++) {
            employees.add(new Employee(names[(int) (Math.random() * names.length)], companies[(int) (Math.random() * companies.length)], 15000 + (int) (Math.random() * 100000), 21 + (int) (Math.random() * 39)));
        }
        return employees;
    }


    @Override
    public int compareTo(Employee o) {
      return this.getName().compareTo(o.getName());//Проверка ещё одного способа сортировки
    }
}