package io.khasang.pm.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Rabbit {
    private String name;
    private int age;

    public Rabbit() {
    }

    public Rabbit(String s) {
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

    @PreDestroy
    public void cleanUp(){
        System.err.println("Кролик убежал!");
    }

    @PostConstruct
    public void init(){
        System.err.println("Кролик приходит...");
    }
}
