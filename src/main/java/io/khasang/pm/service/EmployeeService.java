package io.khasang.pm.service;

import io.khasang.pm.entity.Employee;

import java.util.List;

public interface EmployeeService {
    /**
     * required for adding employee to db
     *
     * @param employee - employee for adding
     * @return added employee
     */
    Employee add(Employee employee);

    /**
     * getting specify employee by ID
     *
     * @param id - employee's id for receiving
     * @return employee by id
     */
    Employee getById(long id);

    /**
     * getting all employees
     *
     * @return all employees from DB
     */
    List<Employee> getAll();
}
