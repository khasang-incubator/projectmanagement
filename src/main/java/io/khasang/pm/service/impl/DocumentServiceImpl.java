package io.khasang.pm.service.impl;

import io.khasang.pm.dao.DocumentDao;
import io.khasang.pm.dto.DocumentDto;
import io.khasang.pm.entity.Document;
import io.khasang.pm.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    private DocumentDao documentDao;
    private DocumentDto documentDto;

    @Override
    public Document add(Document document) {
        return documentDao.add(document);
    }

    @Override
    public DocumentDto getById(long id) {
        return documentDto.getDocumentDto(documentDao.getById(id));
    }

    @Override
    public List<DocumentDto> getAll() {
        List<Document> documents = documentDao.getAll();
        List<DocumentDto> documentDtos = new ArrayList<>();

        for (Document document : documents) {
            documentDtos.add(documentDto.getDocumentDto(document));
        }

        return documentDtos;
    }

    @Override
    public DocumentDto update(Document document) {
        return documentDto.getDocumentDto(documentDao.update(document));
    }

    @Override
    public DocumentDto delete(long id) {
        return documentDto.getDocumentDto(documentDao.delete(documentDao.getById(id)));
    }

    @Autowired
    public void setDocumentDao(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @Autowired
    public void setDocumentDto(DocumentDto documentDto) {
        this.documentDto = documentDto;
    }
}
