package com.example.demo;

import com.example.demo.models.Greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HelloController {
    
    @GetMapping(value="hello")
    public String getMethodName() {
        return "Hello!!!!";
    }
 
//    http://algo.com/account/1?pagesize=10&page=2

    @GetMapping(value="echo/{message}")
    public String getMethodName(@PathVariable String message) {
        return message;
    }
    
    @GetMapping(value="echoObject/{message}")
    public Greeting getMethodNameEcho(@PathVariable String message) {

        Greeting greet = new Greeting();
        greet.setMessage(message);

        var greet2 = new Greeting();
        greet2.setMessage(message);

        return greet;
    }

    @PostMapping(value="echoObject")
    public Greeting postMethodName(@RequestBody Greeting entity) {        
        return entity;
    }
    
}
