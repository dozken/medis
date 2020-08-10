import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Patient, PatientSearch } from './patient.model';
import { ViewEncapsulation } from '@angular/core';
import { PatientsService } from './patients.service';
import { ConfirmDialogComponent } from 'app/system/shared/confirm-dialog.component';

@Component({
  selector: 'app-patients-component',
  styleUrls: ['./patients.component.css'],
  templateUrl: './patients.component.html',
  encapsulation: ViewEncapsulation.None,
})
export class PatientsComponent implements OnInit {
  patients: Patient[];
  searchDTO: PatientSearch;
  page = 4;
  sliderValue;
  tooltipValue: string;
  constructor(private service: PatientsService, private modalService: NgbModal) {}

  open(content) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title', backdropClass: 'light-blue-backdrop', windowClass: 'modalCenter'});
  }

  ngOnInit() {
    this.searchDTO = new PatientSearch();
    this.doGetAll();
  }

  slideChange() {
    this.searchDTO.ageFrom = this.sliderValue[0];
    this.searchDTO.ageTo = this.sliderValue[1];
    this.doTooltip();
  }

  doGetAll(): void {
    this.service.getAll().subscribe(results => {
      this.patients = results;
      console.log(this.patients);
    });
  }

  doSearch(): void {
    this.service.search(this.searchDTO).subscribe(results => {
      this.patients = results.body;
    });
  }

  doDelete(row: Patient): void {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.text = 'Are you sure you want to delete ' + row.name + '?';
    modalRef.result.then(result => {
      if (result && result.action && result.action === 'yes') {
        this.service.delete(row).subscribe(() => this.doGetAll());
      }
    });
  }

  radioChangeHandler(event: any) {
    this.searchDTO.gender = event.target.value;
  }

  doTooltip() {
    this.tooltipValue = 'from ' + this.sliderValue[0] + ' to ' + this.sliderValue[1] + ' years';
  }
}
