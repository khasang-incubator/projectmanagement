package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.CatWoman;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";
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
    public void checkCatUpdate() throws URISyntaxException {
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

        receivedCat.setName("murzik");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Cat> entity = new HttpEntity<>(receivedCat, headers);
        Cat updatedCat = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Cat.class
        ).getBody();

        assertEquals("murzik", updatedCat.getName());
        ResponseEntity<Cat> responseEntity2 = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                updatedCat.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        assertEquals("murzik", responseEntity2.getBody().getName());
    }

    @Test
    public void checkDelete() throws InterruptedException {
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

        ResponseEntity<Cat> responseEntity2 = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                Cat.class,
                receivedCat.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());

        TimeUnit.MILLISECONDS.sleep(100);

        ResponseEntity<Cat> responseEntity3 = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                receivedCat.getId()
        );
        System.err.println("ad");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.err.println("asdd");
        assertNull(responseEntity3.getBody());
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

        Cat cat = prefillCat();

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

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("barsik");
        cat.setDescription("good");

        CatWoman catWoman1 = new CatWoman();
        catWoman1.setName("Riska");
        catWoman1.setDescription("good");

        CatWoman catWoman2 = new CatWoman();
        catWoman2.setName("Riska");
        catWoman2.setDescription("good");

        List<CatWoman> catWomen = new ArrayList<>();
        catWomen.add(catWoman1);
        catWomen.add(catWoman2);
        cat.setCatWomen(catWomen);

        return cat;
    }

}
