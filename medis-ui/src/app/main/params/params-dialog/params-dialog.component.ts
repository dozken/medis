import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Parameter, ParameterType } from '../parameter.model';
import { ParamsService } from '../params.service';

@Component({
  selector: 'app-params-dialog',
  templateUrl: './params-dialog.component.html',
  styleUrls: ['./params-dialog.component.css'],
})
export class ParamsDialogComponent implements OnInit {
  ParameterType = ParameterType;
  parameterTypeValues = Object.keys(ParameterType);
  title: String = '';
  form: FormGroup;
  index = -1;
  disabled = false;
  checked = false;
  parameters: Parameter[] = [];
  @Input() param: Parameter;
  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder, private paramsService: ParamsService) {}
  ngOnInit() {
    this.form = this.createParamForm();
    this.form.patchValue(this.param);
    if (this.param.id != null) {
      this.title = 'Update parameter';
      this.form.controls['type'].disable();
      console.log(this.param);
      this.getSelectedParam(this.param.type);
      this.checked = true;
      this.disabled = true;

      if (this.param.childParams.length > 0) {
        this.checked = true;
        this.disabled = true;
      } else {
        this.checked = false;
        this.disabled = false;
      }
    } else {
      this.title = 'New parameter';
    }
  }

  createParamForm(): FormGroup {
    this.form = this.fb.group({
      id: [null],
      type: [null, Validators.required],
      value: [null, [Validators.required, this.noWhitespaceValidator]],
      childParams: new FormArray([]),
    });
    this.param.childParams.forEach(param => {
      (<FormArray>this.form.controls['childParams']).push(
        new FormGroup({
          type: new FormControl(param.type),
          value: new FormControl(param.value),
        })
      );
    });
    return this.form;
  }

  addChild() {
    (<FormArray>this.form.controls['childParams']).push(
      new FormGroup({
        type: new FormControl(this.param.type),
        value: new FormControl('', [Validators.required, this.noWhitespaceValidator]),
      })
    );
    if ((<FormArray>this.form.controls['childParams']).length > 0) {
      this.disabled = true;
    }
  }

  deleteChild(i: any) {
    (<FormArray>this.form.controls['childParams']).removeAt(i);

    if ((<FormArray>this.form.controls['childParams']).length === 0) {
      this.disabled = false;
      this.checked = false;
    }
  }

  closeModal() {
    this.activeModal.close('Modal Closed');
  }
  getSelectedParam(value: any) {
    for (let i = 0; i < this.parameterTypeValues.length; i++) {
      if (value === this.parameterTypeValues[i]) {
        this.index = i;
        return this.index;
      }
    }
  }

  save() {
    if (this.param.id == null) {
      this.paramsService.create(this.form.value).subscribe(e => this.closeModal(), error => console.log(error));
    } else {
      this.form.value.type = this.param.type;
      this.paramsService.update(this.form.value).subscribe(result => this.closeModal(), error => console.log(error));
    }
  }

  checking() {
    this.checked = !this.checked;
  }

  public noWhitespaceValidator(control: FormControl) {
    const isWhitespace = (control.value || '').trim().length === 0;
    const isValid = !isWhitespace;
    return isValid ? null : { whitespace: true };
  }
}
