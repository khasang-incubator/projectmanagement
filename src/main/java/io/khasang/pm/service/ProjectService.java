package io.khasang.pm.service;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Project;

import java.util.List;

public interface ProjectService {
    /**
     * required for adding cats to db
     *
     * @param project - cat for adding
     * @return added cat
     */
    Project add(Project project);

    /**
     * getting specify cat by ID
     *
     * @param id - cat's id for receiving
     * @return cat by id
     */
    Project getById(long id);

    /**
     * getting all cats
     *
     * @return all cats from DB
     */
    List<Project> getAll();
}
