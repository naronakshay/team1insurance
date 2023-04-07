import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { SendDataService } from '../send-data.service';

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

    console.log(this.loginForm.value.emailFormControl, this.loginForm.value.passwordControl)

    if(this.loginForm.valid){

      this.authService.login(this.loginForm.value.emailFormControl, this.loginForm.value.passwordControl)
      .subscribe(
        (user) => {
          this.router.navigate(['/register']);
    
          
        },
        (error) => {
          this.errorMessage = error;
          console.log(this.errorMessage);

        }
      );
    
    }
    
   
    
  }

}





