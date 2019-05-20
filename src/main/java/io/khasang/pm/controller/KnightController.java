package io.khasang.pm.controller;

import io.khasang.pm.service.KnightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/knight")
@Controller
public class KnightController {
    private KnightService knightService;

    public KnightController(KnightService knightService) {
        this.knightService = knightService;
    }

    @RequestMapping("/action")
    public String getKnightAction(Model model) {
        model.addAttribute("action", knightService.getAchievement("goblin"));
        return "knight";
    }
}
