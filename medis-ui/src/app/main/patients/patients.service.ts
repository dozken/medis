import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { Observable, Subject } from 'rxjs';

import { Patient, PatientSearch } from './patient.model';

@Injectable({
  providedIn: 'root',
})
export class PatientsService {
  baseUrl = '/api/patient';

  constructor(private http: HttpClient) {
    console.log('Patient service created!');
  }

  getAll(): Observable<Patient[]> {
    return this.http.get<Patient[]>(this.baseUrl);
  }

  delete(patient: Patient): Observable<HttpResponse<any>> {
    return this.http.delete(this.baseUrl + '/' + patient.id, { observe: 'response' });
  }

  create(patient: Patient): Observable<HttpResponse<Patient>> {
    return this.http.post<Patient>(this.baseUrl, patient, { observe: 'response' });
  }

  update(patient: Patient): Observable<HttpResponse<Patient>> {
    return this.http.put<Patient>(this.baseUrl, patient, { observe: 'response' });
  }

  search(search: PatientSearch): Observable<HttpResponse<Patient[]>> {
    return this.http.post<Patient[]>(this.baseUrl + '/search', search, { observe: 'response' });
  }
}
