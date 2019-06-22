package io.khasang.pm.controller;

import io.khasang.pm.entity.ChildDocument;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.*;

public class ChildDocumentControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/childDocument";
    private static final String ADD = "/add";
    private static final String UPDATE = "/update";
    private static final String DELETE = "/delete";
    private static final String GET = "/get";
    private static final String ALL = "/all";

    @Test
    public void checkAddChildDocument() {
        ChildDocument childDocument = createChildDocument();
        RestTemplate template = new RestTemplate();
        ResponseEntity<ChildDocument> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ChildDocument.class,
                childDocument.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ChildDocument receivedChildDocument = responseEntity.getBody();
        assertNotNull(receivedChildDocument);
    }

    @Test
    public void checkChildDocumentUpdate() {
        ChildDocument childDocument = createChildDocument();
        RestTemplate template = new RestTemplate();
        ResponseEntity<ChildDocument> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ChildDocument.class,
                childDocument.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ChildDocument receivedChildDocument = responseEntity.getBody();
        assertNotNull(receivedChildDocument);

        receivedChildDocument.setName("NameUpdatedFromIntegrationTest");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<ChildDocument> entity = new HttpEntity<>(receivedChildDocument, headers);
        ChildDocument updatedChilDDocument = template.exchange(
                ROOT + UPDATE,
                HttpMethod.PUT,
                entity,
                ChildDocument.class
        ).getBody();

        assertEquals("NameUpdatedFromIntegrationTest", updatedChilDDocument.getName());

        ResponseEntity<ChildDocument> responseEntity2 = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ChildDocument.class,
                updatedChilDDocument.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        assertEquals("NameUpdatedFromIntegrationTest", responseEntity2.getBody().getName());
    }

    @Test
    public void checkChildDocumentDelete() {
        ChildDocument childDocument = createChildDocument();
        RestTemplate template = new RestTemplate();
        ResponseEntity<ChildDocument> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ChildDocument.class,
                childDocument.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ChildDocument receivedChildDocument = responseEntity.getBody();
        assertNotNull(receivedChildDocument);

        ResponseEntity<ChildDocument> responseEntity2 = template.exchange(
                ROOT + DELETE + "/{id}",
                HttpMethod.DELETE,
                null,
                ChildDocument.class,
                receivedChildDocument.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());

        ResponseEntity<ChildDocument> responseEntity3 = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                ChildDocument.class,
                receivedChildDocument.getId()
        );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity3.getBody());
    }

    @Test
    public void checkGettingAllChildDocuments() {
        createChildDocument();
        createChildDocument();

        RestTemplate template = new RestTemplate();
        ResponseEntity<List<ChildDocument>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ChildDocument>>() {
                }
        );
        List<ChildDocument> receivedChildDocuments = responseEntity.getBody();
        assertNotNull(receivedChildDocuments);
    }

    private ChildDocument createChildDocument() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        ChildDocument document = prefillChildDocument();
        HttpEntity<ChildDocument> entity = new HttpEntity<>(document, headers);
        RestTemplate template = new RestTemplate();
        ChildDocument createdChildDocument = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                ChildDocument.class
        ).getBody();

        assertNotNull(createdChildDocument);
        assertEquals("testChDocName", createdChildDocument.getName());
        return createdChildDocument;
    }

    private ChildDocument prefillChildDocument() {
        ChildDocument childDocument = new ChildDocument();
        childDocument.setName("testChDocName");
        childDocument.setDescription("testChDocDescription");
        return childDocument;
    }
}
