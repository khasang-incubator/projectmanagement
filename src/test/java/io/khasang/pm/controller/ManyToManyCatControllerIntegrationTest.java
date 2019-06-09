package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.CatWoman;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ManyToManyCatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";

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
        Cat receivedCat = responseEntity.getBody();
        Assert.assertNotNull(receivedCat);


    }

    @Test
    public void checkGettingAllCats() {
        // add h2db  - before test clean all db data
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

        List<Cat> receivedCats = responseEntity.getBody();
        assertNotNull(receivedCats);
    }

    private Cat createCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat("barsik");
        Cat cat2 = prefillCat("murzik");

        HttpEntity<Cat> entity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();
        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();

        Assert.assertNotNull(createdCat);
        assertEquals("barsik", createdCat.getName());
        return createdCat;
    }

    private Cat prefillCat(String name) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setDescription("good");

        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName("Riska");
        catWoman1.setDescription("good");

        CatWoman catWoman2 = new CatWoman();
        catWoman2.setName("Murka");
        catWoman2.setDescription("nice");

        List<CatWoman> catWomen = new ArrayList<>();
        catWomen.add(catWoman1);
        catWomen.add(catWoman2);
        cat.setCatWomen(catWomen);

        return cat;
    }


}
