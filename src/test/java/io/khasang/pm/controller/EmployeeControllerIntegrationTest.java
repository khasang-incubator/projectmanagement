package io.khasang.pm.controller;

import io.khasang.pm.entity.Car;
import io.khasang.pm.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/employee";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";

    @Test
    public void checkAddEmployee() {
        Employee barsik = createEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Employee> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Employee.class,
                barsik.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Employee receivedEmployee = responseEntity.getBody();
        Assert.assertNotNull(receivedEmployee);
    }

    @Test
    public void checkGettingAllEmployees() {
        createEmployee();
        createEmployee();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Employee>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );

        List<Employee> receivedCats = responseEntity.getBody();
        assertNotNull(receivedCats);
    }

    private Employee createEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee();

        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        RestTemplate template = new RestTemplate();
        Employee createdEmployee = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Employee.class
        ).getBody();

        Assert.assertNotNull(createdEmployee);
        assertEquals("Barsik", createdEmployee.getName());
        return createdEmployee;
    }

    private Employee prefillEmployee() {
        Employee employee = new Employee();
        employee.setTitle("Boss");
        employee.setName("Barsik");

        Car car1 = new Car();
        car1.setModel("BMW");
        car1.setYear(LocalDate.of(2008, 11, 21));

        Car car2 = new Car();
        car2.setModel("LADA");
        car2.setYear(LocalDate.of(2013, 3, 16));

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        employee.setCarList(cars);

        return employee;
    }
}
