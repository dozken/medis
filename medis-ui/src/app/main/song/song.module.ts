import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SongComponent } from './song.component';
import { throwIfAlreadyLoaded } from '../../system/shared/module-import.guard';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SongDialogComponent } from './song-dialog/song-dialog.component';
import { CorePipesModule } from 'app/system/pipes/core-pipes.module';

const routes: Routes = [
  {
    path: '',
    component: SongComponent,
  },
];

@NgModule({
  declarations: [SongComponent, SongDialogComponent],
  imports: [RouterModule.forChild(routes), NgbModule, CommonModule, FormsModule, ReactiveFormsModule, CorePipesModule],

  entryComponents: [SongDialogComponent],
})
export class SongModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: SongModule
  ) {
    throwIfAlreadyLoaded(parentModule, 'SongModule');
  }
}
