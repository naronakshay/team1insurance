import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PremiumServiceService {


  private baseUrl = 'http://localhost:8080/api/v1/member';


  private currentUserSubject!: BehaviorSubject<any>;
  public currentUser!: Observable<any>;


  constructor(private http: HttpClient) { }

  getPlansByEmail(email:string | null):Observable<any>{
    return this.http.get(`${this.baseUrl}/premiums/${email}`);

  }
}
