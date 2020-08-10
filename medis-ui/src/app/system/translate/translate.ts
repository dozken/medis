import { HttpClient } from '@angular/common/http';

import { TranslateService, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

export const TRANSLATE_STORAGE_KEY = 'ngx-translate-lang';

/**
 * Utility method to get selected language from sessionStorage or browser
 */
export function getSelectedLanguage(translateService: TranslateService): string {
  const storedLanguage: string = sessionStorage.getItem(TRANSLATE_STORAGE_KEY);
  // Check if the lenguage has been stored else if the language wasnt stored, then use browser default if supported
  if (storedLanguage && translateService.getLangs().indexOf(storedLanguage) > -1) {
    console.log('LANGUAGE' + storedLanguage);
    return storedLanguage;
  } else if (translateService.getLangs().indexOf(translateService.getBrowserLang()) > -1) {
    console.log('LANGUAGE' + translateService.getBrowserLang());
    return translateService.getBrowserLang();
  }
  // If everything fails, then use default lang
  console.log('LANGUAGE' + translateService.getDefaultLang());
  return translateService.getDefaultLang();
}

/**
 * Crate custom TranslateLoader since we have a diff dir structure for our json files
 */
export function createTranslateLoader(httpClient: HttpClient): TranslateLoader {
  const result = new TranslateHttpLoader(httpClient, 'assets/i18n/', '.json');
  console.log('Loaded translate', result);
  return result;
}
