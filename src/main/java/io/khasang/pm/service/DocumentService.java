package io.khasang.pm.service;

import io.khasang.pm.entity.Document;
import java.util.List;

public interface DocumentService {
    /**
     * required for adding document to db
     *
     * @param document - document for adding
     * @return added document
     */
    Document add(Document document);

    /**
     * getting specify document by ID
     *
     * @param id - document's id for receiving
     * @return document by id
     */
    Document getById(long id);

    /**
     * getting all document
     *
     * @return all document from DB
     */
    List<Document> getAll();
}
