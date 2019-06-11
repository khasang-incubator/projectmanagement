package io.khasang.pm.controller;

import io.khasang.pm.dto.ProjectDto;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Project;
import io.khasang.pm.service.CatService;
import io.khasang.pm.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Project addProject(@RequestBody Project project) {
        return projectService.add(project);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProjectDto getById(@PathVariable("id") long id) {
        return projectService.getById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectDto> getAll(){
        return projectService.getAll();
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
