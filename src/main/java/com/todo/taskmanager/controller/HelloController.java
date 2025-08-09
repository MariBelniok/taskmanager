package com.todo.taskmanager.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld(@RequestParam(value = "name", defaultValue = "Spring") String name) {
        return String.format("Hello, %s!", name);
    }
}
