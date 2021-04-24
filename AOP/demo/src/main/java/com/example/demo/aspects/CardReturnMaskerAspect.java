package com.example.demo.aspects;

import com.example.demo.models.Card;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class CardReturnMaskerAspect {
    @Around("execution(com.example.demo.models.Card com.example.demo.services.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = joinPoint.proceed();
        Card card = (Card)result;
        card.setVin("XXX");
        card.setNumber("XXXXXXXX");
        
        return result;
    }

    @Around("execution(java.lang.Iterable<com.example.demo.models.Card> com.example.demo.services.*.*(..))")
    public Object aroundList(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result = joinPoint.proceed();
        Iterable<Card> cards = (Iterable<Card>)result;
        for (Card card : cards) {
            card.setVin("XXX");
            card.setNumber("XXXXXXXX");
        }
        
        return result;
    }
}
