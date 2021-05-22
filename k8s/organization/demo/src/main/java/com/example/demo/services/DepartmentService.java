package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department")
public interface DepartmentService {
    
    @GetMapping(value="/organization/{organizationId}")
    public List<Department> findByOrganizationId(@PathVariable String organizationId);

    @GetMapping(value="/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationIdWithEmployees(@PathVariable String organizationId);
}
