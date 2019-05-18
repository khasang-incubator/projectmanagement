package io.khasang.pm.controller;

import io.khasang.pm.model.Queries;
import io.khasang.pm.model.SimpleQueries;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    private Queries simpleQueries;

    public AppController(Queries simpleQueries) {
        this.simpleQueries = simpleQueries;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model) {
        model.addAttribute("name", "Hello my first spring app!");
        return "hello";
    }

    @RequestMapping("/create")
    public String createTable(Model model){
        model.addAttribute("status", simpleQueries.getTableCreationStatus());
        return "result";
    }

    @RequestMapping("/select")
    public String selectInTable(Model model){
        model.addAttribute("status", simpleQueries.getSelectionStatus());
        return "result";
    }

    @RequestMapping("/insert")
    public String insertToTable(Model model){
        model.addAttribute("status", simpleQueries.insertData());
        return "result";
    }

    @RequestMapping("/update")
    public String updateTable(Model model){
        model.addAttribute("status", simpleQueries.updateData());
        return "result";
    }
}
