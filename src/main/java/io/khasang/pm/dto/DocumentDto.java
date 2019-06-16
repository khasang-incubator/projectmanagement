package io.khasang.pm.dto;

import io.khasang.pm.entity.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DocumentDto {
    private long id;
    private String docNumber;
    private LocalDate docDate;
    private String author;
    private String type;
    private String content;

    public DocumentDto getDocumentDto(Document document) {
        DocumentDto documentDto = new DocumentDto();
        documentDto.setId(document.getId());
        documentDto.setDocNumber(document.getDocNumber());
        documentDto.setDocDate(document.getDocDate());
        documentDto.setAuthor(document.getAuthor());
        documentDto.setType(document.getType());
        documentDto.setContent(document.getContent());
        return documentDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
