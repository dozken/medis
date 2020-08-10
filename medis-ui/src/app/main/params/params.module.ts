
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { throwIfAlreadyLoaded } from '../../system/shared/module-import.guard';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ParamsDialogComponent } from './params-dialog/params-dialog.component';
import { ConfirmCloseComponent } from './params-dialog/confirm-close.component';
import { ParamsComponent } from './params.component';
import { CorePipesModule } from '../../system/pipes/core-pipes.module';
const routes: Routes = [
  {
      path: '',
      component: ParamsComponent,
  }
];

@NgModule({
  declarations: [
      ParamsComponent,
      ParamsDialogComponent,
      ConfirmCloseComponent
  ],
  imports: [
      RouterModule.forChild(routes),
      NgbModule,
      CommonModule,
      FormsModule,
    ReactiveFormsModule,
     CorePipesModule
  ],

  entryComponents: [
    ParamsDialogComponent,
    ConfirmCloseComponent

  ]
})
export class ParamsModule {
  constructor(
      @Optional()
      @SkipSelf()
      parentModule: ParamsModule
  ) {
      throwIfAlreadyLoaded(parentModule, 'ParamsModule');
  }
}
