package io.khasang.pm.service.impl;

import io.khasang.pm.dao.ChildDocumentDao;
import io.khasang.pm.entity.ChildDocument;
import io.khasang.pm.service.ChildDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("childDocumentService")
public class ChildDocumentServiceImpl implements ChildDocumentService {
    private ChildDocumentDao childDocumentDao;

    @Override
    public ChildDocument add(ChildDocument childDocument) {
        return childDocumentDao.add(childDocument);
    }

    @Override
    public ChildDocument update(ChildDocument childDocument) {
        return childDocumentDao.update(childDocument);
    }

    @Override
    public ChildDocument delete(long id) {
        return childDocumentDao.delete(getById(id));
    }

    @Override
    public ChildDocument getById(long id) {
        return childDocumentDao.getById(id);
    }

    @Override
    public List<ChildDocument> getAllChildDocuments() {
        return childDocumentDao.getAll();
    }

    @Autowired
    public void setChildDocumentDao(ChildDocumentDao childDocumentDao) {
        this.childDocumentDao = childDocumentDao;
    }
}
