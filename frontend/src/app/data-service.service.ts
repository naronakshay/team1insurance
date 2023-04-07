import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor() { }

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
