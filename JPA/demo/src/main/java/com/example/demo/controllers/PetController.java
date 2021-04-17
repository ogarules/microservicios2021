package com.example.demo.controllers;

import javax.validation.constraints.Min;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Store of pets", description = "Api para administrar las mascotas")
@RestController
@Validated
public class PetController {
    
    @Autowired
    private PetService service;

    @Autowired
    SmartValidator validator;

    @ApiOperation(value = "Devuelve todas las mascotas activas", produces = "application/json", consumes = "application/json", protocols = "http,https", notes="blablabla")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping(value="pets")
    public Iterable<Pet> getPets() {
        return service.getAllPets();
    }

    @ApiOperation(value = "Devuelve una mascota en base a su identificador interno", produces = "application/json", consumes = "application/json", protocols = "http,https", notes="blablabla2")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 500, message = "Internal Server Error")})
    @GetMapping(value="pets/{id}")
    public Pet getPet(@PathVariable Integer id) {
        return service.getPetById(id);
    }

    @ApiOperation(value = "Agrega una mascota", produces = "application/json", consumes = "application/json", protocols = "http,https", notes="blablabla 3")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value="pets")
    public ResponseEntity<Pet> addPet(@RequestBody Pet entity, BindingResult result) {

        this.validator.validate(entity, result);
        if(result.hasErrors()){
            return new ResponseEntity<Pet>(entity, HttpStatus.BAD_REQUEST);
        }

        entity = this.service.addPet(entity);

        return ResponseEntity.ok(entity);
    }

    @ApiOperation(value = "Actualiza una mascota", produces = "application/json", consumes = "application/json", protocols = "http,https", notes="blablabla 4")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PutMapping(value="pets/{id}")
    public ResponseEntity<Pet> putPet(@PathVariable @Min(100) Integer id, @Validated({OnUpdate.class, Default.class}) @RequestBody Pet entity){//}, BindingResult result) {
        // if(result.hasErrors()){
        //     return new ResponseEntity<Pet>(entity, HttpStatus.BAD_REQUEST);
        // } 

        return ResponseEntity.ok(service.updatePet(entity, id));
    }

    @ApiOperation(value = "Da de baja una mascota", produces = "application/json", consumes = "application/json", protocols = "http,https", notes="blablabla 5")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
                            @ApiResponse(code = 500, message = "Internal Server Error")})
    @DeleteMapping(value="pets/{id}")
    public void removePet(@PathVariable Integer id) {
        this.service.removePet(id);
    }
}
