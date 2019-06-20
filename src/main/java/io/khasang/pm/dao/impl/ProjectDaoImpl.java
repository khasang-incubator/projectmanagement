package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.ProjectDao;
import io.khasang.pm.entity.Project;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProjectDaoImpl extends BasicDaoImpl<Project> implements ProjectDao {
    public ProjectDaoImpl(Class<Project> entityClass) {
        super(entityClass);
    }

    public List<Project> getByName(String name){
//        CriteriaBuilder builder = getSession().getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(Project.class);
//        Root<Project> root = query.from(Project.class);
//
//        query.select(root);
//        query.where(builder.equal(root.get("name"), name));
//
//        TypedQuery<Project> typedQuery = getSession().createQuery(query);
//        return typedQuery.getResultList();

        List<Project> list = getSession().createQuery("from Project where name = ?1")
                .setParameter(1
                ,name).list();
        return list;
    }
}
