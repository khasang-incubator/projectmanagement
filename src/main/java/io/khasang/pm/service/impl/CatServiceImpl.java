package io.khasang.pm.service.impl;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("catService")
public class CatServiceImpl implements CatService {
    private CatDao catDao;

    @Override
    public Cat add(Cat cat) {
        return catDao.add(cat);
    }

    @Override
    public Cat update(Cat cat) {
        return catDao.update(cat);
    }

    @Override
    public Cat delete(long id) {
        return catDao.delete(getByID(id));
    }

    @Override
    public Cat getByID(long id) {
        return catDao.getById(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catDao.getAll();
    }

    @Autowired
    public void setCatDao(CatDao catDao) {
        this.catDao = catDao;
    }
}
