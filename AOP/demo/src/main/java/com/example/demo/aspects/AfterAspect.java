package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class AfterAspect {
    
    @AfterReturning(value = "execution(* com.example.demo.services.*.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        log.info("{} regresa con -> {}", joinPoint, result);
    }
}
