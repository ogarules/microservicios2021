package com.example.demo.controllers;

import javax.validation.groups.Default;

import com.example.demo.models.Pet;
import com.example.demo.services.PetService;
import com.example.demo.validations.OnUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    
    @Autowired
    private PetService service;

    @Autowired
    SmartValidator validator;

    @GetMapping(value="pets")
    public Iterable<Pet> getPets() {
        return service.getAllPets();
    }
    
    @GetMapping(value="pets/{id}")
    public Pet getPet(@PathVariable Integer id) {
        return service.getPetById(id);
    }

    @PostMapping(value="pets")
    public ResponseEntity<Pet> addPet(@RequestBody Pet entity, BindingResult result) {

        this.validator.validate(entity, result);
        if(result.hasErrors()){
            return new ResponseEntity<Pet>(entity, HttpStatus.BAD_REQUEST);
        }

        entity = this.service.addPet(entity);

        return ResponseEntity.ok(entity);
    }

    @PutMapping(value="pets/{id}")
    public ResponseEntity<Pet> putPet(@PathVariable Integer id, @Validated({OnUpdate.class, Default.class}) @RequestBody Pet entity, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<Pet>(entity, HttpStatus.BAD_REQUEST);
        } 

        return ResponseEntity.ok(service.updatePet(entity, id));
    }

    @DeleteMapping(value="pets/{id}")
    public void removePet(@PathVariable Integer id) {
        this.service.removePet(id);
    }
}
