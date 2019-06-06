package io.khasang.pm.controller;

import io.khasang.pm.entity.ChildDocument;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class ChildDocumentControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/childDocument";
    private static final String ADD = "/add";
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
        ChildDocument receivedChildocument = responseEntity.getBody();
        assertNotNull(receivedChildocument);
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
