package io.khasang.pm.controller;

import io.khasang.pm.model.*;
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
    private DeleteFromTable deleteFromTable;
    private UpdateTable updateTable;

    private Rabbit rabbit;

    public AppController(CreateTable createTable, InsertIntoTable insertIntoTable, SelectFromTable selectFromTable, DeleteFromTable deleteFromTable, UpdateTable updateTable, Rabbit rabbit) {
        this.createTable = createTable;
        this.insertIntoTable = insertIntoTable;
        this.selectFromTable = selectFromTable;
        this.deleteFromTable = deleteFromTable;
        this.updateTable = updateTable;
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
        return "queryStatus";
    }

    @RequestMapping("/insert")
    public String insertIntoTable(Model model) {
        model.addAttribute("status", insertIntoTable.getTableInsertionStatus());
        return "queryStatus";
    }

    @RequestMapping("/delete")
    public String deleteFromTable(Model model) {
        model.addAttribute("status", deleteFromTable.getTableDeletingStatus());
        return "queryStatus";
    }

    @RequestMapping("/update")
    public String updateTable(Model model) {
        model.addAttribute("status", updateTable.getTableUdtatingStatus());
        return "queryStatus";
    }

    @RequestMapping("/select")
    public String selectFromTable(Model model) {
        model.addAttribute("status", selectFromTable.getTableSelectionStatus());
        return "queryStatus";
    }

    @RequestMapping("/select_inner_join")
    public String selectFromTableWithInnerJoin(Model model) {
        model.addAttribute("status", selectFromTable.getTableSelectionStatusWithInnerJoin());
        return "queryStatus";
    }
}
