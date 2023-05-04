import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface State{
  state_id : number;
  state_name: String;

}

interface City {

  city_id: number;
  city_name: string;
  tier_1:boolean;
  state:State
}

interface Disease{
  disease_id:number;
  disease_name:string;

}





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
