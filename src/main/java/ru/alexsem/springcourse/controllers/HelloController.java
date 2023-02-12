package ru.alexsem.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
//    Get - запрос
    @GetMapping("/hello1-world")
    public String sayHello() {
        return "hello_world";
    }
}
