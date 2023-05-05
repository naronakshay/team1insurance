import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../data-service.service';
import { SendDataService } from '../member-service';
import jwtDecode from 'jwt-decode';
import { Plan } from '../Entity/plan';
import { PremiumServiceService } from '../premium-service.service';





@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent  implements OnInit  {



  name!:String | null;
  member!:any;
  errorMessage: any;
  userDetails:any;
  annualPremium:any;
  monthlyPremium:any;
  premiumDetails:any;
  plans!:Plan[];

  selectedPlan:any = null;


  constructor(private shareddata: DataServiceService,private premiumService:PremiumServiceService,
    private userService:SendDataService ) {

    
  }


ngOnInit(): void {


    const email = localStorage.getItem('email'); 
    const details = localStorage.getItem('details'); 


    if (details && JSON.parse(details).email === email) { 
      this.userDetails = JSON.parse(details);
      this.name = this.userDetails.firstName.toUpperCase();
    } else {
      this.userService.getMemberByEmail(email).subscribe(
        (data) => {
          this.userDetails = data;
          localStorage.setItem('name',this.userDetails.firstName.toUpperCase());
          this.name = localStorage.getItem('name');
          localStorage.setItem('details', JSON.stringify(data));
        },
        (error) => {
          console.log(error);
        }
      );
    }
    localStorage.removeItem('details');


    
    this.premiumService.getPlansByEmail(email).subscribe(
      (data) => {
        localStorage.setItem('plans', JSON.stringify(data));
        const plansData = JSON.parse(localStorage.getItem('plans') || '[]');
        this.plans = plansData;   
      },
      (error) => {
        console.log(error);
      }
    );

    
  }


  onButtonClicked(plan: Plan) {
    localStorage.setItem('monthlyPremium', plan.monthlyPremium.toString());
  }
  


  }

  
  

  
   
     
  
  
  


