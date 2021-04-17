package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.services.DummyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DummyController {

    @Autowired
    DummyService service;

    @GetMapping(value="dummy")
    public String getMethodName() {
        return service.GetDummyValue("valor dummy");
    }
    
    @PostMapping(value="card")
    public Card postMethodName(@RequestBody Card entity) {
        //TODO: process POST request
        
        return service.SaveCard(entity);
    }
    
}
