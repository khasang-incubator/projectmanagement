package io.khasang.pm.controller;

public class Cat implements Animal {
    private String name;
    private String secondName;

    public Cat() {
    }

    public Cat(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
