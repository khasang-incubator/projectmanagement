package io.khasang.pm.service;

import io.khasang.pm.entity.Cat;

import java.util.List;

public interface CatService {

    Cat add(Cat cat);

    Cat update(Cat cat);

    Cat delete(long id);

    Cat getById(long id);

    List<Cat> getAllCat();
}
