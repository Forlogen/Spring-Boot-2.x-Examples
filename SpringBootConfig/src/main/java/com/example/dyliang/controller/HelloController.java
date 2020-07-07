package com.example.dyliang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${person.last_name}")
    public String name;

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello" + name;
    }
}
