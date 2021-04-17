package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class BeforeAspect {
    
    @Before("execution(* com.example.demo.services.*.*(..))")
    public void ArgumentsLoggerAspect(JoinPoint joinPoint){
        log.info("My awesome Before Aspect!!!!!!!!!");
        log.info("-> {} ", joinPoint);
        log.info("  -> args: {} ", joinPoint.getArgs());
        log.info("  -> sourcelocation: {} ", joinPoint.getSourceLocation());
    }
}
