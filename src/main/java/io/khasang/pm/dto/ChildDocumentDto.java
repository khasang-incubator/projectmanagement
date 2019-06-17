package io.khasang.pm.dto;

import io.khasang.pm.entity.ChildDocument;

public class ChildDocumentDto {
    private long id;
    private String name;
    private String description;

    public ChildDocumentDto getChildDocumentDto(ChildDocument childDocument) {
        ChildDocumentDto childDocumentDto = new ChildDocumentDto();
        childDocumentDto.setId(childDocument.getId());
        childDocumentDto.setName(childDocument.getName());
        childDocumentDto.setDescription(childDocument.getDescription());
        return childDocumentDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
