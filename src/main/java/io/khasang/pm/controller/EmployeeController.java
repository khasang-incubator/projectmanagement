package io.khasang.pm.controller;

import io.khasang.pm.dto.EmployeeDto;
import io.khasang.pm.entity.Employee;
import io.khasang.pm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.add(employee);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EmployeeDto getById(@PathVariable("id") long id) {
        return employeeService.getById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

}
