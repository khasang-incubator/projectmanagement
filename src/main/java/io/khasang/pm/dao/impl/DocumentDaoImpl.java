package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.DocumentDao;
import io.khasang.pm.entity.Document;

public class DocumentDaoImpl extends BasicDaoImpl<Document> implements DocumentDao {
    public DocumentDaoImpl(Class<Document> entityClass) {
        super(entityClass);
    }
}
