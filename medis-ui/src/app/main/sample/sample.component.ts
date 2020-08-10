import { Component, OnInit } from '@angular/core';
import { SampleService } from './sample.service';
import { Sample, SampleType, SampleSearch } from './sample.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SampleDialogComponent } from './sample-dialog/sample-dialog.component';
import { ConfirmDialogComponent } from 'app/system/shared/confirm-dialog.component';

@Component({
  selector: 'app-sample-component',
  styleUrls: ['./sample.component.scss'],
  templateUrl: './sample.component.html',
})
export class SampleComponent implements OnInit {
  samples: Sample[];
  searchDTO: SampleSearch;
  public sampleKeys = Object.keys(SampleType);

  constructor(private service: SampleService, private formBuilder: FormBuilder, private modalService: NgbModal) {}

  ngOnInit() {
    this.searchDTO = new SampleSearch();
    this.doSearch();
  }

  doNew() {
    const modalRef = this.modalService.open(SampleDialogComponent);
    modalRef.componentInstance.model = new Sample();
    modalRef.result.then(result => {
      if (result) {
        this.doSearch();
      }
    });
  }
  doEdit(row: Sample): void {
    const modalRef = this.modalService.open(SampleDialogComponent);
    modalRef.componentInstance.model = row;
    modalRef.result.then(result => {
      if (result) {
        this.doSearch();
      }
    });
  }

  doDelete(row: Sample): void {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.text = 'Are you sure you want to delete ' + row.name + '?';
    modalRef.result.then(result => {
      if (result && result.action && result.action === 'yes') {
        this.service.delete(row).subscribe(() => this.doSearch());
      }
    });
  }

  doSearch(): void {
    this.service.search(this.searchDTO).subscribe(results => {
      this.samples = results.body;
    });
  }
}
