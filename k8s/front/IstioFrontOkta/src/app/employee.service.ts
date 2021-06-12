import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private httpClient: HttpClient) { }
  
  getAllEmployees() {
    return this.httpClient.get(`${environment.apiUrl}/employee`);
  }
}
