import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SendDataService {


  private baseUrl = 'http://localhost:8080/api/v1/member';

 

  
  public currentUser!: Observable<any>;

  constructor(private http: HttpClient) {}

  register(data: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, data).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'An error occurred while registering the member';
        if (error.status === 400) {
          errorMessage = error.error;
        }
        return throwError(errorMessage);
      })
    );
  
  }

  login(email: string, password: string) {
    return this.http.post<string>(`${this.baseUrl}/login`, {email,password}, { responseType: 'text' as 'json' });

  }


  logout() {
      
      localStorage.clear();
  }

  getMemberByEmail(email: string | null): Observable<any> {
    return this.http.get(`${this.baseUrl}/user/${email}`);
  }
 

 
  
}
