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

    private CreateTable createTable;

    public AppController(CreateTable createTable) {
        this.createTable = createTable;
    }

    @RequestMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @RequestMapping("/")
    public String getHelloPage() {
        return "cat";
    }

    @RequestMapping("/doc")
    public String workWithDoc() {
        return "doc";
    }

    @RequestMapping("/create")
    public String createTable(Model model) {
        model.addAttribute("status", createTable.getTableCreationStatus());
        return "create";
    }

    @RequestMapping("/user")
    public String getUserPage() {
        return "user";
    }

    // localhost:8080/password/admin
    @RequestMapping("/password/{password}")
    public String getEncryptPassword(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", password);
        model.addAttribute("passwordAfterEncode", new BCryptPasswordEncoder().encode(password));
        return "password";
    }
}
