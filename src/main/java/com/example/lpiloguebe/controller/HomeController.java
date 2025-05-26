package com.example.lpiloguebe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/be")
    public String home() {
        return "server is running";
    }
}
