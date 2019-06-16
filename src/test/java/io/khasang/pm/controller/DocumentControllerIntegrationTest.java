package io.khasang.pm.controller;

import io.khasang.pm.dto.DocumentDto;
import io.khasang.pm.entity.Document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

public class DocumentControllerIntegrationTest {

    private static final String ROOT = "http://localhost:8080/doc";
    private static final String ADD = "/add";
    private static final String GET = "/get";
    private static final String ALL = "/all";
    private RestTemplate template;
    private HttpHeaders headers;

    public DocumentControllerIntegrationTest() {
        this.template = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void checkAddDocument() {
        Document document = createDoc();
        ResponseEntity<DocumentDto> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                DocumentDto.class,
                document.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        DocumentDto reseivedDoc = responseEntity.getBody();
        assertNotNull(reseivedDoc);
        assertEquals(document.getDocNumber(), reseivedDoc.getDocNumber());
    }

    @Test
    public void checkGettingAllDocuments() {
        ResponseEntity<List<DocumentDto>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DocumentDto>>() {
                }
        );
        List<DocumentDto> receivedDocuments = responseEntity.getBody();

        assertNotNull(receivedDocuments);
        assertFalse(receivedDocuments.isEmpty());
    }

    private Document createDoc() {
        Document doc = prefillDocument();

        Document createdDoc = template.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                createHttpEntity(doc),
                Document.class
        ).getBody();

        assertNotNull(createdDoc);
        assertEquals("12345/o/99", createdDoc.getDocNumber());
        return createdDoc;
    }

    private Document prefillDocument() {
        Document doc = new Document();
        doc.setDocNumber("12345/o/99");
        doc.setDocDate(LocalDate.of(2019, 6, 4));
        doc.setAuthor("Anonimus");
        doc.setContent("Freedom to parrots!!!");
        doc.setType("declaration");
        return doc;
    }

    private HttpEntity<Document> createHttpEntity(Document entity) {
        return new HttpEntity<>(entity, headers);
    }
}
