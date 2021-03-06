import { Component, OnInit } from '@angular/core';
import { Doctor } from '../doctor/doctor';
import { ActivatedRoute, Router } from '@angular/router';
import { DoctorService } from '../doctor/doctor.service';

@Component({
  selector: 'app-doctor-edit',
  templateUrl: './doctor-edit.component.html',
  styleUrls: ['./doctor-edit.component.sass']
})
export class DoctorEditComponent implements OnInit {
  id: string;
  doctor: Doctor;
  submitted = false;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private doctorService: DoctorService) { }

  ngOnInit() {
    this.doctor = new Doctor();

    this.id = this.route.snapshot.params['id'];

    this.doctorService.getDoctor(this.id)
      .subscribe(data => {
        console.log(data)
        this.doctor = data;
        this.doctor.specialitiesText = this.doctor.specialities.toString();
      }, error => console.log(error));
  }

  editDoctor() {
    this.doctor.specialities = this.doctor.specialitiesText.split(',').map(s => s.trim());
    this.doctorService.editDoctor(this.id, this.doctor)
      .subscribe(
        data => this.list(),
          error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.editDoctor();
  }

  list() {
    this.router.navigate(['doctors']);
  }
}
