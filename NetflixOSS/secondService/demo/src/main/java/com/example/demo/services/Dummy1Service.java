package com.example.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="first-service")
public interface Dummy1Service {
    
    @GetMapping(value="dummy1")
    @CircuitBreaker(name = "service1", fallbackMethod = "fallback")
    public String getMethodName();

    default String fallback(Exception e){
        return "error";
    }
}
