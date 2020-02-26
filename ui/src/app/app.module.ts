import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorAddComponent } from './doctor-add/doctor-add.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorGetComponent } from './doctor-get/doctor-get.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    DoctorAddComponent,
    DoctorListComponent,
    DoctorGetComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
