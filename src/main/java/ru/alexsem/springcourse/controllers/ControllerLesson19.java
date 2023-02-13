package ru.alexsem.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class ControllerLesson19 {
    @GetMapping("/parameter")
    public String helloPage(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println("Hello " + name + " " + surname);
        return "first/hello";
    }
    
    //    Or using annotations:
    @GetMapping("/newparameter")
    public String helloPageNew(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "surname", required = false) String surname) {
        System.out.println("Hello " + name + " " + surname);
        return "first/hello";
    }
}
