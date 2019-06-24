package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.RoleDao;
import io.khasang.pm.entity.Role;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }
}