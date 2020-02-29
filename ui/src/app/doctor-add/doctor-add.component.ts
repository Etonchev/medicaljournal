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

  save() {
    this.doctor.specialities = this.doctor.specialitiesText.split(',').map(s => s.trim());
    this.doctorService.createDoctor(this.doctor)
      .subscribe(
        data => this.list(),
          error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  list() {
    this.router.navigate(['doctors']);
  }
}
