package io.khasang.pm.dao;

import io.khasang.pm.entity.Cat;

import java.util.List;

public interface CatDao extends BasicDao<Cat> {
    /**
     * method for receiving cats by name
     *
     * @param name - cats name for query
     * @return cat's by name  
     */
    List<Cat> getByName(String name);
}
