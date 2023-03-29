import { Component, Input } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private router: Router) { }

  
  @Input()
  showButton: boolean = false;

  moveTo(){
    this.router.navigate(['']);
   
  }
}
