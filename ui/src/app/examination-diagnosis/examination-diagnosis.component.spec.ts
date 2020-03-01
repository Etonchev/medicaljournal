import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationDiagnosisComponent } from './examination-diagnosis.component';

describe('ExaminationDiagnosisComponent', () => {
  let component: ExaminationDiagnosisComponent;
  let fixture: ComponentFixture<ExaminationDiagnosisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExaminationDiagnosisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationDiagnosisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
