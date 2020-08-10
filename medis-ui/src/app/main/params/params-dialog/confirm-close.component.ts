import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ParamsService } from '../params.service';

@Component({
    // tslint:disable-next-line:component-selector
    selector: 'confirmclose-app',
    template: `
  <div class="modal-body">
    <p>Вы уверены что хотите удалить?</p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-dark" (click)="Delete()">Да</button>

    <button type="button" class="btn btn-outline-dark" (click)="Cancel()">Нет</button>
    </div>`
})
export class ConfirmCloseComponent implements OnInit {
  @Input() id;
  deleted = false;
  constructor ( public activeModal: NgbActiveModal, private paramsService: ParamsService){

  }
  ngOnInit(){
    console.log(this.id);
  }
  Delete() {
    this.paramsService.delete(this.id).subscribe(result => {console.log(result) ;
    this.deleted = true;

  });
    this.closeModal();
  }

  Cancel() {
    this.closeModal();
  }
  closeModal() {
    this.activeModal.close('Modal Closed');
  }
}
