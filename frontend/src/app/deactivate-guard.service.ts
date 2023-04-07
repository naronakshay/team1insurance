import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';
import { Register2Component } from './register2/register2.component';

@Injectable({
  providedIn: 'root'
})
export class DeactivateGuardService implements CanDeactivate<Register2Component> {
  canDeactivate(component: Register2Component): Observable<boolean> | Promise<boolean> | boolean {
    return false;
  }

  constructor() { 

  }
}
