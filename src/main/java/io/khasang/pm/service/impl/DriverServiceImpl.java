package io.khasang.pm.service.impl;

import io.khasang.pm.dao.DriverDao;
import io.khasang.pm.dto.DriverDto;
import io.khasang.pm.entity.Driver;
import io.khasang.pm.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("driverService")
public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;
    private DriverDto driverDto;

    @Override
    public Driver add(Driver driver) {
        return driverDao.add(driver);
    }

    @Override
    public DriverDto getById(long id) {
        return driverDto.getDriverDto(driverDao.getById(id));
    }

    @Override
    public List<DriverDto> getAll() {
        List<Driver> driverList = driverDao.getAll();
        List<DriverDto> driverDtoList = new ArrayList<>();

        for (Driver driver : driverList) {
            driverDtoList.add(driverDto.getDriverDto(driver));
        }

        return driverDtoList;
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

    @Autowired
    public void setDriverDto(DriverDto driverDto) {
        this.driverDto = driverDto;
    }
}
