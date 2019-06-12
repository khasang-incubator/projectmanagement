package io.khasang.pm.service.impl;

import io.khasang.pm.dao.RoleDao;
import io.khasang.pm.entity.Role;
import io.khasang.pm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getById(long id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Autowired
    public void setRoleDao (RoleDao roleDao){
        this.roleDao = roleDao;
    }
}
