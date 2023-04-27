import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../data-service.service';
import { SendDataService } from '../send-data.service';
import jwtDecode from 'jwt-decode';
interface Plan {
  plan_id: number;
  plan_type: string;
  coverage: number;
  plan_details:string;
  finalPremium: number;
  monthlyPremium:number;
  cashless_hospitals:number;
}


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

  constructor(private shareddata: DataServiceService,private userService:SendDataService) {

    
  }
  ngOnInit(): void {
    const email = localStorage.getItem('email'); 
    const details = localStorage.getItem('details'); 

    //to get the user details by email 

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




    //to get the basic premium of user by email

    this.userService.getPremiumByEmail(email).subscribe(
      (data) => {
        this.premiumDetails = data;
        localStorage.setItem('annualPremium',this.premiumDetails.premium);
        this.annualPremium = localStorage.getItem('annualPremium');
        this.monthlyPremium = Math.floor(this.annualPremium/12);
      },
      (error) => {
        console.log(error);
      }
    );
    
   // to get plan details of member by email

    this.userService.getPlansByEmail(email).subscribe(
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
  
  

  
   
     
  
  
  




