import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LibDashboardComponent } from './lib-dashboard.component';

describe('LibDashboardComponent', () => {
  let component: LibDashboardComponent;
  let fixture: ComponentFixture<LibDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LibDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LibDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
