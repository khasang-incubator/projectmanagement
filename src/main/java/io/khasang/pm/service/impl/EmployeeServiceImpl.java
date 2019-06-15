package io.khasang.pm.service.impl;

import io.khasang.pm.dao.EmployeeDao;
import io.khasang.pm.dto.EmployeeDto;
import io.khasang.pm.entity.Employee;
import io.khasang.pm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;
    private EmployeeDto employeeDto;

    @Override
    public Employee add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public EmployeeDto getById(long id) {
        return employeeDto.getEmployeeDto(employeeDao.getById(id));
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeDao.getAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee e : employees) {
            employeeDtos.add(employeeDto.getEmployeeDto(e));
        }

        return employeeDtos;
    }

    @Autowired
    public void setCatDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
