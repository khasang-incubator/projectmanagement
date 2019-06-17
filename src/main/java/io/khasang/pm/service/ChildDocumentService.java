package io.khasang.pm.service;

import io.khasang.pm.entity.ChildDocument;

import java.util.List;

public interface ChildDocumentService {
    /**
     * required for adding childDocument to db
     *
     * @param childDocument - childDocument for adding
     * @return added childDocument
     */
    ChildDocument add(ChildDocument childDocument);

    /**
     * required for updating childDocument in db
     *
     * @param childDocument - childDocument for update
     * @return updated childDocument
     */
    ChildDocument update(ChildDocument childDocument);

    /**
     * required for deleting childDocument from db
     *
     * @param id - childDocument's id for deleting
     * @return deleted childDocument
     */
    ChildDocument delete(long id);

    /**
     * getting specify childDocument by ID from db
     *
     * @param id - childDocument's id for receiving
     * @return childDocument by id
     */
    ChildDocument getById(long id);

    /**
     * getting all childDocuments
     *
     * @return all childDocument from db
     */
    List<ChildDocument> getAllChildDocuments();
}
