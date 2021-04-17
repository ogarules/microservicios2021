package com.example.demo.services;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements PetService{
    @Autowired
    PetRepository repository;

    @Override
    public Pet addPet(Pet pet) {
        return this.repository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet, Integer id) {
        Pet petDb = repository.findById(id).get();
        
        petDb.setName(pet.getName());
        petDb.setTag(pet.getTag());
        
        repository.save(petDb);

        return petDb;
    }

    @Override
    public Pet getPetById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Iterable<Pet> getAllPets() {
        return repository.findAll();
    }

    @Override
    public void removePet(Integer id) {
        Pet petDb = repository.findById(id).get();
        repository.delete(petDb);        
    }
}
