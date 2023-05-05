import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import { SendDataService } from '../member-service';


@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})




export class PasswordComponent {


  form!:FormGroup;
  errorMessage: any;
  message:any;
  message2:any;
 

  constructor(private sendDataService : SendDataService,private dataService: DataServiceService,private fb: FormBuilder,private formbulder:FormBuilder, private router : Router) {

      this.form = this.fb.group({
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', Validators.required],
    agreeToTerms:['']

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
        const passwordData = this.form.value;
        const passwordData1 = {
          password: passwordData.password,
          
        };

        this.dataService.setPasswordData(passwordData1);
        const sharedData = this.dataService.getSharedData();
        const mergedData = Object.assign({}, sharedData.Data3, sharedData.Data1, sharedData.Data2);
        console.log(mergedData);
        this.sendDataService.register(mergedData).subscribe(
          (data) => {

            this.message="Registration successful";
            this.message2="Click Here to Login";
            console.log('Registration successful', data);
            
          },
          (error) => {
            console.error('Registration error', error);
            this.errorMessage = error;
          }
        
        );

        if(this.errorMessage=""){
          this.router.navigate([''])

        }
        
        
      }

    }


   
    


    



  
   
}
