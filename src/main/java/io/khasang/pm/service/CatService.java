package io.khasang.pm.service;

import io.khasang.pm.entity.Cat;

import java.util.List;

public interface CatService {
    Cat add(Cat cat);

    Cat getById(long id);

    List<Cat> getAll();
}
