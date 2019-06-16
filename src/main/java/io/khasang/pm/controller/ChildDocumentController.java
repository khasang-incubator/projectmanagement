package io.khasang.pm.controller;

import io.khasang.pm.entity.ChildDocument;
import io.khasang.pm.service.ChildDocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/childDocument")
public class ChildDocumentController {
    private ChildDocumentService childDocumentService;

    public ChildDocumentController(ChildDocumentService childDocumentService) {
        this.childDocumentService = childDocumentService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ChildDocument addChildDocument(@RequestBody ChildDocument childDocument) {
        return childDocumentService.add(childDocument);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ChildDocument updateChildDocument(@RequestBody ChildDocument childDocument) {
        return childDocumentService.update(childDocument);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public ChildDocument deleteChildDocument(@PathVariable("id") long id) {
        return childDocumentService.delete(id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ChildDocument getChildDocument(@PathVariable("id") long id) {
        return childDocumentService.getById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<ChildDocument> getAllChildDocuments() {
        return childDocumentService.getAllChildDocuments();
    }
}
