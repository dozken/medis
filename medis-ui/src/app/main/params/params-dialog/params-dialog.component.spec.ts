import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParamsDialogComponent } from './params-dialog.component';

describe('ParamsDialogComponent', () => {
  let component: ParamsDialogComponent;
  let fixture: ComponentFixture<ParamsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParamsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParamsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
