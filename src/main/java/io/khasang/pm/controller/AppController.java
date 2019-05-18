package io.khasang.pm.controller;

import io.khasang.pm.model.CreateTable;
import io.khasang.pm.model.InsertIntoTable;
import io.khasang.pm.model.SelectFromTable;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// MVC - Spring MVC
@Controller
@ImportResource(value = "classpath:ioc.xml")
public class AppController {

    private CreateTable createTable;
    private InsertIntoTable insertIntoTable;
    private SelectFromTable selectFromTable;
    private Rabbit rabbit;

    public AppController(CreateTable createTable, InsertIntoTable insertIntoTable, SelectFromTable selectFromTable, Rabbit rabbit) {
        this.createTable = createTable;
        this.insertIntoTable = insertIntoTable;
        this.selectFromTable = selectFromTable;
        this.rabbit = rabbit;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model) {

        //something doc
        model.addAttribute("name", rabbit.getName());
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model) {
        model.addAttribute("status", createTable.getTableCreartionStatus());
        return "create";
    }

    @RequestMapping("/insert")
    public String insertTable(Model model) {
        model.addAttribute("status", insertIntoTable.getTableInsertionStatus());
        return "insert";
    }

    @RequestMapping("/select")
    public String selectTable(Model model) {
        model.addAttribute("status", selectFromTable.getTableSelectionStatus());
        return "select";
    }
}
