package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.DriverDao;
import io.khasang.pm.entity.Driver;

public class DriverDaoImpl  extends BasicDaoImpl<Driver> implements DriverDao {
    public DriverDaoImpl(Class<Driver> entityClass) {
        super(entityClass);
    }
}
