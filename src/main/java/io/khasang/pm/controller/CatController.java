package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cat")
public class CatController {
    private CatService catService;

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Cat addCat(@RequestBody Cat cat){
        return catService.add(cat);
    }

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }
}
