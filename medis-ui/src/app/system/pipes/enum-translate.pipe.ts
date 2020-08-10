import { Pipe, PipeTransform, ChangeDetectorRef } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

/**
 * This is a dummy pipe that works simular as the translate pipe of ngx-translate/core
 */
@Pipe({
  name: 'enum',
  pure: false, // required to update the value when the promise is resolved
})
export class EnumTranslatePipe implements PipeTransform {
  value: string;
  lastKey: string;

  constructor(private _ref: ChangeDetectorRef, private _translateService: TranslateService) {}

  transform(value: any, key: any): any {
    if (!key || !value) {
      return value;
    }
    this._translateService.get('ENUM.' + key + '.' + value).subscribe(result => {
      this.value = result;
    });
    return this.value;
  }
}
