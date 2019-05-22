package io.khasang.pm.controller;

import io.khasang.pm.model.CreateTable;
import io.khasang.pm.model.SelectTable;
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
    private SelectTable selectTable;
    private Rabbit rabbit;

    public AppController(SelectTable selectTable,CreateTable createTable, Rabbit rabbit) {
        this.createTable = createTable;
        this.rabbit = rabbit;
        this.selectTable = selectTable;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model) {

        //something doc
        model.addAttribute("name", rabbit.getName());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model){
        model.addAttribute("status",createTable.getTableCreationStatus());
        return "create";
    }

    @RequestMapping("/select")
    public String selectTable(Model model){
        model.addAttribute("status",selectTable.getTableSelectStatus());
        return "select";
    }

    @RequestMapping("/user")
    public String getUserPage(){
        return "user";
    }

    @RequestMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }
    //localhost:8080/password/admin
    @RequestMapping("/password/{password}")
    public String getEncryptPassword(@PathVariable("password") String password, Model model){
        model.addAttribute("password",password);
        model.addAttribute("passwordAfterEncode",new BCryptPasswordEncoder().encode(password));
        return "password";
    }

}
