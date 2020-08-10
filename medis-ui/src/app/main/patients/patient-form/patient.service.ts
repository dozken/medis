import {Observable, Subject} from 'rxjs';
import { Injectable } from '@angular/core';
import { Patient } from '../patient.model';
import { Parameter, ParameterSearch} from './../../params/parameter.model';
import { Params } from '@angular/router';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Allergy } from '../classes/allergy';

@Injectable({
    providedIn: 'root'
})

export class PatientService {
    // public  patient =  new Subject<Patient>();
    public patient: Patient = new Patient();

    constructor(
        private http: HttpClient,
    ) {
    console.log('Patient service created!');

    }
    setPatient(patient: Patient){

      this.patient = patient;
    }
    getPatient(){
        return this.patient;
    }
    search(search: ParameterSearch): Observable<HttpResponse<Params[]>> {
        return this.http.post<Params[]>('api/parameter/search', search, { observe: 'response' });
      }

}
