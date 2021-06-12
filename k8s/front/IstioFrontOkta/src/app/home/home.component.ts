import { stringify } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../department.service';
import { EmployeeService } from '../employee.service';
import { OrganizationService } from '../organization.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private organizationService: OrganizationService,
    private departmentService: DepartmentService,
    private employeeervice: EmployeeService) { }

  ngOnInit(): void {

    this.departmentService.getAllDepartments().subscribe((data) => {
      console.log(data);
      console.log('Departamentos obtenidos');
    }, error => {
      console.error(error);
    });

    this.employeeervice.getAllEmployees().subscribe((data) => {
      console.log(data);
      console.log('Empleados obtenidos');
    }, error => {
      console.error(error);
    });

    this.organizationService.getAllOrganization().subscribe((data) => {
      console.log(data);
      console.log('Organizaciones obtenidos');
    }, error => {
      console.error(error);
    });
  }

}
