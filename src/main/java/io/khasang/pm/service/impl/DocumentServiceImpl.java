package io.khasang.pm.service.impl;

import io.khasang.pm.dao.DocumentDao;
import io.khasang.pm.entity.Document;
import io.khasang.pm.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    private DocumentDao documentDao;

    @Override
    public Document add(Document document) {
        return documentDao.add(document);
    }

    @Override
    public Document getById(long id) {
        return documentDao.getById(id);
    }

    @Override
    public List<Document> getAll() {
        return documentDao.getAll();
    }

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
}
