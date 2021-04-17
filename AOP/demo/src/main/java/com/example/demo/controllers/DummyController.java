package com.example.demo.controllers;

import com.example.demo.services.DummyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class DummyController {

    @Autowired
    DummyService service;

    @GetMapping(value="dummy")
    public String getMethodName() {
        return service.GetDummyValue("valor dummy");
    }
    
}
