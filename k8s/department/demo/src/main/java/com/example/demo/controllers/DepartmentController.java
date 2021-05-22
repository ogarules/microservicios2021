package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DepartmentController {
    
    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentRepository repository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        log.info("K8S Obteniendo todos los departmanetos");
        return this.repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable String id) {
        log.info("K8S Obteniendo el departamento: " + id);
        return this.repository.findById(id);
    }
    
    @PostMapping
    public Department postMethodName(@RequestBody Department entity) {
        log.info("K8S Agregando departamento: ");
        
        return this.repository.save(entity);
    }

    @GetMapping("/organization/{organizationId}")
    public Iterable<Department> getOrganizationDepartments(@PathVariable String organizationId) {
        log.info("K8S Obteniendo departamentos para la organizacion: " + organizationId);
        
        return this.repository.findByOrganizationId(organizationId);
    }
    
    @GetMapping("/organization/{organizationId}/with-employees")
    public Iterable<Department> getOrganizationDepartmentsWithEmployees(@PathVariable String organizationId) {
        log.info("K8S Obteniendo departamentos para la organizacion con empleados: " + organizationId);
        
        List<Department> departments = this.repository.findByOrganizationId(organizationId);
        departments.forEach(d -> d.setEmployees(this.employeeService.findByDepartmentId(d.getId())));

        return departments;
    }
    
}
