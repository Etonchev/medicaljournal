import { Component, OnInit } from '@angular/core';
import { Patient } from '../patient/patient';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../patient/patient.service';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.sass']
})
export class PatientEditComponent implements OnInit {
  id: string;
  patient: Patient;
  submitted = false;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private patientService: PatientService) { }

  ngOnInit() {
    this.patient = new Patient();

    this.id = this.route.snapshot.params['id'];

    this.patientService.getPatient(this.id)
      .subscribe(data => {
        console.log(data)
        this.patient = data;
      }, error => console.log(error));
  }

  editPatient() {
    this.patientService.editPatient(this.id, this.patient)
      .subscribe(
        data => this.list(),
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.editPatient();
  }

  list() {
    this.router.navigate(['patients']);
  }
}
