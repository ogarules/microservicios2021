package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class PetController {
    
    @Autowired
    private PetRepository repository;

    @GetMapping(value="pets")
    public Iterable<Pet> getPets() {
        return repository.findAll();
    }
    
    @GetMapping(value="pets/{id}")
    public Pet getPet(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @PostMapping(value="pets")
    public Pet addPet(@RequestBody Pet entity) {
        repository.save(entity);
        
        return entity;
    }

    @PutMapping(value="pets/{id}")
    public Pet putPet(@PathVariable Integer id, @RequestBody Pet entity) {
        Pet petDb = repository.findById(id).get();
        
        petDb.setName(entity.getName());
        petDb.setTag(entity.getTag());
        
        repository.save(petDb);

        return petDb;
    }

    @DeleteMapping(value="pets/{id}")
    public void removePet(@PathVariable Integer id) {

        Pet petDb = repository.findById(id).get();
        repository.delete(petDb);
        //repository.deleteById(id);
    }
}
