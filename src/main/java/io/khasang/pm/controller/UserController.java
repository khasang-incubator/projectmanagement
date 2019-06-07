package io.khasang.pm.controller;

import io.khasang.pm.entity.User;
import io.khasang.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "/get/{login}", method = RequestMethod.GET)
    @ResponseBody
    public User getById(@PathVariable("login") String login) {
        return userService.getByLogin(login);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAll(){
        return userService.getAll();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}