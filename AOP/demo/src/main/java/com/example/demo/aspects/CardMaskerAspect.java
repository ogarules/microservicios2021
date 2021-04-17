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
public class CardMaskerAspect {
    @Around("@annotation(com.example.demo.aspects.CardMasker)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info("Masking Caaard!!!!!!!!!");
        Object[] args = joinPoint.getArgs();
        Card card = (Card)args[0];
        card.setVin("XXX");

        Object result = joinPoint.proceed();

        return result;
    }
}
