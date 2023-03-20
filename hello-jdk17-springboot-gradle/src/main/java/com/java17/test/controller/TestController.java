package com.java17.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @RequestMapping("/")
    public String index() {
        return "hello, Java17 Spring 3.0.2 world!";
    }
    
}
