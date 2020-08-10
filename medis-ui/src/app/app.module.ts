import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing-module';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PatientFormComponent } from './main/patients/patient-form/patient-form.component';
import { NgbdTabsetBasic } from './main/patients/patient-form/patient-step/tabset-basic';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { PatientsComponent } from './main/patients/patients.component';
import {
  NgbdModalFocus,
  NgbdModalConfirm,
  NgbdModalConfirmAutofocus,
} from './main/patients/patient-form/confirm-close-dialog/modal-focus';
import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { AuthInterceptor } from './auth/auth.interceptor';
import { LoginComponent } from './auth/login/login.component';
import { NavigationComponent } from './main/navigation/navigation.component';
import { HeaderComponent } from './main/navigation/header/header.component';
import { TranslateService, TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { createTranslateLoader, getSelectedLanguage } from './system/translate/translate';
import { NgxBootstrapSliderModule } from 'ngx-bootstrap-slider';
import { NgxPaginationModule } from 'ngx-pagination';
import { ConfirmDialogComponent } from 'app/system/shared/confirm-dialog.component';
import { PatientInformationComponent } from './main/patients/patient-form/patient-components/patient-information/patient-information.component';
import { PatientContactsComponent } from './main/patients/patient-form/patient-components/patient-contacts/patient-contacts.component';
import { PatientDiseasesComponent } from './main/patients/patient-form/patient-components/patient-diseases/patient-diseases.component';
import { PatientAllergyComponent } from './main/patients/patient-form/patient-components/patient-allergy/patient-allergy.component';
import { PatientTreatmentComponent } from './main/patients/patient-form/patient-components/patient-treatment/patient-treatment.component';
import { PatientApproveComponent } from './main/patients/patient-form/patient-components/patient-approve/patient-approve.component';


@NgModule({
  declarations: [
    AppComponent,
    PatientFormComponent,
    NgbdTabsetBasic,
    PatientsComponent,
    NgbdModalFocus,
    NgbdModalConfirm,
    NgbdModalConfirmAutofocus,
    LoginComponent,
    NavigationComponent,
    HeaderComponent,
    ConfirmDialogComponent,
    PatientInformationComponent,
    PatientContactsComponent,
    PatientDiseasesComponent,
    PatientAllergyComponent,
    PatientTreatmentComponent,
    PatientApproveComponent,
  ],
  imports: [
    AngularFontAwesomeModule,
    BrowserModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    NgxBootstrapSliderModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient],
      },
    }),
    NgxPaginationModule,
  ],
  entryComponents: [NgbdModalConfirm, NgbdModalConfirmAutofocus, ConfirmDialogComponent],
  providers: [
    AuthService,
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    {
      // Configure LOCALE_ID depending on the language set in browser
      provide: LOCALE_ID,
      useFactory: getSelectedLanguage,
      deps: [TranslateService],
    },
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}
