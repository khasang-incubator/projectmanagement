package io.khasang.pm.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.khasang.pm.entity.Cat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class CatControllerIntergationTest {

    private  static final String ROOT = "http://localhost:8080/cat";
    private  static final String ADD = "/add";
    private  static final String GET = "/get";
    private  static final String ALL = "/all";

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

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("b  arsik");
        cat.setDescription("good");
        return cat;
    }
}
