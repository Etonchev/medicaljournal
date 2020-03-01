import { DoctorGetComponent } from './doctor-get/doctor-get.component';
import { DoctorAddComponent } from './doctor-add/doctor-add.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorEditComponent } from "./doctor-edit/doctor-edit.component";
import {PatientListComponent} from "./patient-list/patient-list.component";
import {PatientEditComponent} from "./patient-edit/patient-edit.component";
import {PatientGetComponent} from "./patient-get/patient-get.component";
import {PatientAddComponent} from "./patient-add/patient-add.component";
import {ExaminationListComponent} from "./examination-list/examination-list.component";
import {ExaminationGetComponent} from "./examination-get/examination-get.component";
import {ExaminationEditComponent} from "./examination-edit/examination-edit.component";
import {ExaminationAddComponent} from "./examination-add/examination-add.component";
import {ExaminationDiagnosisComponent} from "./examination-diagnosis/examination-diagnosis.component";

const routes: Routes = [
  { path: '', redirectTo: 'doctor', pathMatch: 'full' },
  { path: 'home', redirectTo: '' },
  // Doctor APIs
  { path: 'doctors', component: DoctorListComponent },
  { path: 'doctor/add', component: DoctorAddComponent },
  { path: 'doctor/edit/:id', component: DoctorEditComponent },
  { path: 'doctor/details/:id', component: DoctorGetComponent },
  // Patient APIs
  { path: 'patients', component: PatientListComponent },
  { path: 'patient/add', component: PatientAddComponent },
  { path: 'patient/edit/:id', component: PatientEditComponent },
  { path: 'patient/details/:id', component: PatientGetComponent },
  // Examination APIs
  { path: 'examinations', component: ExaminationListComponent },
  { path: 'examination/add', component: ExaminationAddComponent },
  { path: 'examination/details/:id', component: ExaminationGetComponent },
  { path: 'examination/edit/:id', component: ExaminationEditComponent },
  { path: 'examinations/diagnosis/:diagnosis', component: ExaminationDiagnosisComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
