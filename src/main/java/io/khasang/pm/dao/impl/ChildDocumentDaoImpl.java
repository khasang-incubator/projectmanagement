package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.ChildDocumentDao;
import io.khasang.pm.entity.ChildDocument;

public class ChildDocumentDaoImpl extends BasicDaoImpl<ChildDocument> implements ChildDocumentDao {
    public ChildDocumentDaoImpl(Class<ChildDocument> entityClass) {
        super(entityClass);
    }
}
