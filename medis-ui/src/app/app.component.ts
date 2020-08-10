import { Component } from '@angular/core';
import { getSelectedLanguage } from './system/translate/translate';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  constructor(private translateService: TranslateService) {
    // Add languages
    this.translateService.addLangs(['en', 'kk', 'ru']);
    // Set the default language
    this.translateService.setDefaultLang('ru');
    // Get selected language and load it
    this.translateService.use(getSelectedLanguage(this.translateService));
  }
  title = 'medis-copy';
}
