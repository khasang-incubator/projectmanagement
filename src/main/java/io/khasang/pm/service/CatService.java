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

    /**
     * getting specify cat by name
     *
     * @param name - cat's name for receiving
     * @return cat by name
     */
    List<Cat> getByName(String name);

    /**
     * required for updating cats into db
     *
     * @param cat - cat for update
     * @return updated cat
     */
    Cat update(Cat cat);

    /**
     * delete specify cat by ID
     *
     * @param id - cat's id for deletion
     * @return deleted cat by id
     */
    Cat delete(long id);
}
