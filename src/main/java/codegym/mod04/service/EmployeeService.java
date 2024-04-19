package codegym.mod04.service;

import codegym.mod04.model.Employee;
import codegym.mod04.repository.EmployeeRepository;

import java.util.UUID;

public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void save(Employee employee){


        employeeRepository.save(employee);
    }

}
