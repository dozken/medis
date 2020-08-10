import { AfterViewInit, Component, OnInit, ViewChild, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Sample, SampleType } from '../sample.model';
import { SampleService } from '../sample.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-sample-dialog-component',
  styleUrls: ['./sample-dialog.component.scss'],
  templateUrl: './sample-dialog.component.html',
})
export class SampleDialogComponent implements OnInit {
  form: FormGroup;
  @Input() model: Sample;
  public sampleKeys = Object.keys(SampleType);

  constructor(private service: SampleService, private formBuilder: FormBuilder, public activeModal: NgbActiveModal) {
    this.createForm();
  }
  ngOnInit(): void {
    this.form.patchValue(this.model);
  }
  private createForm() {
    this.form = this.formBuilder.group({
      id: null,
      name: [null, Validators.required],
      shortName: null,
      type: [null, Validators.required],
    });
  }
  doSave() {
    if (this.form.value.id) {
      this.service.update(this.form.value).subscribe(result => this.activeModal.close({ action: 'save' }));
    } else {
      this.service.create(this.form.value).subscribe(result => this.activeModal.close({ action: 'save' }));
    }
  }
}
