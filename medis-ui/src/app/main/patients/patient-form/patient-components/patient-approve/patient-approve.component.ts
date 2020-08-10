import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../patient.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-patient-approve',
  templateUrl: './patient-approve.component.html',
  styleUrls: ['./patient-approve.component.css']
})
export class PatientApproveComponent implements OnInit {
  public data;
  constructor(private patientService: PatientService) {
  }

  ngOnInit() {
    // this.patientService.patient.subscribe(data=>{console.log("dataoio",data)});
    this.data = this.patientService.getPatient();
    console.log(this.data);
  }

}
