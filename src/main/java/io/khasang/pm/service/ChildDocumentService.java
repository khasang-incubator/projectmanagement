package io.khasang.pm.service;

import io.khasang.pm.entity.ChildDocument;

import java.util.List;

public interface ChildDocumentService {
    ChildDocument add(ChildDocument childDocument);

    ChildDocument update(ChildDocument childDocument);

    ChildDocument delete(long id);

    ChildDocument getById(long id);

    List<ChildDocument> getAllChildDocuments();
}
