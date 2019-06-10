package io.khasang.pm.service.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.EmployeeDao;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Employee;
import io.khasang.pm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Override
    public Employee add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public Employee getById(long id) {
        return employeeDao.getById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Autowired
    public void setCatDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

}
