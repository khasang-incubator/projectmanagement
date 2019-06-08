package io.khasang.pm.service.impl;

import io.khasang.pm.dao.DriverDao;
import io.khasang.pm.entity.Driver;
import io.khasang.pm.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("driverService")
public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;

    @Override
    public Driver add(Driver driver) {
        return driverDao.add(driver);
    }

    @Override
    public Driver getById(long id) {
        return driverDao.getById(id);
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public Driver saveOrUpdate(Driver driver) {
        return driverDao.saveOrUpdate(driver);
    }

    @Autowired
    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }
}
