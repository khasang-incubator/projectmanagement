package io.khasang.pm.controller;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class Rabbit {
    private String name;
    private int age;

    public Rabbit() {
    }

    public Rabbit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // work only with singleton scope
    @PreDestroy
    public void cleanUp() {
        System.err.println("Rabbit is gone!");
    }

    @PostConstruct
    public void init() {
        System.err.println("Rabbit is coming...");
    }
}
