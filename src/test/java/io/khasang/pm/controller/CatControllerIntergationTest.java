package io.khasang.pm.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.khasang.pm.entity.Cat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class CatControllerIntergationTest {

    private  static final String ROOT = "http://localhost:8080/cat";
    private  static final String ADD = "/add";
    private  static final String GET = "/get";
    private  static final String ALL = "/all";
    private  static final String UPDATE = "/update";

    @Test
    public void checkAddCat() {
        Cat barsik = createCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                barsik.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Cat recievedCat = responseEntity.getBody();
        assertNotNull(recievedCat);
    }

    @Test
    public void checkUpdateCat() {
        //create cat
        Cat barsik = createCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                barsik.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Cat recievedCat = responseEntity.getBody();
        assertNotNull(recievedCat);

        //update cat
        String catName = barsik.getName();
        barsik.setName("murzik");

        Cat updatedCat = updateCat(barsik);

        template = new RestTemplate();
        responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                updatedCat.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        recievedCat = responseEntity.getBody();
        assertNotNull(recievedCat);
        assertNotEquals(catName, recievedCat.getName());
    }

    @Test
    public void checkGettingAllCats() {
        //add h2db - before test clean all db data
        createCat();
        createCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Cat>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );

        List<Cat> reciviedCats = responseEntity.getBody();
        Assert.assertNotNull(reciviedCats);
    }

    private Cat createCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity<Cat> entity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();
        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals("barsik", createdCat.getName());
        return createdCat;
    }

    private Cat updateCat(Cat cat) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Cat> entity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();
        Cat updatedCat = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Cat.class
        ).getBody();

        assertNotNull(updatedCat);
        return updatedCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("barsik");
        cat.setDescription("good");
        return cat;
    }
}
