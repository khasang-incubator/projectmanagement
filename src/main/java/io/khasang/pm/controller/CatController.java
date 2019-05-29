package io.khasang.pm.controller;

import io.khasang.pm.entity.Cat;
import io.khasang.pm.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cat")
public class CatController {
    private CatService catService;

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Cat addCat(@RequestBody Cat cat){
        return catService.add(cat);
       // return catService.saveOrUpdate(cat);
    }

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Cat getById(@PathVariable("id") long id){
        return catService.getById(id);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Cat> getAll(){
        return catService.getAll();
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Cat update(@PathVariable("id") long id){
        try {
            Cat updatedCat = catService.getById(id);
            updatedCat.setName("новый " + updatedCat.getName());
            return catService.saveOrUpdate(updatedCat);
        } catch (NullPointerException npe) {

        }
        return null;
    }

    @Autowired
    public void setCatService(CatService catService) {
        this.catService = catService;
    }
}
