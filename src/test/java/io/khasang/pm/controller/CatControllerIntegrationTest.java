package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";
    private RestTemplate template;
    private HttpHeaders headers;
    private HttpEntity<Cat> entity;

    public CatControllerIntegrationTest() {
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void checkAddCat() {

        Cat newCat = createCat();
        ResponseEntity<Cat> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Cat.class,
                newCat.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Cat reseivedCat = responseEntity.getBody();
        assertNotNull(reseivedCat);
        assertEquals(newCat.getName(), reseivedCat.getName());
    }

    @Test
    public void checkGettingAllCats() {
        ResponseEntity<List<Cat>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cat>>() {
                }
        );
        List<Cat> receivedCats = responseEntity.getBody();

        assertNotNull(receivedCats);
        assertFalse(receivedCats.isEmpty());
    }

    @Test
    public void checkUpdate() {

        Cat updatedCat = createCat();
        updatedCat.setName("new " + updatedCat.getName());

        entity = initHttpEntity(updatedCat);
        ResponseEntity<Cat> responseChangedEntity = template.exchange(
                ROOT + UPDATE + "/{id}",
                HttpMethod.PUT,
                entity,
                Cat.class,
                updatedCat.getId()
        );

        Cat changedCat = responseChangedEntity.getBody();

        assertEquals(HttpStatus.OK, responseChangedEntity.getStatusCode());
        assertNotNull(changedCat);
        assertEquals(changedCat.getName(), updatedCat.getName());
    }

    private Cat createCat() {

        Cat cat = prefillCat();
        entity = initHttpEntity(cat);
        Cat createdCat = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals("red12345", createdCat.getName());
        return createdCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("red12345");
        cat.setDescription("mad cat");
        return cat;
    }

    public HttpEntity<Cat> initHttpEntity(Cat entity) {
        return new HttpEntity<>(entity, headers);
    }
}
