import { Component, OnInit } from '@angular/core';
import {  FormGroup } from '@angular/forms';
import { Patient } from '../../../patient.model';
import { PatientService } from '../../patient.service';


@Component({
  selector: 'app-patient-information',
  templateUrl: './patient-information.component.html',
  styleUrls: ['./patient-information.component.css']
})
export class PatientInformationComponent implements OnInit {
  form: FormGroup;
  patient: Patient = this.patientService.getPatient();


  constructor(private patientService: PatientService) {
 }

  ngOnInit() {
    this.patient = this.patientService.getPatient();

  }




submit(){

  console.log('copy', this.patient);

  this.patientService.setPatient(this.patient);
}
}

