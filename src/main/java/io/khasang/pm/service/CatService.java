package io.khasang.pm.service;

import io.khasang.pm.entity.Cat;

import java.util.List;

public interface CatService {
   /**
    *
    * required for adding cats to db
    *
    * @param cat
    * @return added cat
   */
    public Cat add(Cat cat);
    /**
     *
     * getting specify cat by id
     *
     * @param id
     * @return cat by id
     */
    public Cat getById(long id);

    /**
     * gettint all cats
     *
     * @return list of all cats
     */
    public List<Cat> getAll();

}
