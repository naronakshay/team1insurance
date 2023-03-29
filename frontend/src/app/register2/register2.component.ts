import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register2',
  templateUrl: './register2.component.html',
  styleUrls: ['./register2.component.css']
})
export class Register2Component {

  constructor(private router: Router) { }

  moveTof(){
    this.router.navigate(['password']);
  }

  moveTob(){
    this.router.navigate(['register']);

  }

}
