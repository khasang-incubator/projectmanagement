package io.khasang.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// MVC - Spring MVC
@Controller
@ImportResource(value = "classpath:ioc.xml")
public class AppController {


    private Rabbit rabbit = new Rabbit();

    public AppController(Rabbit rabbit) {
        this.rabbit = rabbit;
    }

    @RequestMapping("/")
    public String getHelloPage(Model model) {

        //something doc
        model.addAttribute("name", rabbit.getName());
        return "hello";
    }

}
