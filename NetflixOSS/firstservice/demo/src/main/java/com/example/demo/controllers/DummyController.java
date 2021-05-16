package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class DummyController {
    
    @Value("${server.port}")
    private String port;

    @GetMapping(value="dummy1")
    public String getMethodName() {
        return "dummy1 puerto => " + port;
    }
    
}
