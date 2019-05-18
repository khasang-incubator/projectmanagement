package io.khasang.pm.controller;

import io.khasang.pm.model.CreateTable;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// MVC - Spring MVC
@Controller
@ImportResource(value = "classpath:ioc.xml")
public class AppController {

    private CreateTable createTable;
    private Rabbit rabbit;

    public AppController(CreateTable createTable, Rabbit rabbit) {
        this.createTable = createTable;
        this.rabbit = rabbit;
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

}
