package io.khasang.pm.controller;

import io.khasang.pm.entity.Driver;
import io.khasang.pm.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {
    private DriverService driverService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Driver addDriver(@RequestBody Driver driver) {
        return driverService.saveOrUpdate(driver);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Driver getById(@PathVariable("id") long id) {
        return driverService.getById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Driver> getAll() {
        return driverService.getAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Driver update(@RequestBody Driver driver) {
        Driver updatedDriver = driverService.getById(driver.getId());
        if (updatedDriver != null) {
            if (!driver.getName().isEmpty()) {
                updatedDriver.setName(driver.getName());
            }
        } else {
            updatedDriver = driver;
        }
        return driverService.saveOrUpdate(updatedDriver);
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }
}
