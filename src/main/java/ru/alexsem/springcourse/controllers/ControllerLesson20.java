package ru.alexsem.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class ControllerLesson20 {
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a,
                             @RequestParam("b") int b,
                             @RequestParam("action") String action,
                             Model model) {
        
        double result = switch (action) {
            case "multiplication" -> a * b;
            case "div" -> (double) a / b;
            default -> 0;
        };
        model.addAttribute("theResult",result);
        return "first/calculator";
    }
}
