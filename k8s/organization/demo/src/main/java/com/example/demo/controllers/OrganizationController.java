package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.models.Organization;
import com.example.demo.repositories.OrganizationRepository;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class OrganizationController {
    
    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    OrganizationRepository repository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public Iterable<Organization> getAllOrganizations() {
        log.info("K8S Obteniendo todas las organizaciones");
        return this.repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Organization> getOrganization(@PathVariable String id) {
        log.info("K8S Obteniendo la organizacion: " + id);
        return this.repository.findById(id);
    }
    
    @PostMapping
    public Organization saveOrganization(@RequestBody Organization entity) {
        log.info("K8S Agregando Organizacion");
        
        return this.repository.save(entity);
    }
    
    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable String id){
        log.info("K8S Eliminando Organizacion: " + id);
        this.repository.deleteById(id);
    }

    @GetMapping(value="/{id}/deparments")
    public Optional<Organization> getOrganizationWithDepartments(@PathVariable String id) {
        log.info("K8S Obteniendo Organizacion con departamentos: " + id);
        Optional<Organization> organization = this.repository.findById(id);

        if(organization.isPresent()){
            Organization organizationPresent = organization.get();
            organizationPresent.setDepartments(this.departmentService.findByOrganizationId(id));
        }

        return organization;
    }

    @GetMapping("/{id}/departmentemployees")
    public Optional<Organization> getOrganizationWithDepartmentEmployees(@PathVariable String id) {
        log.info("K8S Obteniendo Organizacion con departamentos y empleados: " + id);
        Optional<Organization> organization = this.repository.findById(id);

        if(organization.isPresent()){
            Organization organizationPresent = organization.get();
            organizationPresent.setDepartments(this.departmentService.findByOrganizationIdWithEmployees(id));
        }

        return organization;
    }
    
    @GetMapping("/{id}/employees")
    public Optional<Organization> getOrganizationWithEmployees(@PathVariable String id) {
        log.info("K8S Obteniendo Organizacion empleados: " + id);
        Optional<Organization> organization = this.repository.findById(id);

        if(organization.isPresent()){
            Organization organizationPresent = organization.get();
            organizationPresent.setEmployees(this.employeeService.findByOrganizationId(id));
        }

        return organization;
    }
    
}
