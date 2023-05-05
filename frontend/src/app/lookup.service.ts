import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { State } from './Entity/state';
import { City } from './Entity/city';
import { Disease } from './Entity/disease';



@Injectable({
  providedIn: 'root'
})
export class LookupService  {

 
  private baseUrl = 'http://localhost:8080/api/v1/member';

  constructor(private http: HttpClient) { }
  

  getStates(): Observable<State[]> {
    return this.http.get<State[]>(`${this.baseUrl}/states`);
  }

 
  getCities(stateId: number): Observable<City[]> {
    const url = `${this.baseUrl}/cities/${stateId}`;
    return this.http.get<City[]>(url);
  }

  getDisease(): Observable<Disease[]> {
    return this.http.get<Disease[]>(`${this.baseUrl}/disease`);
  }

  
 

 
  

}
