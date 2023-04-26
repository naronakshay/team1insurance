import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { PasswordComponent } from './password/password.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { Register2Component } from './register2/register2.component';
import {MatRadioModule} from '@angular/material/radio';
import { LookupService } from './lookup.service';
import { HttpClientModule } from '@angular/common/http';
import { QuoteComponent } from './quote/quote.component';
import { CarouselComponent } from './carousel/carousel.component';
import { BootstrapCardComponent } from './bootstrap-card/bootstrap-card.component';
import { LandingComponent } from './landing/landing.component';
import { MatIconModule } from '@angular/material/icon';
import { UserComponent } from './user/user.component';
import { MatStepperModule } from '@angular/material/stepper';
import { PremiumDisplayComponent } from './premium-display/premium-display.component';






@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    PasswordComponent,
    Register2Component,
    QuoteComponent,
    
    CarouselComponent,     
    BootstrapCardComponent, LandingComponent, UserComponent, PremiumDisplayComponent
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatOptionModule,
    MatCheckboxModule,
    MatRadioModule,
    HttpClientModule,
    MatIconModule,
    MatStepperModule
  ],
  providers: [LookupService],
  bootstrap: [AppComponent]
})
export class AppModule { }
