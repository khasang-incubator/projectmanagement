package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Project;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/project";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";

    @Test
    public void checkAddCat() {
        Project project = createProject();

        RestTemplate template = new RestTemplate();
        ResponseEntity<Project> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Project.class,
                project.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Project receivedProject = responseEntity.getBody();
        Assert.assertNotNull(receivedProject);

        // update cat

        // select from db
    }

    @Test
    public void checkGettingAllCats() {
        // add h2db  - before test clean all db data
        createProject();
        createProject();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Project>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Project>>() {
                }
        );

        List<Project> receivedProjects = responseEntity.getBody();
        assertNotNull(receivedProjects);
    }

    private Project createProject() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Project project = prefillProject();

        HttpEntity<Project> entity = new HttpEntity<>(project, headers);
        RestTemplate template = new RestTemplate();
        Project createdProject = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Project.class
        ).getBody();

        Assert.assertNotNull(createdProject);
        assertEquals("project", createdProject.getName());
        return createdProject;
    }

    private Project prefillProject() {
        Project project = new Project();
        project.setName("project");
        project.setDescription("good");
        return project;
    }


}
