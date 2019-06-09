package io.khasang.pm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cat_id")
    private long id;

    private String name;
    private String description;

   // @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<CatWoman> catWomen = new ArrayList<>();

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

    public List<CatWoman> getCatWomen() {
        return catWomen;
    }

    public void setCatWomen(List<CatWoman> catWomen) {
        this.catWomen = catWomen;
    }
}
