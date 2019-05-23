package io.khasang.pm.controller;

import io.khasang.pm.model.*;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/select_left_join")
    public String selectFromTableWithLeftJoin(Model model) {
        model.addAttribute("status", selectFromTable.getTableSelectionStatusWithLeftJoin());
        return "queryStatus";
    }

    @RequestMapping("/select_right_join")
    public String selectFromTableWithRightJoin(Model model) {
        model.addAttribute("status", selectFromTable.getTableSelectionStatusWithRightJoin());
        return "queryStatus";
    }

    @RequestMapping("/select_full_join")
    public String selectFromTableWithFullJoin(Model model) {
        model.addAttribute("status", selectFromTable.getTableSelectionStatusWithFullJoin());
        return "queryStatus";
    }

    @RequestMapping("/user")
    public String getUserPage(Model model) {
        return "user";
    }

    @RequestMapping("/admin")
    public String getAdminPage(Model model) {
        return "admin";
    }

    //localhost:8080/password/admin
    @RequestMapping("/password/{password}")
    public String getEncryptPassword(@PathVariable("password") String password, Model model) {
        model.addAttribute("password", password);
        model.addAttribute("passwordAfterEncode", new BCryptPasswordEncoder().encode(password));
    return "password";
    }
}
