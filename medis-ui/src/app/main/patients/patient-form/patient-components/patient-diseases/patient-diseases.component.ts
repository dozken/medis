import { Component, OnInit } from '@angular/core';
import { ParameterSearch, Parameter, ParameterType } from 'app/main/params/parameter.model';
import { Patient } from 'app/main/patients/patient.model';
import { PatientService } from '../../patient.service';
import { Diseases } from 'app/main/patients/classes/diseases';


@Component({
  selector: 'app-patient-diseases',
  templateUrl: './patient-diseases.component.html',
  styleUrls: ['./patient-diseases.component.css']
})
export class PatientDiseasesComponent implements OnInit {
  searchDTO: ParameterSearch = new ParameterSearch();
  parameters: Parameter[] = [];
  patient: Patient = this.patientService.getPatient();
  disease: Diseases = new Diseases();
  constructor(private patientService: PatientService) {
    this.searchDTO.type = ParameterType.DISEASES;
  }

  ngOnInit() {
    this.patient = this.patientService.getPatient();
    this.doSearch();
  }
  doSearch(): void {
    console.log(this.searchDTO);
    this.patientService.search(this.searchDTO).subscribe(results => {
      this.parameters = results.body;
    });
  }
  submit(){
    this.patient.diseases.push(this.disease);
    this.patientService.setPatient(this.patient);
  }
  delete(){
    this.disease = new Diseases();
  }
}
