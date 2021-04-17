package com.example.demo.services;

import com.example.demo.aspects.CardMasker;
import com.example.demo.controllers.DummyController;
import com.example.demo.models.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DummyServiceImpl implements DummyService{
    private static final Logger log = LoggerFactory.getLogger(DummyController.class);
    
    @Override
    public String GetDummyValue(String value) {
        //log.info(" -> args : {} ", value);
        return value;
    }

    @Override
    @CardMasker
    public Card SaveCard(Card card) {
        return card;
    }
    
}
