package io.khasang.pm.dao;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Project;

import java.util.List;

public interface ProjectDao extends BasicDao<Project> {
    /**
     * method for receiving projects by name
     *
     * @param name - projects name for query
     * @return project's by name  
     */
    List<Project> getByName(String name);
}
