package io.khasang.pm.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    private String name;

    @Id
    @Column(unique = true, nullable = false)
    private String login;
    private String password;
    private String function;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
