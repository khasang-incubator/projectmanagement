package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.ProjectDao;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Project;

public class ProjectDaoImpl extends BasicDaoImpl<Project> implements ProjectDao {
    public ProjectDaoImpl(Class<Project> entityClass) {
        super(entityClass);
    }
}
