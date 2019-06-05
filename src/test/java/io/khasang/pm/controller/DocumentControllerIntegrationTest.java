package io.khasang.pm.controller;

import io.khasang.pm.entity.Document;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.sql.Date;
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
        ResponseEntity<Document> responseEntity = template.exchange(
                ROOT + GET + "/{id}",
                HttpMethod.GET,
                null,
                Document.class,
                document.getId()
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Document reseivedDoc = responseEntity.getBody();
        assertNotNull(reseivedDoc);
        assertEquals(document.getDocNumber(), reseivedDoc.getDocNumber());
    }

    @Test
    public void checkGettingAllDocuments() {
        ResponseEntity<List<Document>> responseEntity = template.exchange(
                ROOT + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Document>>() {
                }
        );
        List<Document> receivedCats = responseEntity.getBody();

        assertNotNull(receivedCats);
        assertFalse(receivedCats.isEmpty());
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
        doc.setDocDate(Date.valueOf("2019-06-04"));
        doc.setAuthor("Anonimus");
        doc.setContent("Freedom to parrots!!!");
        doc.setType("declaration");
        return doc;
    }

    private HttpEntity<Document> createHttpEntity(Document entity) {
        return new HttpEntity<>(entity, headers);
    }
}
