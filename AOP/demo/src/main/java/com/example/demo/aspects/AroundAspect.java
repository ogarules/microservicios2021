package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class AroundAspect {

    @Around("execution(* com.example.demo.services.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info("My awesome Around Aspect!!!!!!!!!");
        log.info("-> {} ", joinPoint);
        log.info("  -> args: {} ", joinPoint.getArgs());
        log.info("  -> sourcelocation: {} ", joinPoint.getSourceLocation());
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        log.info("{} regresa around con -> {}", joinPoint, result);
        log.info("{} se tardó -> {} milisengudos", joinPoint, System.currentTimeMillis() - start);
        
        return result;
    }
}
