package io.khasang.pm.dao;

import java.util.List;

public interface BasicDao<T> {
    public T add(T entity);
    public T getById(long id);
    public List<T> getAll();
}
