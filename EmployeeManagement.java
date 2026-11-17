import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A small console application for adding, displaying, and searching employees.
 */
public class EmployeeManagement {
    private static final List<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    addEmployee();
                    break;
                case "2":
                    displayEmployees();
                    break;
                case "3":
                    searchEmployee();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–4.");
            }
        }

        scanner.close();
    }

    private static void addEmployee() {
        System.out.print("Employee ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Employee ID cannot be empty.");
            return;
        }
        if (findEmployeeById(id) != null) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Department: ");
        String department = scanner.nextLine().trim();
        if (name.isEmpty() || department.isEmpty()) {
            System.out.println("Name and department cannot be empty.");
            return;
        }

        System.out.print("Salary: ");
        String salaryInput = scanner.nextLine().trim();
        try {
            BigDecimal salary = new BigDecimal(salaryInput);
            if (salary.signum() < 0) {
                System.out.println("Salary cannot be negative.");
                return;
            }
            employees.add(new Employee(id, name, department, salary));
            System.out.println("Employee added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid numeric salary.");
        }
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }

        System.out.println("\nEmployee ID | Name | Department | Salary");
        System.out.println("-----------------------------------------");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter employee ID to search: ");
        String id = scanner.nextLine().trim();
        Employee employee = findEmployeeById(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee found:");
            System.out.println("Employee ID | Name | Department | Salary");
            System.out.println(employee);
        }
    }

    private static Employee findEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        return null;
    }

    private static final class Employee {
        private final String id;
        private final String name;
        private final String department;
        private final BigDecimal salary;

        private Employee(String id, String name, String department, BigDecimal salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        private String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + department + " | " + salary.toPlainString();
        }
    }
}
