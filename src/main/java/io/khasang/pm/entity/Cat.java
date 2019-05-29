package io.khasang.pm.entity;

import io.khasang.pm.controller.Animal;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
  //  @ManyToOne
   // private Color catColor;
    private String description;

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
/*
    public Color getCatColor() {
        return catColor;
    }

    public void setCatColor(Color catColor) {
        this.catColor = catColor;
    }
*/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
