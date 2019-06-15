package io.khasang.pm.controller;

import io.khasang.pm.entity.Login;
import io.khasang.pm.entity.Role;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/role";
    private static final String ADD  = "/add";
    private static final String GET  = "/get";
    private static final String ALL  = "/all";

    @Test
    public void checkAddRole(){
        Role testRole = createRole();
        RestTemplate template = new RestTemplate();
        ResponseEntity<Role> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Role.class,
                testRole.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Role receivedRole = responseEntity.getBody();
        assertNotNull(receivedRole);
    }

    private Role createRole() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Role role = preFillRole();
        HttpEntity<Role> entity = new HttpEntity<>(role, headers);
        RestTemplate template = new RestTemplate();
        Role createdRole = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Role.class
        ).getBody();
        //Assert - класс с статич.методами для сравнения ожидаемого значения с актуальным
        assertNotNull(createdRole);
        assertEquals("testRole", createdRole.getName());

        return createdRole;
    }

    private Role preFillRole() {
        Role role = new Role();
        role.setName("testRole");
        role.setDescription("test role only for integration test");

        Login loginOne = new Login();
        loginOne.setName("loginOne");
        loginOne.setDescription("This is login number one");

        Login loginTwo = new Login();
        loginTwo.setName("loginTwo");
        loginTwo.setDescription("This is login number two");

        List<Login> logins = new ArrayList<>();
        logins.add(loginOne);
        logins.add(loginTwo);

        role.setLogin(logins);
        return role;
    }
}
