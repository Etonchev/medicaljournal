import { PatientGetComponent } from '../patient-get/patient-get.component';
import { Observable } from "rxjs";
import { PatientService } from "../patient/patient.service";
import { Patient } from "../patient/patient";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {Doctor} from "../doctor/doctor";
import {DoctorService} from "../doctor/doctor.service";

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.sass']
})
export class PatientListComponent implements OnInit {
  patients: Observable<Patient[]>;

  constructor(private patientService: PatientService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.patients = this.patientService.getPatientList();
  }

  deleteDoctor(uin: string) {
    this.patientService.deletePatient(uin)
      .subscribe(
        data => {
          this.reloadData();
        },
        error => console.log(error));
  }

  doctorDetails(uin: string){
    this.router.navigate(['details', uin]);
  }

  editDoctor(id: string){
    this.router.navigate(['edit', id]);
  }
}
