package io.khasang.pm.dao;

import java.util.List;

public interface BasicDao<T> {
    /**
     * required for adding entities to db
     *
     * @param entity - entity for adding
     * @return added entity
     */
    T add(T entity);

    T update(T entity);

    T delete(T entity);

    /**
     * getting specify entity by ID
     *
     * @param id - entity's id for receiving
     * @return entity by id
     */
    T getById(long id);

    /**
     * getting all entities
     *
     * @return all entities from DB
     */
    List<T> getAll();
}
