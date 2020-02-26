import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private baseUrl = 'http://localhost:8080/api/doctor';

  constructor(private http: HttpClient) { }

  getDoctor(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createDoctor(doctor: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, doctor);
  }

  updateDoctor(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteDoctor(id: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getDoctorList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
