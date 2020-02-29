import { Doctor } from '../doctor';
import { Component, OnInit, Input } from '@angular/core';
import { DoctorService } from '../doctor.service';
import { DoctorListComponent } from '../doctor-list/doctor-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-doctor-get',
  templateUrl: './doctor-get.component.html',
  styleUrls: ['./doctor-get.component.sass']
})
export class DoctorGetComponent implements OnInit {
  id: string;
  doctor: Doctor;

  constructor(private route: ActivatedRoute,private router: Router,
              private doctorService: DoctorService) { }

  ngOnInit() {
    this.doctor = new Doctor();

    this.id = this.route.snapshot.params['id'];

    this.doctorService.getDoctor(this.id)
      .subscribe(data => {
        console.log(data)
        this.doctor = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['doctors']);
  }
}