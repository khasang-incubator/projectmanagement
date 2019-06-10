package io.khasang.pm.controller;

import io.khasang.pm.entity.Car;
import io.khasang.pm.entity.Driver;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class DriverControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/driver";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private RestTemplate template;
    private HttpHeaders headers;

    public DriverControllerIntegrationTest(){
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void checkAddDriver() {
        Driver newDriver = createDriver();

        ResponseEntity<Driver> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Driver.class,
                newDriver.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Driver reseivedDriver = responseEntity.getBody();
        assertNotNull(reseivedDriver);
        assertEquals(newDriver.getName(), reseivedDriver.getName());
    }

    @Test
    public void checkGettingAllDrivers() {
        ResponseEntity<List<Driver>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Driver>>() {
                }
        );
        List<Driver> receivedDrivers = responseEntity.getBody();

        assertNotNull(receivedDrivers);
        assertFalse(receivedDrivers.isEmpty());
    }

    private Driver createDriver() {
        Driver driver = prefillDriver();
        Driver createdDriver = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                createHttpEntity(driver),
                Driver.class
        ).getBody();

        assertNotNull(createdDriver);
        assertEquals("Turtle", createdDriver.getName());
        return createdDriver;
    }

    private Driver prefillDriver() {
        Car carOne = new Car("Ford","S123UV75");
        Car carTwo = new Car("Fiat","t456RE75");

        Driver driver = new Driver();
        driver.setName("Turtle");
        driver.addCar(carOne);
        driver.addCar(carTwo);
        return driver;
    }

    private HttpEntity<Driver> createHttpEntity(Driver entity) {
        return new HttpEntity<>(entity, headers);
    }
}
