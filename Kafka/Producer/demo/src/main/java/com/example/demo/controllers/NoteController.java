package com.example.demo.controllers;

import com.example.demo.models.Note;
import com.example.demo.services.Producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class NoteController {
    
    @Autowired
    Producer producer;

    @PostMapping(value="notes")
    public Note postMethodName(@RequestBody Note entity) {
        
        this.producer.sendNote(entity);
        return entity;
    }
    
}
