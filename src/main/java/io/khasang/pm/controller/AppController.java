package io.khasang.pm.controller;

import io.khasang.pm.model.Queries;
import io.khasang.pm.model.SimpleQueries;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


// MVC - Spring MVC
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

    @RequestMapping("/work-with-cat")
    public String getHelloPage() {
        return "work-with-cat";
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

    @RequestMapping("/delete")
    public String deleteFromTable(Model model){
        model.addAttribute("status", simpleQueries.deleteData());
        return "result";
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
        model.addAttribute("passwordAfterEncrypt", new BCryptPasswordEncoder().encode(password));
        return "password";
    }
}
