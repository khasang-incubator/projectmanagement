package io.khasang.pm.controller;

import io.khasang.pm.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    CreateTable createTable;

    public AppController(CreateTable createTable) {
        this.createTable = createTable;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model) {
        model.addAttribute("name", "Hello my first spring app!");
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model){
        model.addAttribute("status", createTable.getTableCreationStatus());
        return "create";
    }
}
