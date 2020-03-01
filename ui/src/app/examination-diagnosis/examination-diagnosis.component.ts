import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {Examination} from "../examination/examinationClass";
import {ExaminationService} from "../examination/examination.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-examination-diagnosis',
  templateUrl: './examination-diagnosis.component.html',
  styleUrls: ['./examination-diagnosis.component.sass']
})
export class ExaminationDiagnosisComponent implements OnInit {
  diagnosis: string;
  examinations: Observable<Examination[]>;
  numberOfExaminations: number;

  constructor(private examinationService: ExaminationService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit() {
    this.diagnosis = this.route.snapshot.params['diagnosis'];
    this.reloadData();
  }

  reloadData() {
    this.examinations = this.examinationService.getExaminationsByDiagnosis(this.diagnosis);
    this.examinations.subscribe(result => {
      this.numberOfExaminations = result.length;
    });
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
