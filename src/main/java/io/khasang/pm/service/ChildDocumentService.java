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
     * required for updating childDocument into db
     *
     * @param childDocument - childDocument for updating
     * @return updated childDocument
     */
    ChildDocument update(ChildDocument childDocument);

    /**
     * required for deleting childDocument by ID from db
     *
     * @param id - childDocument for deleting
     * @return deleted childDocument
     */
    ChildDocument delete(long id);

    /**
     * getting specify childDocument by ID
     *
     * @param id - childDocument's id for receiving
     * @return childDocument by id
     */
    ChildDocument getById(long id);

    /**
     * getting all childDocuments
     *
     * @return all childDocuments from db
     */
    List<ChildDocument> getAllChildDocuments();
}
