import { DoctorGetComponent } from './doctor-get/doctor-get.component';
import { DoctorAddComponent } from './doctor-add/doctor-add.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorEditComponent } from "./doctor-edit/doctor-edit.component";
import {PatientListComponent} from "./patient-list/patient-list.component";
import {PatientEditComponent} from "./patient-edit/patient-edit.component";
import {PatientGetComponent} from "./patient-get/patient-get.component";

const routes: Routes = [
  { path: '', redirectTo: 'doctor', pathMatch: 'full' },
  // Doctor APIs
  { path: 'doctors', component: DoctorListComponent },
  { path: 'add', component: DoctorAddComponent },
  { path: 'doctor/edit/:id', component: DoctorEditComponent },
  { path: 'doctor/details/:id', component: DoctorGetComponent },
  // Patient APIs
  { path: 'patients', component: PatientListComponent },
  { path: 'patient/edit/:id', component: PatientEditComponent },
  { path: 'patient/details/:id', component: PatientGetComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
