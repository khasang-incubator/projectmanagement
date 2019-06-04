package io.khasang.pm.dao.impl;

import io.khasang.pm.dao.UserDao;
import io.khasang.pm.entity.User;

public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    public UserDaoImpl(Class<User> entityClass) {
        super(entityClass);
    }
}
