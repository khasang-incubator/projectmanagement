package io.khasang.pm.controller;

import io.khasang.pm.dto.ProjectDto;
import io.khasang.pm.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/project";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";

    @Test
    public void checkAddProject() {
        Project project = createProject();

        RestTemplate template = new RestTemplate();
        ResponseEntity<ProjectDto> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ProjectDto.class,
                project.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ProjectDto receivedProject = responseEntity.getBody();
        Assert.assertNotNull(receivedProject);

    }

    @Test
    public void checkGettingAllProjects() {
        // add h2db  - before test clean all db data
        createProject();
        createProject();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<ProjectDto>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProjectDto>>() {
                }
        );

        List<ProjectDto> receivedProjects = responseEntity.getBody();
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
        assertEquals("BarsikProject", createdProject.getName());
        return createdProject;
    }

    private Project prefillProject() {

        Project project = new Project();
        project.setDescription("Boss");
        project.setName("BarsikProject");

        Book book1 = new Book();
        book1.setModel("Harry Potter");
        book1.setYear(LocalDate.of(2008, 11, 21));

        Book book2 = new Book();
        book2.setModel("Terminator");
        book2.setYear(LocalDate.of(2013, 3, 16));

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        project.setBookList(books);

        return project;

//        Project project = new Project();
//        project.setName("project");
//        project.setDescription("good");
//        return project;
    }


}
