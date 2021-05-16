package com.example.demo.controllers;

import com.example.demo.services.BotService;
import com.example.demo.services.Dummy1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class DummyController {
    
    @Value("${server.port}")
    private String port;

    @Autowired
    BotService botService;

    @Autowired
    Dummy1Service dummy1Service;

    @GetMapping(value="dummy2")
    public String getMethodName() {
        return "dummy2 port => " + port;
    }
    
    @GetMapping(value = "/bot/{message}")
    public String getBotResponse(@PathVariable String message){
        return this.botService.getBotResponse(message);
    }

    @GetMapping(value = "/feigndummy1")
    public String callFirstService(){
        return this.dummy1Service.getMethodName();
    }
}
