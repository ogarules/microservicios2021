package com.example.demo.services;

import com.example.demo.models.Pet;

public interface PetService {
    
    Pet addPet(Pet pet);
    Pet updatePet(Pet pet, Integer id);
    Pet getPetById(Integer id);
    Iterable<Pet> getAllPets();
    void removePet(Integer id);
}
