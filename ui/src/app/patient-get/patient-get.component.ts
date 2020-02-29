import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Patient } from "../patient/patient";
import { PatientService } from "../patient/patient.service";
import { DoctorService } from "../doctor/doctor.service";
import { Doctor } from "../doctor/doctor";

@Component({
  selector: 'app-patient-get',
  templateUrl: './patient-get.component.html',
  styleUrls: ['./patient-get.component.sass']
})
export class PatientGetComponent implements OnInit {
  id: string;
  patient: Patient;
  doctor: Doctor;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private patientService: PatientService,
              private doctorService: DoctorService) { }

  ngOnInit() {
    this.patient = new Patient();

    this.id = this.route.snapshot.params['id'];

    this.reloadData();
  }

  reloadData() {
    this.patientService.getPatient(this.id)
      .subscribe(data => {
        this.patient = data;
        this.doctorService.getDoctor(this.patient.personalGPUin)
          .subscribe(data => {
            this.doctor = data;
          })
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['patients']);
  }

  edit(id: string){
    this.router.navigate(['patient/edit', id]);
  }
}
