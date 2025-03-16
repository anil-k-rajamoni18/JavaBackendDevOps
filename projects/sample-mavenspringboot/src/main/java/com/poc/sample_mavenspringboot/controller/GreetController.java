package com.poc.sample_mavenspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/greet")
public class GreetController {

    @GetMapping("/{userName}")
    public String greetUser(@PathVariable String userName) {
        return String.format("Hello %s , Welcome to Maven Spring Boot Project <o_o> ", userName);
    }
}
