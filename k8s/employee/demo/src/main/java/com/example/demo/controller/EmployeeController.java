package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        log.info("K8S Obteniendo todos los empleados");
        return this.employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id) {
        log.info("K8S Obteniendo empleado con id: " + id);
        return this.employeeRepository.findById(id);
    }
    
    @PostMapping
    public Employee addEmployee(@RequestBody Employee entity) {
        log.info("K8S Guardando empleado los empleados");
        
        return this.employeeRepository.save(entity);
    }

    @GetMapping("/department/{id}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String id) {
        log.info("K8S Obteniendo empleados por departamento");

        return this.employeeRepository.findByDepartmentId(id);
    }
    
    @GetMapping("/organization/{id}")
    public List<Employee> getEmployeesByOrganization(@PathVariable String id) {
        log.info("K8S Obteniendo empleados por organizacion");

        return this.employeeRepository.findByOrganizationId(id);
    }
}
