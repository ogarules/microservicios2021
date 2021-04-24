package com.example.demo.services;

import com.example.demo.models.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    
    @Autowired
    KafkaTemplate<String, Note> kafkaTemplate;

    public void sendNote(Note note){
        this.kafkaTemplate.send("notes", note);
    }
}
