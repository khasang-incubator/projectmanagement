package io.khasang.pm.service.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.ProjectDao;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Project;
import io.khasang.pm.service.CatService;
import io.khasang.pm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    private ProjectDao projectDao;

    @Override
    public Project add(Project project) {
        return projectDao.add(project);
    }

    @Override
    public Project getById(long id) {
        return projectDao.getById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectDao.getAll();
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
}
