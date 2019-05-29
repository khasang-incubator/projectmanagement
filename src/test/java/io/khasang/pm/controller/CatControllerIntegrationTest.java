package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

public class CatControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/cat";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String UPDATE = "/update";

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

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Cat reseivedCat = responseEntity.getBody();
        assertNotNull(reseivedCat);
        assertEquals(barsik.getName(),reseivedCat.getName());

    }

    @Test
    public void checkGettingAllCats(){
     //   createCat();

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
        assertEquals(receivedCats.isEmpty(),false);

    }

    @Test
    public void checkUpdate(){

    }

    private Cat createCat() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Cat cat = prefillCat();

        HttpEntity<Cat> entity = new HttpEntity<>(cat, headers);
        RestTemplate template = new RestTemplate();
        Cat createdCat = template.exchange(
                ROOT+ADD,
                HttpMethod.POST,
                entity,
                Cat.class
        ).getBody();

        assertNotNull(createdCat);
        assertEquals("red",createdCat.getName());

        return createdCat;
    }

    private Cat prefillCat() {
        Cat cat = new Cat();
        cat.setName("red");
        cat.setDescription("mad cat");
        return cat;
    }
}
