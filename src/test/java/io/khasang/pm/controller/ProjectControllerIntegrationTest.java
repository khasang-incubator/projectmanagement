package io.khasang.pm.controller;

import io.khasang.pm.dto.ProjectDto;
import io.khasang.pm.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ProjectControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/project";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private static final String DELETE = "/delete";
    private static final String UPDATE = "/update";

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

    @Test
    public void checkProjectUpdate() throws URISyntaxException {
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

        receivedProject.setName("murzik_project");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Project> entity = new HttpEntity<>(receivedProject, headers);
        Project updatedProject = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                Project.class
        ).getBody();

        assertEquals("murzik_project", updatedProject.getName());
        ResponseEntity<Project> responseEntity2 = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Project.class,
                updatedProject.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        assertEquals("murzik_project", responseEntity2.getBody().getName());
    }

    @Test
    public void checkDeletingProject() {
        Project project = createProject();

        RestTemplate template = new RestTemplate();
        ResponseEntity<ProjectDto> responseEntity = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                ProjectDto.class,
                project.getId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ProjectDto deletedProject = responseEntity.getBody();
        assertNotNull(deletedProject);
        assertEquals(project.getId(), deletedProject.getId());
    }


    @Test
    public void checkDelete() throws InterruptedException {
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

        ResponseEntity<Project> responseEntity2 = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                Project.class,
                receivedProject.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());

        TimeUnit.MILLISECONDS.sleep(100);

//        ResponseEntity<Project> responseEntity3= template.exchange(
//                ROOT + GET + "/{id}",
//                HttpMethod.GET,
//                null,
//                Project.class,
//                receivedProject.getId()
//        );
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNull(responseEntity3.getBody());
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
    }

}
