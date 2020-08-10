import { Component, OnInit } from '@angular/core';
// import { ParameterType } from './parameterType.enum';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Parameter, ParameterType, ParameterSearch } from './parameter.model';
import { ParamsService } from './params.service';
import { ParamsDialogComponent } from './params-dialog/params-dialog.component';
import { ConfirmCloseComponent } from './params-dialog/confirm-close.component';
@Component({
  selector: 'app-params',
  templateUrl: './params.component.html',
  styleUrls: ['./params.component.css'],
})
export class ParamsComponent implements OnInit {
  parameter: Parameter = new Parameter();
  parameters: Parameter[] = [];
  searchDTO: ParameterSearch;
  public parameterKeys = Object.keys(ParameterType);
  searchForm: FormGroup;
  constructor(private modalService: NgbModal, private paramsService: ParamsService, private formBuilder: FormBuilder) {

  }


   ngOnInit() {
     this.searchDTO = new ParameterSearch();
     this.doSearch();
  }

  getList() {
    this.paramsService.getAll().subscribe(
      result => {
        this.parameters = result;
      },
      error => {
        console.log(error);
      }
    );
  }
  deleteParam(id: any) {
    const modalRef = this.modalService.open(ConfirmCloseComponent);
    modalRef.componentInstance.id = id;
    modalRef.result.then(result => this.getList());
  }
  open(parameter: any) {
    const modalRef = this.modalService.open(ParamsDialogComponent);
    if (!parameter) {
      parameter = new Parameter();
    }
    modalRef.componentInstance.param = parameter;
    modalRef.result.then(result => this.getList());
  }
  doSearch(): void {
    
    this.paramsService.search(this.searchDTO).subscribe(results => {
      this.parameters = results.body;
      console.log(this.parameters);
    });
  }
}
