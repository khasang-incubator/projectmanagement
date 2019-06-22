package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.entity.Cat;

import java.util.List;

public class CatDaoImpl extends BasicDaoImpl<Cat> implements CatDao {
    public CatDaoImpl(Class<Cat> entityClass) {
        super(entityClass);
    }

    public List<Cat> getByName(String name) {
//        CriteriaBuilder builder = getSession().getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(Cat.class);
//        Root<Cat> root = query.from(Cat.class);
//
//        query.select(root);
//        query.where(builder.equal(root.get("name"), name));
//
//        TypedQuery<Cat> typedQuery = getSession().createQuery(query);
//        return typedQuery.getResultList();

        List<Cat> list = getSession().createQuery("from Cat where name = ?1")
                .setParameter(1, name).list();
        return list;
    }
}
