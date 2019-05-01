package io.khasang.pm.controller;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

@Service
public class Dog implements Animal, DisposableBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void destroy() throws Exception {

    }
}
