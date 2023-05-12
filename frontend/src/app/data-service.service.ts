import { Injectable } from '@angular/core';
import { Password } from './Entity/passwordData';
import { Register2Data } from './Entity/register2Data';
import { RegisterData } from './Entity/registerData';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor() { } 

  sharedData = {
    Data1: {},
    Data2: {},
    Data3: {}
  };

  setRegisterData(data: RegisterData) {
    this.sharedData.Data1 = data;
  }

  setRegisterData2(data: Register2Data) {
    this.sharedData.Data2 = data;
  }

  setPasswordData(data: Password) {
    this.sharedData.Data3 = data;
  }

  getSharedData() {
    return this.sharedData;
  }

  

}
