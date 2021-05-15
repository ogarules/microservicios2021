package com.example.demo.services;

import com.example.demo.models.Note;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerService {
    
    @KafkaListener(topics ="notes", groupId = "group1")
    public void consume(Note message){      
        log.info("Mensaje consumido desde grupo 1 : " + message.toString());
    }

    @KafkaListener(topics ="notes", groupId = "group2")
    public void consume2(Note message){      
        log.info("Mensaje consumido desde grupo2 : " + message.toString());
    }

    @KafkaListener(topics ="notes", groupId = "group3")
    public void consume3(Note message){      
        log.info("Mensaje consumido desde grupo3 : " + message.toString());
    }
}
