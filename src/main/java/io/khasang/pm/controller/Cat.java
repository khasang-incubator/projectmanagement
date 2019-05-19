package io.khasang.pm.controller;

import org.springframework.stereotype.Service;

@Service
public class Cat implements Animal {

    private String name;
    private String color;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
