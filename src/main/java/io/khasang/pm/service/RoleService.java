package io.khasang.pm.service;

import io.khasang.pm.entity.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);

    Role getById(long id);

    List<Role> getAll();
}
