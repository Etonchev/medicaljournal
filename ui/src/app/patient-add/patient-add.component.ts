import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from "../patient/patient";
import { PatientService } from "../patient/patient.service";

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.sass']
})
export class PatientAddComponent implements OnInit {
  patient: Patient = new Patient();
  submitted = false;

  constructor(private patientService: PatientService,
              private router: Router) { }

  ngOnInit() {
  }

  save() {
    this.patientService.createPatient(this.patient)
      .subscribe(
        data => this.list(),
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  list() {
    this.router.navigate(['patients']);
  }
}
