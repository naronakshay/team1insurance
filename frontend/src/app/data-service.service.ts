import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor() { }


  private email!:string;
  setEmail(email:string){
    this.email=email;
  }

  getEmail(){
    return this.email;
  }

  sharedData = {
    RegisterData: {},
    RegisterData2: {},
    PasswordData: {}
  };

  setRegisterData(data: any) {
    this.sharedData.RegisterData = data;
  }

  setRegisterData2(data: any) {
    this.sharedData.RegisterData2 = data;
  }

  setPasswordData(data: any) {
    this.sharedData.PasswordData = data;
  }

  getSharedData() {
    return this.sharedData;
  }

  

}
