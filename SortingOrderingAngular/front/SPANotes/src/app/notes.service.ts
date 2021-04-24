import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotesService {

  constructor(private httpClient : HttpClient) { }

  getNotes(text : String, user: String, value: Number, sort : String, page: Number, pageSize: Number){
    let queryString : String = `?size=${pageSize}&page=${page}`; 

    if(text && text.length >0){
      queryString = `${queryString}&noteText=${text}`;
    }

    if(user && user.length >0){
      queryString = `${queryString}&noteUser=${user}`;
    }

    if(value){
      queryString = `${queryString}&value=${user}`;
    }

    if(sort && sort.length >0){
      queryString = `${queryString}&sort=${sort}`;
    }

    return this.httpClient.get(`${environment.apiUrl}/notes${queryString}`)
  }
}
