import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SendDataService {

  private apiUrl = 'http://localhost:8080/api/v1/member/register'; 
  private loginUrl = 'http://localhost:8080/api/v1/member/login'; 
  constructor(private http: HttpClient) { }

  register(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMessage = 'An error occurred while registering the member';
        if (error.status === 400) {
          errorMessage = error.error;
        }
        return throwError(errorMessage);
      })
    );
  
  }

  login(email: string, password: string): Observable<any> {
    const body = { email: email, password: password };
    return this.http.post(`${this.loginUrl}`, body).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400) {
          return throwError('Invalid Email or Password.');
        } else {
          return throwError('An error occurred while logging in.');
        }
      })
    );
  }

  

}
