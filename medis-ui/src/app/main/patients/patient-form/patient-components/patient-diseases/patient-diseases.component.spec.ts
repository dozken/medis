import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientDiseasesComponent } from './patient-diseases.component';

describe('PatientDiseasesComponent', () => {
  let component: PatientDiseasesComponent;
  let fixture: ComponentFixture<PatientDiseasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientDiseasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientDiseasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
