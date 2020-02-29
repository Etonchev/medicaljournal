import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private baseUrl = 'http://localhost:8080/api/patient';

  constructor(private http: HttpClient) { }

  getPatient(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createPatient(patient: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, patient);
  }

  editPatient(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePatient(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getPatientList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
