package io.khasang.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// MVC - Spring MVC
@Controller
public class AppController {
    @Autowired
    @Qualifier("dog")
    private Animal animal;

    // http://localhost:8080/cat
    @RequestMapping("/")
    public String getHelloPage(Model model) {
        model.addAttribute("name", "Hello my first spring app!");
        return "hello";
    }

}
