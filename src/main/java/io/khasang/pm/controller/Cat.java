package io.khasang.pm.controller;

import org.springframework.stereotype.Service;

@Service
public class Cat implements Animal {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
