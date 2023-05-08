import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PremiumServiceService {


  private plansUrl = 'http://localhost:8080/api/v1/member/premiums';


  private currentUserSubject!: BehaviorSubject<any>;
  public currentUser!: Observable<any>;


  constructor(private http: HttpClient) { }

  getPlansByEmail(email:string | null):Observable<any>{

    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get(`${this.plansUrl}/${email}`,{ headers });

  }
}
