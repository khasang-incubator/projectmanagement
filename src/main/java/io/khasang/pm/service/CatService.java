package io.khasang.pm.service;

import io.khasang.pm.entity.Cat;

import java.util.List;

public interface CatService {
    /**
     * required for adding cats to db
     *
     * @param cat - cat for adding
     * @return added cat
     */
    Cat add(Cat cat);

    /**
     * getting specify cat by ID
     *
     * @param id - cat's id for receiving
     * @return cat by id
     */
    Cat getById(long id);

    /**
     * getting all cats
     *
     * @return all cats from DB
     */
    List<Cat> getAll();
}
