import { DoctorGetComponent } from '../doctor-get/doctor-get.component';
import { Observable } from "rxjs";
import { DoctorService } from "../doctor/doctor.service";
import { Doctor } from "../doctor/doctor";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.sass']
})
export class DoctorListComponent implements OnInit {
  doctors: Observable<Doctor[]>;

  constructor(private doctorService: DoctorService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.doctors = this.doctorService.getDoctorList();
  }

  deleteDoctor(uin: string) {
    this.doctorService.deleteDoctor(uin)
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
