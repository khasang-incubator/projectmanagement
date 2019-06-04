package io.khasang.pm.service;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.User;

import java.util.List;

public interface UserService {
    /**
     * required for adding cats to db
     *
     * @param user - user for adding
     * @return added user
     */
    User add(User user);

    /**
     * getting specify user by ID
     *
     * @param id - user's id for receiving
     * @return user by id
     */
    User getById(long id);

    /**
     * getting all users
     *
     * @return all users from DB
     */
    List<User> getAll();
}
