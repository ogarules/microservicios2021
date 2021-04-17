package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.example.demo.services.PetService;

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
    private PetService service;

    @GetMapping(value="pets")
    public Iterable<Pet> getPets() {
        return service.getAllPets();
    }
    
    @GetMapping(value="pets/{id}")
    public Pet getPet(@PathVariable Integer id) {
        return service.getPetById(id);
    }

    @PostMapping(value="pets")
    public Pet addPet(@RequestBody Pet entity) {
        return service.addPet(entity);
    }

    @PutMapping(value="pets/{id}")
    public Pet putPet(@PathVariable Integer id, @RequestBody Pet entity) {
        return service.updatePet(entity, id);
    }

    @DeleteMapping(value="pets/{id}")
    public void removePet(@PathVariable Integer id) {
        this.service.removePet(id);
    }
}
