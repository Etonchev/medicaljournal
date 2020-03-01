import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorAddComponent } from './doctor-add/doctor-add.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorGetComponent } from './doctor-get/doctor-get.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DoctorEditComponent } from './doctor-edit/doctor-edit.component';
import { PatientAddComponent } from './patient-add/patient-add.component';
import { PatientEditComponent } from './patient-edit/patient-edit.component';
import { PatientGetComponent } from './patient-get/patient-get.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { FooterComponent } from './footer/footer.component';
import { ExaminationListComponent } from './examination-list/examination-list.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorAddComponent,
    DoctorListComponent,
    DoctorGetComponent,
    DoctorEditComponent,
    PatientAddComponent,
    PatientEditComponent,
    PatientGetComponent,
    PatientListComponent,
    FooterComponent,
    ExaminationListComponent
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
