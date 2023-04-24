import { Component, Input } from '@angular/core';

import { Router } from '@angular/router';
import { SendDataService } from '../send-data.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private router: Router,private logout: SendDataService) { }

  
  @Input()
  showButton: boolean = false;
  @Input() showButton2: boolean = false;

  @Input() disableLink!: boolean;


  moveTo(){
    this.router.navigate(['login']);
   
  }

  moveTo2(){
    this.logout.logout;
    this.router.navigate(['login']);
  }


}
