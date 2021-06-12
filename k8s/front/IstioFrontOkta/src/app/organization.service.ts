import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  constructor(private httpClient: HttpClient) { }
  
  getAllOrganization() {
    return this.httpClient.get(`${environment.apiUrl}/organization`);
  }
}
