import { HttpClient } from '@angular/common/http';
import { Component, Input, OnChanges, OnInit } from '@angular/core';
import {FormControl, Validators,FormGroup, FormBuilder} from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Router } from '@angular/router';
import { LookupService } from '../lookup.service';
import { ReactiveFormsModule } from '@angular/forms';


interface City {
  state_id:number;
  city_id: number;
  city_name: string;
}




@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent  implements OnInit  {

  minDate !: Date;
  maxDate = new Date();
  form!: FormGroup;
  isFormValid: boolean = false;
  state!: any[];



  selectedState!: number;
  cities!: City[];
 
  
  


  constructor(private lookupService : LookupService,private router: Router,private dateAdapter: DateAdapter<Date>,private fb:FormBuilder) { 




    this.dateAdapter.setLocale('en-GB');

    const pastYear = this.maxDate.getFullYear() - 100;
    this.minDate = new Date();
    this.minDate.setFullYear(pastYear);

    this.form = this.fb.group({

    firstName : new FormControl('',[ Validators.required]),
    lastName :new FormControl('',[ Validators.required]),
    email:new FormControl('', [Validators.required,Validators.email,]),
    mobileNumber : new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),
    dateOfBirth : new FormControl('', Validators.required),
    address : new FormControl('', Validators.required),
    govId : new FormControl('', [Validators.required, Validators.pattern('[0-9]{12}')]),
    gender : new FormControl('', Validators.required),
    city : new FormControl('', Validators.required),
    state : new FormControl('', Validators.required),
    pincode : new FormControl('', [Validators.required, Validators.pattern('[0-9]{6}')]),

    });

  }

  
  ngOnInit(): void {
    this.lookupService.getStates().subscribe((data: any[]) => {
      this.state = data;
    });
    
  
  }
  

  
  onStateChange() {
    
    this.lookupService.getCities(this.selectedState).subscribe((data) => {
      this.cities = data;
    });
  }


  

 
 
 

 


  onSubmit(){

    if (this.form.valid){
      this.router.navigate(['register2']);
    }
    

  }





  
  
}


