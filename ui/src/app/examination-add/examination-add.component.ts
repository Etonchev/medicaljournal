import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Examination } from "../examination/examinationClass";
import { ExaminationService } from "../examination/examination.service";
import {SickLeave} from "../examination/sickLeave";

@Component({
  selector: 'app-examination-add',
  templateUrl: './examination-add.component.html',
  styleUrls: ['./examination-add.component.sass']
})
export class ExaminationAddComponent implements OnInit {
  examination: Examination = new Examination();
  sickLeave: SickLeave = new SickLeave();
  submitted = false;

  constructor(private examinationService: ExaminationService,
              private router: Router) { }

  ngOnInit() {
  }

  save() {
    this.examination.sickLeave = this.sickLeave;
    this.examination.prescription = this.examination.prescriptionText.split(',').map(p => p.trim());
    this.examinationService.createExamination(this.examination)
      .subscribe(
        data => this.list(),
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  list() {
    this.router.navigate(['examinations']);
  }
}
