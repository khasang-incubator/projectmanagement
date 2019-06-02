package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";


    @Test
    public void checkAddCat(){
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
        Cat receivedCat = responseEntity.getBody();
        assertNotNull(receivedCat);
    }

    @Test
    public void checkGettingAllCats(){
        createCat();
        createCat();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Cat>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {

                }
//
        );
        List<Cat> receivedCats = responseEntity.getBody();
        assertNotNull(receivedCats);
    }

    private Cat createCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefilCat();
        HttpEntity<Cat> entity = new HttpEntity<>(cat,headers);
        RestTemplate template = new RestTemplate();
        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals("barsik",createdCat.getName());
        return createdCat;
    }

    private Cat prefilCat() {
    Cat cat = new Cat();
    cat.setName("barsik");
    cat.setDescription("good");
    return cat;

    }

}
