import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Examination } from "../examination/examinationClass";
import { ExaminationService } from "../examination/examination.service";
import {SickLeave} from "../examination/sickLeave";

@Component({
  selector: 'app-examination-edit',
  templateUrl: './examination-edit.component.html',
  styleUrls: ['./examination-edit.component.sass']
})
export class ExaminationEditComponent implements OnInit {
  id: string;
  examination: Examination;
  sickLeave: SickLeave;
  submitted = false;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private examinationService: ExaminationService) { }

  ngOnInit() {
    this.examination = new Examination();
    this.sickLeave = new SickLeave();

    this.id = this.route.snapshot.params['id'];

    this.examinationService.getExamination(this.id)
      .subscribe(data => {
        this.examination = data;
        this.sickLeave = this.examination.sickLeave;
        this.examination.prescriptionText = this.examination.prescription.toString();
      }, error => console.log(error));
  }

  editExamination() {
    this.examination.prescription = this.examination.prescriptionText.split(',').map(p => p.trim());
    this.examination.sickLeave = this.sickLeave;
    this.examinationService.editExamination(this.id, this.examination)
      .subscribe(
        data => this.list(),
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.editExamination();
  }

  list() {
    this.router.navigate(['examinations']);
  }
}
