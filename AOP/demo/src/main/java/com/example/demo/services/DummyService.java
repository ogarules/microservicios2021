package com.example.demo.services;

import com.example.demo.models.Card;

public interface DummyService {
    
    String GetDummyValue(String value);

    Card SaveCard(Card card);
}
