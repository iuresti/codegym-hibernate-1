package codegym.mod04.repository;

import codegym.mod04.model.Employee;
import codegym.mod04.model.EmployeeTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

class TaskRepositoryTest {

    @BeforeAll
    static void setup() {
        TaskRepository taskRepository = new TaskRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();

        Employee ivan = employeeRepository.save(createEmployee("Ivan", "Programador", new BigDecimal(10), 40, LocalDate.of(1983, Month.JULY, 11)));
        Employee carlos = employeeRepository.save(createEmployee("Carlos", "Programador", new BigDecimal(100), 25, LocalDate.of(2003, Month.JULY, 11)));


        taskRepository.save(createTask(ivan, "Curso AWS", LocalDate.now().plusMonths(2)));
        taskRepository.save(createTask(ivan, "PM-78925", LocalDate.now().plusDays(3)));
        taskRepository.save(createTask(ivan, "PM-5681", LocalDate.now().plusWeeks(2)));

        taskRepository.save(createTask(carlos, "Módulo 4 Lección 10", LocalDate.now().plusDays(3)));
        taskRepository.save(createTask(carlos, "Levantar Startup", LocalDate.now().plusWeeks(5)));

    }

    private static EmployeeTask createTask(Employee employee, String name, LocalDate deadline) {
        EmployeeTask employeeTask = new EmployeeTask();

        employeeTask.setEmployee(employee);
        employeeTask.setName(name);
        employeeTask.setDeadline(deadline);

        return employeeTask;
    }

    @Test
    void getAllByEmployeeName() {
        TaskRepository taskRepository = new TaskRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();

        List<Employee> employees = employeeRepository.getAllByName("Ivan");

        List<EmployeeTask> tasks = taskRepository.getAllByEmployeeName("Ivan");

        employees = employeeRepository.getAllByName("Carlos");

        tasks = taskRepository.getAllByEmployeeName("Carlos");

        System.out.println("Terminó");


    }

    private static Employee createEmployee(String name, String ocuppation, BigDecimal salary, int age, LocalDate birthday) {
        Employee employee = new Employee();

        employee.setName(name);
        employee.setOccupation(ocuppation);
        employee.setSalary(salary);
        employee.setBirthdate(birthday);
        employee.setAge(40);

        return employee;
    }
}
