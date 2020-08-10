import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../patient.service';
import { ParameterSearch, ParameterType, Parameter } from '../../../../params/parameter.model';

import { Patient } from 'app/main/patients/patient.model';

import { Degree, Allergy } from 'app/main/patients/classes/allergy';

@Component({
  selector: 'app-patient-allergy',
  templateUrl: './patient-allergy.component.html',
  styleUrls: ['./patient-allergy.component.css'],
})
export class PatientAllergyComponent implements OnInit {
  searchDTO: ParameterSearch = new ParameterSearch();
  parameters: Parameter[] = [];
  childs: String[] = [];
  content = false;
  disableAnother = true;
  another: any = '';
  allergy: Allergy = new Allergy();
  patient: Patient = this.patientService.getPatient();
  public degrees = Object.keys(Degree);
  constructor(private patientService: PatientService) {
    this.searchDTO.type = ParameterType.ALLERGY_CATEGORY;
  }
  ngOnInit() {
    this.patient = this.patientService.getPatient();
    this.doSearch();
    if (this.patient.allergy.length > 0) {
      this.content = false;
    }
  }
  open() {
    this.content = true;
  }
  delete() {
    this.content = false;
    this.searchDTO = new ParameterSearch();
    this.allergy = new Allergy();
  }
  doSearch(): void {
    console.log(this.searchDTO);
    this.patientService.search(this.searchDTO).subscribe(results => {
      this.parameters = results.body;
    });
  }

  filter(param: any) {
    this.getAllergens(param.value);
  }

  getAllergens(categoryAllergy: any) {
    for (let i = 0; i < this.parameters.length; i++) {
      if (this.parameters[i].value === categoryAllergy) {
        this.allergy.category = this.parameters[i].value;
        for (let j = 0; j < this.parameters[i].childParams.length; j++) {
          this.childs[j] = this.parameters[i].childParams[j].value;
        }
      }
    }
  }

  filtering() {
    if (this.allergy.allergen === null) {
      this.disableAnother = false;
      this.allergy.allergen = this.another;
    } else {
      this.disableAnother = true;
      this.another = null;
    }
  }

  submit() {
    this.filtering();
    this.patient.allergy.push(this.allergy);
    this.patientService.setPatient(this.patient);
  }
}
