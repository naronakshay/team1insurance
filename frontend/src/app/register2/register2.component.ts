import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { StateKey } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';
import { Disease } from '../Entity/disease';
import { Register2Data } from '../Entity/register2Data';
import { LookupService } from '../lookup.service';




@Component({
  selector: 'app-register2',
  templateUrl: './register2.component.html',
  styleUrls: ['./register2.component.css']
})
export class Register2Component implements OnInit {

  
  selectedOption: boolean = false;
  selectedDiseases: string[] = [];
 
  illnessDetails!:String;


  
  disease!: any[];

 
  


  constructor(private formBuilder: FormBuilder,private lookupService:LookupService,private dataService: DataServiceService,private router: Router) { }


  ngOnInit(): void {
    
    this.lookupService.getDisease().subscribe((data: Disease[]) => {
      this.disease = data

      this.disease.forEach(disease => {
        disease.selected = false;
      });
    });
  }

  
  
  onDiseaseSelectionChange() {

    this.disease.forEach(disease => {
      if (disease.selected && !this.selectedDiseases.includes(disease.diseaseName)) {
        this.selectedDiseases.push(disease.diseaseName);
      } else if (!disease.selected && this.selectedDiseases.includes(disease.diseaseName)) {
        this.selectedDiseases.splice(this.selectedDiseases.indexOf(disease.diseaseName), 1);
      }
    });



}




   
  
  
  

  moveTof(){
  
   
    
    this.illnessDetails=this.selectedDiseases.join(', ');

    const formData:Register2Data = {
      tobaccoUser: this.selectedOption,
      illnessDetails:this.illnessDetails
      
     
    };
    console.log(formData)

    
  
   
    this.dataService.setRegisterData2(formData);

    
  



  
  



  

    this.router.navigate(['password']);
  }

  moveTob(){
   
    this.router.navigate(['register']);

  }

}
