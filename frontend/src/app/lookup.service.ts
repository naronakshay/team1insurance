import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface State{
  state_id : number;
  state_name: String;


}

interface City {

  state_id:number;
  city_id: number;
  city_name: string;
}





@Injectable({
  providedIn: 'root'
})
export class LookupService implements OnInit{

 
  private baseUrl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    
  }

  getStates(): Observable<State[]> {
    return this.http.get<State[]>(`${this.baseUrl}/states`);
  }

 
  getCities(stateId: number): Observable<City[]> {
    const url = `${this.baseUrl}/cities/${stateId}`;
    return this.http.get<City[]>(url);
  }
 

 
  

}
