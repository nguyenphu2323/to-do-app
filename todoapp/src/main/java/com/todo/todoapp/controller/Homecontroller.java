package com.todo.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Homecontroller {
    @GetMapping("/")
    public String getHome(Model model) {
        return "index";
    }

}
