package io.khasang.pm.service.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.ProjectDao;
import io.khasang.pm.dto.ProjectDto;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Employee;
import io.khasang.pm.entity.Project;
import io.khasang.pm.service.CatService;
import io.khasang.pm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;
    private ProjectDto projectDto;

    @Autowired
    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    @Override
    public Project add(Project project) {
        return projectDao.add(project);
    }

    @Override
    public ProjectDto getById(long id) {
        return projectDto.getProjectDto(projectDao.getById(id));
    }

    @Override
    public List<ProjectDto> getAll() {
        List<Project> projects = projectDao.getAll();
        List<ProjectDto> projectDtos = new ArrayList<>();

        for (Project p : projects) {
            projectDtos.add(projectDto.getProjectDto(p));
        }

        return projectDtos;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
}
