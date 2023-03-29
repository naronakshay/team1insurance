import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  emailFormControl = new FormControl('', [ Validators.required,Validators.email,]);
  passwordControl = new FormControl('', Validators.required);
  onSubmit() {
    
   
    }
  }







