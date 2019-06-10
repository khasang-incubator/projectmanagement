package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.EmployeeDao;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Employee;

public class EmployeeDaoImpl extends BasicDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(Class<Employee> entityClass) {
        super(entityClass);
    }
}
