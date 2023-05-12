import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import jwtDecode from 'jwt-decode';


import { SendDataService } from '../member-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm!: FormGroup;
  loginError: boolean = false;
  errorMessage: any;
  

  constructor(private authService :SendDataService ,private formBuilder: FormBuilder,private router:Router){

    this.loginForm = this.formBuilder.group({
      emailFormControl: ['', [Validators.required, Validators.email]],
      passwordControl: ['', Validators.required]
    });
  


  }

 
  
  onSubmit() {

    

    if(this.loginForm.valid){

      this.authService.login(this.loginForm.value.emailFormControl,this.loginForm.value.passwordControl).subscribe(
        token => {
          localStorage.setItem('token', token);
          const decodedToken = jwtDecode<any>(token);
          const email = decodedToken.sub;
          localStorage.setItem('email',email);  
          this.router.navigate(['/user'])
          console.log(token)
        },
        (error) => {


          if (error.status === 400) {
            this.errorMessage = 'Invalid email or password';
          } else if (error.status === 500) {
            this.errorMessage = 'Internal Server Error! Try again';
          } if (error.status === 401) {
            this.errorMessage = 'Invalid email or password';
          }
          else {
            this.errorMessage = 'Invalid email or password';
          }}
      
        
      );


      
      
    }
  
    
   
    
  }

}





