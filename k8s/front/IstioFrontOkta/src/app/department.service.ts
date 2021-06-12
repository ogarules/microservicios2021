import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private httpClient: HttpClient) { }
  
  getAllDepartments() {
    return this.httpClient.get(`${environment.apiUrl}/department`);
  }
}
