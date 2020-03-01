import { Doctor } from '../doctor/doctor';
import { Component, OnInit, Input } from '@angular/core';
import { DoctorService } from '../doctor/doctor.service';
import { Router, ActivatedRoute } from '@angular/router';
import {ExaminationService} from "../examination/examination.service";
import {Examination} from "../examination/examinationClass";

@Component({
  selector: 'app-doctor-get',
  templateUrl: './doctor-get.component.html',
  styleUrls: ['./doctor-get.component.sass']
})
export class DoctorGetComponent implements OnInit {
  id: string;
  doctor: Doctor;
  examinations: Examination[];
  numberOfExaminations: number;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private doctorService: DoctorService,
              private examinationService: ExaminationService) { }

  ngOnInit() {
    this.doctor = new Doctor();

    this.id = this.route.snapshot.params['id'];

    this.reloadData();
  }

  reloadData() {
    this.doctorService.getDoctor(this.id)
      .subscribe(data => {
        this.doctor = data;
        this.examinationService.getExaminationsByDoctorUin(this.id).subscribe(
          data => {
            this.examinations = data;
            this.numberOfExaminations = this.examinations.length;
          }, error1 => console.log(error1));
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['doctors']);
  }

  deletePatient(egn: string) {
    this.doctorService.deleteDoctorPatient(this.doctor.uin, egn)
      .subscribe(data => {
        this.reloadData();
      }, error => console.log(error));
  }
}
