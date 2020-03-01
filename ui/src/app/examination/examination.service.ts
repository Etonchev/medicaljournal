import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExaminationService {
  private baseUrl = 'http://localhost:8080/api/examination';

  constructor(private http: HttpClient) { }

  getExamination(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createExamination(examination: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, examination);
  }

  editExamination(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteExamination(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getExaminationList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getExaminationsByDoctorUin(doctorUin: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/?doctorUin=${doctorUin}`);
  }
}
