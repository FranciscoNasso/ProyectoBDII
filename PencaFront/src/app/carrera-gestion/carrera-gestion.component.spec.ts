import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarreraGestionComponent } from './carrera-gestion.component';

describe('CarreraGestionComponent', () => {
  let component: CarreraGestionComponent;
  let fixture: ComponentFixture<CarreraGestionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarreraGestionComponent]
    });
    fixture = TestBed.createComponent(CarreraGestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
