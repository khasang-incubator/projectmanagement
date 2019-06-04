package io.khasang.pm.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "doc_id")
    private long id;
    private String docNumber;
    private Date dateDoc;
    private String author;
    private String type;
    private String content;

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

    public Date getDateDoc() {
        return dateDoc;
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
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
