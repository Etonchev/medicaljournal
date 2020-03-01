import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationGetComponent } from './examination-get.component';

describe('ExaminationGetComponent', () => {
  let component: ExaminationGetComponent;
  let fixture: ComponentFixture<ExaminationGetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExaminationGetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExaminationGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
