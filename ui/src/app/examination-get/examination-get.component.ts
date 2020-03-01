import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Examination } from '../examination/examinationClass';
import { ExaminationService } from "../examination/examination.service";
import {Patient} from "../patient/patient";

@Component({
  selector: 'app-examination-get',
  templateUrl: './examination-get.component.html',
  styleUrls: ['./examination-get.component.sass']
})
export class ExaminationGetComponent implements OnInit {
  id: string;
  examination: Examination;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private examinationService: ExaminationService) { }

  ngOnInit() {
    this.examination = new Examination();

    this.id = this.route.snapshot.params['id'];

    this.reloadData();
  }

  reloadData() {
    this.examinationService.getExamination(this.id)
      .subscribe(data => {
        this.examination = data;
        this.examination.patient = new Patient();
      }, error => console.log(error));
  }

  list() {
    this.router.navigate(['examinations']);
  }
}
