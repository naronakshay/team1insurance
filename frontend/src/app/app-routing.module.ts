import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './landing/landing.component';
import { LoginComponent } from './login/login.component';
import { PasswordComponent } from './password/password.component';
import { RegisterComponent } from './register/register.component';
import { Register2Component } from './register2/register2.component';

import { UserComponent } from './user/user.component';



const routes: Routes = [
  { path: '', component: LandingComponent },
  {path:'login',component:LoginComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'password', component: PasswordComponent },
  { path: 'register2', component: Register2Component} ,
  {path:'user',component:UserComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
