import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Examination } from "../examination/examinationClass";
import { Router } from "@angular/router";
import { ExaminationService } from "../examination/examination.service";

@Component({
  selector: 'app-examination-list',
  templateUrl: './examination-list.component.html',
  styleUrls: ['./examination-list.component.sass']
})
export class ExaminationListComponent implements OnInit {
  examinations: Observable<Examination[]>;

  constructor(private examinationService: ExaminationService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.examinations = this.examinationService.getExaminationList();
  }

  deleteExamination(uin: string) {
    this.examinationService.deleteExamination(uin)
      .subscribe(
        data => {
          this.reloadData();
        },
        error => console.log(error));
  }

  examinationDetails(id: string) {
    this.router.navigate(['examination/details', id]);
  }

  editExamination(id: string) {
    this.router.navigate(['examination/edit', id]);
  }

  examinationsByDiagnosis(diagnosis: string) {
    this.router.navigate(['examinations/diagnosis', diagnosis])
  }

  patientDetails(id: string) {
    this.router.navigate(['patient/details', id])
  }

  doctorDetails(id: string) {
    this.router.navigate(['doctor/details', id])
  }
}
