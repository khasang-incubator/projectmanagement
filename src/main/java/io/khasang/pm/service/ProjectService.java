package io.khasang.pm.service;

import io.khasang.pm.dto.ProjectDto;
import io.khasang.pm.entity.Project;

import java.util.List;

public interface ProjectService {
    /**
     * required for adding project to db
     *
     * @param project - project for adding
     * @return added project
     */
    Project add(Project project);

    /**
     * getting specify project by ID
     *
     * @param id - project's id for receiving
     * @return project by id
     */
    ProjectDto getById(long id);

    /**
     * getting all projects
     *
     * @return all projects from DB
     */
    List<ProjectDto> getAll();

    /**
     * updating project in db
     *
     * @param project - project for updating
     * @return updated project
     */
    ProjectDto update(Project project);

    /**
     * deleting project by id from db
     *
     * @param id - id project's for deleting
     * @return deleting project
     */
    ProjectDto delete(long id);
}
