import { DoctorGetComponent } from './doctor-get/doctor-get.component';
import { DoctorAddComponent } from './doctor-add/doctor-add.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { DoctorEditComponent } from "./doctor-edit/doctor-edit.component";

const routes: Routes = [
  { path: '', redirectTo: 'doctor', pathMatch: 'full' },
  { path: 'doctors', component: DoctorListComponent },
  { path: 'add', component: DoctorAddComponent },
  { path: 'edit/:id', component: DoctorEditComponent },
  { path: 'details/:id', component: DoctorGetComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
