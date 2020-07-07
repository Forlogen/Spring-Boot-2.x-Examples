package com.example.dyliang.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllerNew {

    @GetMapping("/abc")
    public String hello(Model model){
        model.addAttribute("msg", "hello Forlogen");
        return "success";
    }
}
