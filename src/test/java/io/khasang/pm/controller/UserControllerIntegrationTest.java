package io.khasang.pm.controller;

import io.khasang.pm.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/users";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";

    @Test
    public void checkGetUser() {
        User user = createUser("asdf1");
        RestTemplate template = new RestTemplate();
        ResponseEntity<User> responseEntity = template.exchange(
                ROOT + GET + "/" + user.getLogin(),
                HttpMethod.GET,
                null,
                User.class,
                user.getLogin()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        User receivedUser = responseEntity.getBody();
        Assert.assertNotNull(receivedUser);
        Assert.assertEquals(user.getLogin(), receivedUser.getLogin());
    }

    @Test
    public void checkGettingAllUsers() {
        createUser("asdf2");
        createUser("asdf3");

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }
        );

        List<User> receivedUsers = responseEntity.getBody();
        assertNotNull(receivedUsers);
    }

    private User createUser(String login) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        User user = prefillUser(login);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        RestTemplate template = new RestTemplate();
        User createdUser = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                User.class
        ).getBody();

        Assert.assertNotNull(createdUser);
        assertEquals("igor", createdUser.getName());
        return createdUser;
    }

    private User prefillUser(String login) {
        User user = new User();
        user.setName("igor");
        user.setLogin(login);
        user.setPassword("igor");
        user.setFunction("spec");
        return user;
    }
}
