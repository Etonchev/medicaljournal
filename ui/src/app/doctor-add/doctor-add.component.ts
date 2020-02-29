import { DoctorService } from '../doctor/doctor.service';
import { Doctor } from '../doctor/doctor';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-add',
  templateUrl: './doctor-add.component.html',
  styleUrls: ['./doctor-add.component.sass']
})
export class DoctorAddComponent implements OnInit {
  doctor: Doctor = new Doctor();
  submitted = false;

  constructor(private doctorService: DoctorService,
              private router: Router) { }

  ngOnInit() {
  }

  newEmployee(): void {
    this.submitted = false;
    this.doctor = new Doctor();
  }

  save() {
    this.doctorService.createDoctor(this.doctor)
      .subscribe(data => console.log(data), error => console.log(error));
    this.doctor = new Doctor();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/doctors']);
  }
}
