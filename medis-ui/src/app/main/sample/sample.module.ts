import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SampleComponent } from './sample.component';
import { throwIfAlreadyLoaded } from '../../system/shared/module-import.guard';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SampleDialogComponent } from './sample-dialog/sample-dialog.component';
import { CorePipesModule } from 'app/system/pipes/core-pipes.module';

const routes: Routes = [
  {
    path: '',
    component: SampleComponent,
  },
];

@NgModule({
  declarations: [SampleComponent, SampleDialogComponent],
  imports: [RouterModule.forChild(routes), NgbModule, CommonModule, FormsModule, ReactiveFormsModule, CorePipesModule],

  entryComponents: [SampleDialogComponent],
})
export class SampleModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: SampleModule
  ) {
    throwIfAlreadyLoaded(parentModule, 'SampleModule');
  }
}
