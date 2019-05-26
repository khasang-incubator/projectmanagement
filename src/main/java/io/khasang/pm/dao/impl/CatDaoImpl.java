package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.entity.Cat;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao{
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }
}
