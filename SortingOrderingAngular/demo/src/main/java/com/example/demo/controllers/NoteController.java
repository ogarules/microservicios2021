package com.example.demo.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.demo.models.Note;
import com.example.demo.models.QNote;
import com.example.demo.repositories.NoteRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class NoteController {
    
    @Autowired
    NoteRepository repository;

    @GetMapping(value="notes")
    public Iterable<Note> getMethodName(@QuerydslPredicate(root= Note.class) Predicate predicate, 
                                        @SortDefault(sort = "noteText", direction = Direction.ASC) 
                                        @PageableDefault(size = 5, page = 0) Pageable page) {
        return this.repository.findAll(predicate, page);
    }
    
    @PostMapping(value="notes")
    public Note postMethodName(@RequestBody Note entity) {
        //TODO: process POST request
        
        return this.repository.save(entity);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value="notesquerydsl")
    public Iterable<Note> getMethodName() {
        JPAQuery<Note> query = new JPAQuery<>(entityManager); 
        QNote note = QNote.note;
        
        return query.from(note)
                    .where(note.value.between(2, 5).and(note.noteText.containsIgnoreCase("oscar")))
                    .orderBy(note.noteUser.asc())
                    .fetch();
    }    
}
