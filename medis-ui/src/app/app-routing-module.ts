
import { NgModule, Optional, SkipSelf } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { throwIfAlreadyLoaded } from './system/shared/module-import.guard';
import { ParamsComponent } from './main/params/params.component';
import { PatientFormComponent } from './main/patients/patient-form/patient-form.component';
import { PatientsComponent } from './main/patients/patients.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: '',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'parameter',
    loadChildren: './main/params/params.module#ParamsModule',
    // component: ParamsComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'sample',
    loadChildren: './main/sample/sample.module#SampleModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'song',
    loadChildren: './main/song/song.module#SongModule',
    canActivate: [AuthGuard],
  },
  {
    path: 'patient-form',
    // loadChildren: './main/sample/sample.module#SampleModule',
    component: PatientFormComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'patients',
    // loadChildren: './main/sample/sample.module#SampleModule',
    component: PatientsComponent,
    canActivate: [AuthGuard],
  },
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {
    constructor(
        @Optional()
        @SkipSelf()
        parentModule: AppRoutingModule,
    ) {
        throwIfAlreadyLoaded(parentModule, 'AppRoutingModule');
    }

}
