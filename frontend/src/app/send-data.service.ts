import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SendDataService {

  private apiUrl = 'http://localhost:8080/api/v1/member/register'; 
  private loginUrl = 'http://localhost:8080/api/v1/member/login'; 
  private baseUrl = 'http://localhost:8080/api/v1/member/user';
  private premiumUrl = 'http://localhost:8080/api/v1/member/premium';
  private plansUrl = 'http://localhost:8080/api/v1/member/premiums';

  private currentUserSubject!: BehaviorSubject<any>;
  public currentUser!: Observable<any>;

  constructor(private http: HttpClient) {}
   

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

 

  login(email: string, password: string) {
    return this.http.post<string>(this.loginUrl, {email,password}, { responseType: 'text' as 'json' });

  }


  logout() {
   

    sessionStorage.removeItem('token'); 
      localStorage.removeItem('token');
      localStorage.removeItem('email');
      localStorage.removeItem('details');
      sessionStorage.removeItem('email');

      


  }

  getMemberByEmail(email: string | null): Observable<any> {
    return this.http.get(`${this.baseUrl}/${email}`);
  }
  getPremiumByEmail(email:string | null):Observable<any>{
    return this.http.get(`${this.premiumUrl}/${email}`);
  }

  getPlansByEmail(email:string | null):Observable<any>{
    return this.http.get(`${this.plansUrl}/${email}`);

  }


 


  
}
