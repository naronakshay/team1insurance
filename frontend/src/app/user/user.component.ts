import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../data-service.service';
import { SendDataService } from '../send-data.service';
import jwtDecode from 'jwt-decode';



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
 

  constructor(private shareddata: DataServiceService,private userService:SendDataService) {

    
  }
  ngOnInit(): void {
    const email = localStorage.getItem('email'); 
    const details = localStorage.getItem('details'); 
    if (details && JSON.parse(details).email === email) { 
      this.userDetails = JSON.parse(details);
      this.name = this.userDetails.firstName;
    } else {
      this.userService.getMemberByEmail(email).subscribe(
        (data) => {
          this.userDetails = data;
          this.name = this.userDetails.firstName;
          localStorage.setItem('details', JSON.stringify(data));
        },
        (error) => {
          console.log(error);
        }
      );
    }
    localStorage.removeItem('details');
    



  }
  
  

  
   
     
  
  
  



}
