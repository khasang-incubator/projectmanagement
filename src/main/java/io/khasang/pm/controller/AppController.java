package io.khasang.pm.controller;

import io.khasang.pm.model.CreateTable;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// MVC - Spring MVC
@Controller
@ImportResource(value = "classpath:ioc.xml")
public class AppController {
    private Rabbit rabbit;
    private CreateTable createTable;

    public AppController(Rabbit rabbit, CreateTable createTable) {
        this.rabbit = rabbit;
        this.createTable = createTable;
    }

    @RequestMapping("/")
    public String getHelloPage (Model model){
        model.addAttribute("name", rabbit.getName());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable (Model model){
        model.addAttribute("status", createTable.getTableCreationStatus());
        return "create";
    }

    @RequestMapping("/user")
    public String getUserPage(){
        return "user";
    }

    @RequestMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @RequestMapping("/password/{password}")
    public String getEncryptPassword(@PathVariable("password") String password, Model model){
        model.addAttribute("password", password);
        model.addAttribute("passwordAfterEncode", new BCryptPasswordEncoder().encode(password));
        return "password";
    }
}