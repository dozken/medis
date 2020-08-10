import { Component, OnInit } from '@angular/core';
import { Address } from 'app/main/patients/classes/address';
import { ParameterSearch, Parameter, ParameterType } from 'app/main/params/parameter.model';
import { PatientService } from '../../patient.service';
import { Patient } from 'app/main/patients/patient.model';

@Component({
  selector: 'app-patient-contacts',
  templateUrl: './patient-contacts.component.html',
  styleUrls: ['./patient-contacts.component.css'],
})
export class PatientContactsComponent implements OnInit {
  parameters: Parameter[] = [];
  searchDTO: ParameterSearch = new ParameterSearch();
  address: Address = new Address();


  childs: String[] = [];
  content = false;
  patient: Patient = this.patientService.getPatient();

  constructor(private patientService: PatientService) {}

  ngOnInit() {
    this.patient = this.patientService.getPatient();
    this.searchDTO = new ParameterSearch(null, ParameterType.REGION);
    this.doSearch();
  }

  open() {
    this.content = true;
  }

  doSearch(): void {
    this.patientService.search(this.searchDTO).subscribe(results => {
      this.parameters = results.body;
      console.log('param', this.parameters);
    });
  }

  filter(param: any) {
    this.getCities(param.value);
  }

  getCities(regionType: any) {
    for (let i = 0; i < this.parameters.length; i++) {
      if (this.parameters[i].value === regionType) {
        this.address.region = this.parameters[i].value;
        for (let j = 0; j < this.parameters[i].childParams.length; j++) {
          this.childs[j] = this.parameters[i].childParams[j].value;
        }
      }
    }
  }
  mdbradioChangeHandler(event: any) {
    this.address.address_type = event.target.value;
  }

  submit() {
    this.patient.address.push(this.address);
    this.patientService.setPatient(this.patient);
  }

  delete() {
    this.searchDTO = new ParameterSearch();
    this.address = new Address();
  }


}
