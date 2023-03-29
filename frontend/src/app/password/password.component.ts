import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})




export class PasswordComponent {


  form!: FormGroup;

  constructor(private fb: FormBuilder,private formbulder:FormBuilder, private router : Router) {

      this.form = this.fb.group({
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', Validators.required]
      },{
    
     validator: this.passwordMatchValidator
    
      });

    }
    
     passwordMatchValidator(form: FormGroup) {
      if (form.get('password')?.value !== form.get('confirmPassword')?.value) {
        form.get('confirmPassword')?.setErrors({ mismatch: true });
    
      } else {
    
        form.get('confirmPassword')?.setErrors(null);
    
      }
    }


    onSubmit(){

      if(this.form.valid)
      {
        this.router.navigate([''])
      }

    }


   
    


    



  
   
}
