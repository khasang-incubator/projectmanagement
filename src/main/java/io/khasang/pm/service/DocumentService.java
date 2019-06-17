package io.khasang.pm.service;

import io.khasang.pm.dto.DocumentDto;
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
     * getting specify documentDto by ID
     *
     * @param id - document's id for receiving
     * @return documentDto by id
     */
    DocumentDto getById(long id);

    /**
     * getting all documentDto
     *
     * @return all documentDto from DB
     */
    List<DocumentDto> getAll();

    /**
     * updating document in db
     *
     * @param document - document for updating
     * @return updated document
     */
    DocumentDto update(Document document);

    /**
     * deleting document by id from db
     *
     * @param id - id document's for deletint
     * @return deleting document
     */
    DocumentDto delete(long id);
}
