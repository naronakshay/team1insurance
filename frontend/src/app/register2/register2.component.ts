import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from '../data-service.service';

@Component({
  selector: 'app-register2',
  templateUrl: './register2.component.html',
  styleUrls: ['./register2.component.css']
})
export class Register2Component {

  selectedOption!:String;
  hypertensive: boolean = false;
  diabetic: boolean = false;
  tobaccoUser:boolean=false;

  


  constructor(private dataService: DataServiceService,private router: Router) { }

  moveTof(){

    if (this.selectedOption=="Yes")
    {
      this.tobaccoUser=true;
    }
    const formData = {
      tobaccoUser: this.tobaccoUser,
      hypertensive: this.hypertensive,
      diabetic: this.diabetic,
      // other form data here
    };

  
    this.dataService.setRegisterData2(formData);

    this.router.navigate(['password']);
  }

  moveTob(){
   
    this.router.navigate(['register']);

  }

}
