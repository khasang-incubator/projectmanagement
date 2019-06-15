package io.khasang.pm.controller;

import io.khasang.pm.entity.Role;
import io.khasang.pm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
// localhost:8080/role
public class RoleController {
    private RoleService roleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Role addRole(@RequestBody Role role){
        return roleService.add(role);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Role getById(@PathVariable("id") long id) {
        return roleService.getById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @Autowired
    public void setRoleService(RoleService roleService){
        this.roleService = roleService;
    }
}