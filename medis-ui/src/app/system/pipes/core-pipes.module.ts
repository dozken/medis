import { NgModule, Optional, SkipSelf } from '@angular/core';

import { throwIfAlreadyLoaded } from '../shared/module-import.guard';
import { CommonModule } from '@angular/common';
import { EnumTranslatePipe } from './enum-translate.pipe';

@NgModule({
  declarations: [EnumTranslatePipe],
  exports: [EnumTranslatePipe],
  imports: [CommonModule],
})
export class CorePipesModule {
  constructor(
    @Optional()
    @SkipSelf()
    parentModule: CorePipesModule
  ) {
    throwIfAlreadyLoaded(parentModule, 'PipesModule');
  }
}
